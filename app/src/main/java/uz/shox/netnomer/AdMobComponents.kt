package uz.shox.netnomer

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun AdBanner(adUnitId: String) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val adView = remember(adUnitId) {
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            this.adUnitId = adUnitId
            loadAd(AdRequest.Builder().build())
        }
    }

    DisposableEffect(lifecycleOwner, adView) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> adView.resume()
                Lifecycle.Event.ON_PAUSE -> adView.pause()
                Lifecycle.Event.ON_DESTROY -> adView.destroy()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            adView.destroy()
        }
    }

    AndroidView(
        factory = { adView },
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
    )
}

@Composable
fun InterstitialAdEffect(adUnitId: String, showSignal: Int) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var interstitialAd by remember(adUnitId) { mutableStateOf<InterstitialAd?>(null) }
    var lastShownSignal by remember(adUnitId) { mutableIntStateOf(0) }

    fun loadInterstitial() {
        InterstitialAd.load(
            context,
            adUnitId,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    if (showSignal > lastShownSignal) {
                        ad.show(context as Activity)
                        lastShownSignal = showSignal
                    }
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                }
            },
        )
    }

    LaunchedEffect(adUnitId) {
        loadInterstitial()
    }

    LaunchedEffect(showSignal, interstitialAd) {
        val ad = interstitialAd
        if (showSignal > lastShownSignal && ad != null) {
            ad.show(context as Activity)
            lastShownSignal = showSignal
            interstitialAd = null
        }
    }

    DisposableEffect(lifecycleOwner, adUnitId) {
        var skipFirstResume = true
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (skipFirstResume) {
                    skipFirstResume = false
                } else {
                    val ad = interstitialAd
                    if (ad != null) {
                        ad.show(context as Activity)
                        interstitialAd = null
                    } else {
                        loadInterstitial()
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}
