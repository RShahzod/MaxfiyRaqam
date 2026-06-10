package uz.shox.netnomer

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdManager(
    private val activity: Activity,
    private val adUnitId: String,
    private val cooldownMs: Long = 3 * 60 * 1000L,
) {
    private var interstitialAd: InterstitialAd? = null
    private var lastShowTime: Long = 0L

    fun load() {
        InterstitialAd.load(
            activity,
            adUnitId,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e("InterstitialAd", "Failed to load: code=${loadAdError.code}, msg=${loadAdError.message}, cause=${loadAdError.cause}")
                    interstitialAd = null
                }
            },
        )
    }

    fun show(onAdDismissed: () -> Unit = {}) {
        val now = System.currentTimeMillis()
        if (now - lastShowTime < cooldownMs) {
            onAdDismissed()
            return
        }

        interstitialAd?.let { ad ->
            lastShowTime = now
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    load()
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e("InterstitialAd", "Failed to show: code=${adError.code}, msg=${adError.message}, cause=${adError.cause}")
                    interstitialAd = null
                    load()
                    onAdDismissed()
                }
            }
            ad.show(activity)
        } ?: onAdDismissed()
    }

    fun destroy() {
        interstitialAd = null
    }
}

@Composable
fun rememberInterstitialAdManager(
    adUnitId: String = AdsConstants.INTERSTITIAL,
): InterstitialAdManager {
    val context = LocalContext.current
    val activity = context as Activity
    val manager = remember(activity, adUnitId) {
        InterstitialAdManager(activity, adUnitId)
    }

    DisposableEffect(Unit) {
        manager.load()
        onDispose { manager.destroy() }
    }

    return manager
}
