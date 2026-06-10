package uz.shox.netnomer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    showHelpDialog: Boolean,
    onCarrierClick: (CarrierPageConfig) -> Unit,
    onHelpDialogDismissed: () -> Unit,
    onDrawerOpenChange: (Boolean) -> Unit = {},
    openUrl: (String) -> Unit,
    shareApp: () -> Unit,
    exitApp: () -> Unit,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showAboutDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }
    var showOverflow by remember { mutableStateOf(false) }

    LaunchedEffect(drawerState.isOpen) {
        onDrawerOpenChange(drawerState.isOpen)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeDrawer(
                openUrl = openUrl,
                shareApp = shareApp,
                showAbout = { showAboutDialog = true },
                closeDrawer = { scope.launch { drawerState.close() } },
            )
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Rounded.Menu, contentDescription = stringResource(R.string.navigation_open))
                        }
                    },
                    actions = {
                        Box {
                            IconButton(onClick = { showOverflow = true }) {
                                Icon(Icons.Rounded.MoreVert, contentDescription = stringResource(R.string.toolbar_more))
                            }
                            DropdownMenu(
                                expanded = showOverflow,
                                onDismissRequest = { showOverflow = false },
                            ) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.chiqish)) },
                                    leadingIcon = { Icon(Icons.AutoMirrored.Rounded.ExitToApp, contentDescription = null) },
                                    onClick = {
                                        showOverflow = false
                                        showExitDialog = true
                                    },
                                )
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.baholash)) },
                                    leadingIcon = { Icon(Icons.Rounded.Star, contentDescription = null) },
                                    onClick = {
                                        showOverflow = false
                                        openUrl(Constants.Links.APP_MARKET)
                                    },
                                )
                            }
                        }
                    },
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    top = innerPadding.calculateTopPadding() + 20.dp,
                    end = 16.dp,
                    bottom = innerPadding.calculateBottomPadding() + 24.dp,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Text(
                        text = stringResource(R.string.maxfiy_raqamni_aniqlash),
                        color = Color(0xFFCCCC00),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics { heading() },
                    )
                    Spacer(Modifier.height(64.dp))
                }
                item {
                    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                        val logoSize = ((maxWidth - 16.dp) / 2).coerceAtMost(160.dp)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            CarrierPageConfigs.all.chunked(2).forEach { rowConfigs ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    rowConfigs.forEach { config ->
                                        val sharedModifier = if (
                                            sharedTransitionScope != null &&
                                            animatedVisibilityScope != null
                                        ) {
                                            with(sharedTransitionScope) {
                                                Modifier.sharedBounds(
                                                    sharedContentState = rememberSharedContentState(
                                                        key = "carrier-${config.id.name}",
                                                    ),
                                                    animatedVisibilityScope = animatedVisibilityScope,
                                                )
                                            }
                                        } else {
                                            Modifier
                                        }
                                        CarrierLogoButton(
                                            config = config,
                                            size = logoSize,
                                            containerColor = config.homeLogoBackgroundColor,
                                            modifier = sharedModifier,
                                            onClick = { onCarrierClick(config) },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    BannerAd(
                        adUnitId = AdsConstants.BANNER_HOME,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }

    if (showHelpDialog) {
        HelpDialog(
            onVideoClick = {
                onHelpDialogDismissed()
                openUrl(Constants.Links.HELP_VIDEO)
            },
            onDismiss = onHelpDialogDismissed,
        )
    }

    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            title = { Text(stringResource(R.string.about_title)) },
            text = {
                Text(stringResource(R.string.about_description))
            },
            confirmButton = {
                TextButton(onClick = { showAboutDialog = false }) {
                    Text(stringResource(R.string.yopish))
                }
            },
        )
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(stringResource(R.string.exit_title)) },
            text = { Text(stringResource(R.string.exit_message)) },
            confirmButton = {
                TextButton(onClick = exitApp) {
                    Text(stringResource(R.string.chiqish))
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            },
        )
    }
}
