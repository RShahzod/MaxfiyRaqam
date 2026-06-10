package uz.shox.netnomer

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object NavRoutes {
    const val HOME = "home"
    const val DETAIL_ARG = "carrierId"
    const val DETAIL = "detail/{$DETAIL_ARG}"
    const val WEB_ARG = "carrierId"
    const val WEB = "web/{$WEB_ARG}"

    fun detail(carrierId: String) = "detail/$carrierId"
    fun web(carrierId: String) = "web/$carrierId"
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NetNomerNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = LocalActivity.current

    var showHelpDialog by rememberSaveable { mutableStateOf(true) }
    var isHomeDrawerOpen by rememberSaveable { mutableStateOf(false) }
    val isDarkTheme = isSystemInDarkTheme()
    val defaultStatusBarColor = if (isDarkTheme) Color.Black else Color.White
    val useDarkStatusIcons = !isDarkTheme

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val interstitialAdManager = rememberInterstitialAdManager()

    SystemBarsEffect(
        statusBarColor = when (currentRoute) {
            NavRoutes.HOME -> if (isHomeDrawerOpen && !isDarkTheme) Color(0xFF60BF78) else defaultStatusBarColor
            else -> defaultStatusBarColor
        },
        useDarkStatusIcons = useDarkStatusIcons,
    )

    val openUrl: (String) -> Unit = remember {
        { url -> context.openUrl(url) }
    }
    val shareApp: () -> Unit = remember {
        { context.shareApp() }
    }
    val exitApp: () -> Unit = remember(activity) {
        { activity?.finish() }
    }

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
        ) {
            composable(NavRoutes.HOME) {
                HomeScreen(
                    showHelpDialog = showHelpDialog,
                    onCarrierClick = { config ->
                        navController.navigate(NavRoutes.detail(config.id.name))
                    },
                    onHelpDialogDismissed = { showHelpDialog = false },
                    onDrawerOpenChange = { isHomeDrawerOpen = it },
                    openUrl = openUrl,
                    shareApp = shareApp,
                    exitApp = exitApp,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                )
            }
            composable(
                route = NavRoutes.DETAIL,
                arguments = listOf(navArgument(NavRoutes.DETAIL_ARG) { type = NavType.StringType }),
            ) { backStackEntry ->
                val carrierId = backStackEntry.arguments
                    ?.getString(NavRoutes.DETAIL_ARG) ?: return@composable
                val config = CarrierPageConfigs.require(CarrierId.valueOf(carrierId))
                CarrierDetailScreen(
                    config = config,
                    onOpenWebsite = { navController.navigate(NavRoutes.web(carrierId)) },
                    onOpenUrl = openUrl,
                    onBack = { navController.popBackStack() },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                )
            }
            composable(
                route = NavRoutes.WEB,
                arguments = listOf(navArgument(NavRoutes.WEB_ARG) { type = NavType.StringType }),
            ) { backStackEntry ->
                val carrierId = backStackEntry.arguments
                    ?.getString(NavRoutes.WEB_ARG) ?: return@composable
                val config = CarrierPageConfigs.require(CarrierId.valueOf(carrierId))
                CarrierWebScreen(
                    title = config.title,
                    url = config.websiteUrl,
                    onClose = {
                        interstitialAdManager.show {
                            navController.popBackStack()
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun SystemBarsEffect(
    statusBarColor: Color,
    useDarkStatusIcons: Boolean,
) {
    val activity = LocalActivity.current
    DisposableEffect(activity, statusBarColor, useDarkStatusIcons) {
        if (activity != null) {
            val previousStatusBarColor = activity.window.statusBarColor
            val controller = WindowCompat.getInsetsController(
                activity.window,
                activity.window.decorView,
            )
            activity.window.statusBarColor = statusBarColor.toArgb()
            controller.isAppearanceLightStatusBars = useDarkStatusIcons
            onDispose {
                activity.window.statusBarColor = previousStatusBarColor
            }
        } else {
            onDispose { }
        }
    }
}

private fun Context.openUrl(url: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (_: ActivityNotFoundException) {
        if (url.startsWith(Constants.Links.MARKET_SCHEME)) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(Constants.Links.playStoreUrl(packageName)),
                ),
            )
        }
    } catch (_: SecurityException) {
        // Unable to handle the intent — no matching app
    }
}

private fun Context.shareApp() {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_text) + "\n" + Constants.Links.APP_PLAY_STORE,
        )
        type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, null))
}
