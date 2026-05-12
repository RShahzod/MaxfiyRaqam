package uz.shox.netnomer

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NetNomerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColors else lightColors,
        content = content,
    )
}

private val lightColors = lightColorScheme(
    primary = Color(0xFF006C51),
    onPrimary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color(0xFFFAFAFA),
    onBackground = Color.Black,
)

private val darkColors = darkColorScheme(
    primary = Color(0xFF7DDCBC),
    onPrimary = Color(0xFF00382A),
    surface = Color(0xFF111412),
    onSurface = Color(0xFFE2E3DF),
    background = Color(0xFF0E1110),
    onBackground = Color(0xFFE2E3DF),
)
