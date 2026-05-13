package uz.shox.netnomer

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Shop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CarrierDetailScreen(
    config: CarrierPageConfig,
    onOpenWebsite: () -> Unit,
    onOpenUrl: (String) -> Unit,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val screenColor = if (isDarkTheme) MaterialTheme.colorScheme.background else config.primaryColor
    val headerColor = MaterialTheme.colorScheme.surface
    val titleColor = if (isDarkTheme) MaterialTheme.colorScheme.onBackground else config.textColor
    val dividerColor = if (isDarkTheme) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f) else Color.White
    val cardColor = MaterialTheme.colorScheme.surface
    val cardTextColor = if (isDarkTheme) MaterialTheme.colorScheme.onSurface else config.primaryColor
    val logoSharedModifier = if (sharedTransitionScope != null && animatedVisibilityScope != null) {
        with(sharedTransitionScope) {
            Modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(key = "carrier-${config.id.name}"),
                animatedVisibilityScope = animatedVisibilityScope,
            )
        }
    } else {
        Modifier
    }

    BackHandler(onBack = onBack)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenColor),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(headerColor)
                        .padding(top = 16.dp, bottom = 20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CarrierLogoButton(
                        config = config,
                        modifier = logoSharedModifier,
                        onClick = {},
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(screenColor)
                        .padding(horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.maxfiy_raqamni_aniqlash),
                        color = titleColor,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .semantics { heading() },
                    )
                    CarrierActionCard(
                        text = stringResource(R.string.sayt_orqali_aniqlash),
                        icon = Icons.Rounded.Public,
                        accentColor = config.primaryColor,
                        dividerColor = if (isDarkTheme) dividerColor else config.dividerColor,
                        containerColor = cardColor,
                        contentColor = cardTextColor,
                        onClick = onOpenWebsite,
                    )
                    CarrierActionCard(
                        text = stringResource(R.string.ilova_orqali_aniqlash),
                        icon = Icons.Rounded.Shop,
                        accentColor = config.primaryColor,
                        dividerColor = if (isDarkTheme) dividerColor else config.dividerColor,
                        containerColor = cardColor,
                        contentColor = cardTextColor,
                        onClick = {
                            when (val action = config.appAction) {
                                is CarrierAppAction.OpenUrl -> onOpenUrl(action.url)
                                is CarrierAppAction.ShowToast -> {
                                    Toast.makeText(context, context.getString(action.messageResId), Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                    )
                    Spacer(Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(dividerColor),
                    )
                    Text(
                        text = stringResource(R.string.maxfiy_raqam_aniqlashni_o_rganish),
                        color = titleColor,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(30.dp),
                    )
                    CarrierActionCard(
                        text = stringResource(R.string.video_orqali_o_rganish),
                        icon = Icons.Rounded.PlayArrow,
                        accentColor = config.primaryColor,
                        dividerColor = if (isDarkTheme) dividerColor else config.dividerColor,
                        containerColor = cardColor,
                        contentColor = cardTextColor,
                        onClick = { onOpenUrl(config.videoUrl) },
                    )
                }
            }
        }
    }
}
