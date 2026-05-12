package uz.shox.netnomer

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

private sealed interface AppScreen {
    data object Home : AppScreen
    data class Detail(
        val config: CarrierPageConfig,
        val showInterstitialOnEnter: Boolean = false,
    ) : AppScreen

    data class Web(val config: CarrierPageConfig) : AppScreen
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NetNomerApp(activity: Activity) {
    var screen by remember { mutableStateOf<AppScreen>(AppScreen.Home) }
    var showHelpDialog by rememberSaveable { mutableStateOf(true) }
    var isHomeDrawerOpen by rememberSaveable { mutableStateOf(false) }
    val isDarkTheme = isSystemInDarkTheme()
    val defaultStatusBarColor = if (isDarkTheme) Color.Black else Color.White
    val useDarkStatusIcons = !isDarkTheme

    BackHandler(enabled = screen !is AppScreen.Home) {
        screen = AppScreen.Home
    }

    SharedTransitionLayout {
        AnimatedContent(
            targetState = screen,
            label = "app-screen",
            transitionSpec = {
                fadeIn(tween(180)).togetherWith(fadeOut(tween(180))).using(SizeTransform(clip = false))
            },
        ) { current ->
            when (current) {
                AppScreen.Home -> {
                    SystemBarsEffect(
                        activity = activity,
                        statusBarColor = if (isHomeDrawerOpen && !isDarkTheme) {
                            Color(0xFF60BF78)
                        } else {
                            defaultStatusBarColor
                        },
                        useDarkStatusIcons = useDarkStatusIcons,
                    )
                    HomeScreen(
                        showHelpDialog = showHelpDialog,
                        onCarrierClick = { screen = AppScreen.Detail(it) },
                        onHelpDialogDismissed = { showHelpDialog = false },
                        onDrawerOpenChange = { isHomeDrawerOpen = it },
                        openUrl = activity::openUrl,
                        shareApp = activity::shareApp,
                        exitApp = activity::finish,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                    )
                }
                is AppScreen.Detail -> {
                    SystemBarsEffect(
                        activity = activity,
                        statusBarColor = defaultStatusBarColor,
                        useDarkStatusIcons = useDarkStatusIcons,
                    )
                    CarrierDetailScreen(
                        config = current.config,
                        showInterstitialOnEnter = current.showInterstitialOnEnter,
                        onOpenWebsite = { screen = AppScreen.Web(current.config) },
                        onOpenUrl = activity::openUrl,
                        onBack = { screen = AppScreen.Home },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                    )
                }
                is AppScreen.Web -> {
                    SystemBarsEffect(
                        activity = activity,
                        statusBarColor = defaultStatusBarColor,
                        useDarkStatusIcons = useDarkStatusIcons,
                    )
                    CarrierWebScreen(
                        title = current.config.title,
                        url = current.config.websiteUrl,
                        onClose = {
                            screen = AppScreen.Detail(
                                config = current.config,
                                showInterstitialOnEnter = true,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun SystemBarsEffect(
    activity: Activity,
    statusBarColor: Color,
    useDarkStatusIcons: Boolean,
) {
    DisposableEffect(activity, statusBarColor, useDarkStatusIcons) {
        val previousStatusBarColor = activity.window.statusBarColor
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        activity.window.statusBarColor = statusBarColor.toArgb()
        controller.isAppearanceLightStatusBars = useDarkStatusIcons
        onDispose {
            activity.window.statusBarColor = previousStatusBarColor
        }
    }
}

private fun Activity.openUrl(url: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (ignored: ActivityNotFoundException) {
        if (url.startsWith(Constants.Links.MARKET_SCHEME)) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.Links.playStoreUrl(packageName)),
                ),
            )
        }
    }
}

private fun Activity.shareApp() {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Maxfiy Raqamni Aniqlash \n${Constants.Links.APP_PLAY_STORE}",
        )
        type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, null))
}
