package uz.shox.netnomer

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(
    adUnitId: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val adView = remember {
        AdView(context).apply {
            this.adUnitId = adUnitId
            setAdSize(AdSize.BANNER)
            loadAd(AdRequest.Builder().build())
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            adView.destroy()
        }
    }

    AndroidView(
        factory = { adView },
        modifier = modifier,
        update = { view ->
            if (view.parent != null) {
                (view.parent as? ViewGroup)?.removeView(view)
            }
        },
    )
}
