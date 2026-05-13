package uz.shox.netnomer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeDrawer(
    openUrl: (String) -> Unit,
    shareApp: () -> Unit,
    showAbout: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalDrawerSheet(
        modifier = modifier.fillMaxWidth(0.75f),
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF60BF78))
                    .statusBarsPadding()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.splash_logo),
                    contentDescription = stringResource(R.string.logo),
                    modifier = Modifier.size(96.dp),
                )
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.ilovani_baholash)) },
                selected = false,
                icon = { Icon(Icons.Rounded.Star, contentDescription = null) },
                onClick = {
                    closeDrawer()
                    openUrl(Constants.Links.APP_PLAY_STORE)
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            )
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.ilovani_ulashish)) },
                selected = false,
                icon = { Icon(Icons.Rounded.Share, contentDescription = null) },
                onClick = {
                    closeDrawer()
                    shareApp()
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            )
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.ilova_haqida)) },
                selected = false,
                icon = { Icon(Icons.Rounded.Info, contentDescription = null) },
                onClick = {
                    closeDrawer()
                    showAbout()
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            )
            HorizontalDivider(Modifier.padding(vertical = 12.dp))
            Text(
                text = stringResource(R.string.bizga_qo_shiling),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp),
            )
            DrawerLink(stringResource(R.string.youtube), R.drawable.youtube) {
                closeDrawer()
                openUrl(Constants.Links.YOUTUBE)
            }
            DrawerLink(stringResource(R.string.telegram), R.drawable.telegra) {
                closeDrawer()
                openUrl(Constants.Links.TELEGRAM)
            }
            DrawerLink(stringResource(R.string.instagram), R.drawable.instag) {
                closeDrawer()
                openUrl(Constants.Links.INSTAGRAM)
            }
            DrawerLink(stringResource(R.string.tiktok), R.drawable.ic_tiktok) {
                closeDrawer()
                openUrl(Constants.Links.TIKTOK)
            }
        }
    }
}

@Composable
fun DrawerLink(text: String, iconResId: Int, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(text) },
        selected = false,
        icon = {
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        },
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
    )
}

@Composable
fun HelpDialog(onVideoClick: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Image(
                painter = painterResource(R.drawable.alert),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
            )
        },
        title = null,
        text = {
            Text(
                text = stringResource(R.string.dialog_uchun),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        },
        confirmButton = {
            TextButton(onClick = onVideoClick) {
                Text(stringResource(R.string.video_orqali_o_rganish))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.yopish))
            }
        },
    )
}

@Composable
fun CarrierLogoButton(
    config: CarrierPageConfig,
    size: Dp = 160.dp,
    containerColor: Color = config.logoBackgroundColor,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .clickable(role = Role.Button, onClick = onClick),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Image(
            painter = painterResource(config.logoResId),
            contentDescription = config.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun CarrierActionCard(
    text: String,
    icon: ImageVector,
    accentColor: Color,
    dividerColor: Color,
    containerColor: Color = Color.White,
    contentColor: Color = accentColor,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 6.dp)
            .clickable(role = Role.Button, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(34.dp),
                )
            }
            Spacer(Modifier.width(14.dp))
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(80.dp)
                    .background(dividerColor),
            )
            Spacer(Modifier.width(24.dp))
            Text(
                text = text,
                color = contentColor,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
