package uz.shox.netnomer

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import hotchemi.android.rate.AppRate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDarkTheme = resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        enableEdgeToEdge(
            statusBarStyle = if (isDarkTheme) {
                SystemBarStyle.dark(android.graphics.Color.BLACK)
            } else {
                SystemBarStyle.light(android.graphics.Color.WHITE, android.graphics.Color.WHITE)
            },
            navigationBarStyle = if (isDarkTheme) {
                SystemBarStyle.dark(android.graphics.Color.BLACK)
            } else {
                SystemBarStyle.light(android.graphics.Color.WHITE, android.graphics.Color.WHITE)
            },
        )
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !isDarkTheme

        AppRate.with(this)
            .setInstallDays(3)
            .setLaunchTimes(3)
            .setRemindInterval(1)
            .setShowLaterButton(true)
            .setDebug(false)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)

        setContent {
            NetNomerTheme {
                NetNomerNavHost()
            }
        }
    }
}
