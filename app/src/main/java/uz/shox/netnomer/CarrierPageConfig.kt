package uz.shox.netnomer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

enum class CarrierId {
    Uzmobile,
    Ucell,
    Beeline,
    Mobiuz,
}

sealed interface CarrierAppAction {
    data class OpenUrl(val url: String) : CarrierAppAction
    data class ShowToast(@StringRes val messageResId: Int) : CarrierAppAction
}

data class CarrierPageConfig(
    val id: CarrierId,
    val title: String,
    val primaryColor: Color,
    val textColor: Color,
    val dividerColor: Color,
    val logoBackgroundColor: Color,
    val homeLogoBackgroundColor: Color,
    @DrawableRes val logoResId: Int,
    val websiteUrl: String,
    val appAction: CarrierAppAction,
    val videoUrl: String,
)

object CarrierPageConfigs {
    val all: List<CarrierPageConfig> = listOf(
        CarrierPageConfig(
            id = CarrierId.Uzmobile,
            title = "Uzmobile",
            primaryColor = Color(0xFF00ABEE),
            textColor = Color.White,
            dividerColor = Color.White,
            logoBackgroundColor = Color(0xFF0FA6F6),
            homeLogoBackgroundColor = Color(0xFF0FA6F6),
            logoResId = R.drawable.uzmobile_logo,
            websiteUrl = Constants.Links.UZMOBILE_WEBSITE,
            appAction = CarrierAppAction.OpenUrl(Constants.Links.UZMOBILE_APP),
            videoUrl = Constants.Links.UZMOBILE_VIDEO,
        ),
        CarrierPageConfig(
            id = CarrierId.Ucell,
            title = "Ucell",
            primaryColor = Color(0xFF623592),
            textColor = Color.White,
            dividerColor = Color.White,
            logoBackgroundColor = Color(0xFF0FA6F6),
            homeLogoBackgroundColor = Color.Black,
            logoResId = R.drawable.ucel,
            websiteUrl = Constants.Links.UCELL_WEBSITE,
            appAction = CarrierAppAction.OpenUrl(Constants.Links.UCELL_APP),
            videoUrl = Constants.Links.UCELL_VIDEO,
        ),
        CarrierPageConfig(
            id = CarrierId.Beeline,
            title = "Beeline",
            primaryColor = Color(0xFFFDD537),
            textColor = Color.Black,
            dividerColor = Color.Black,
            logoBackgroundColor = Color(0xFFFDD537),
            homeLogoBackgroundColor = Color(0xFFFFD001),
            logoResId = R.drawable.beeline,
            websiteUrl = Constants.Links.BEELINE_WEBSITE,
            appAction = CarrierAppAction.OpenUrl(Constants.Links.BEELINE_APP),
            videoUrl = Constants.Links.BEELINE_VIDEO,
        ),
        CarrierPageConfig(
            id = CarrierId.Mobiuz,
            title = "Mobiuz",
            primaryColor = Color(0xFFF4473A),
            textColor = Color.White,
            dividerColor = Color.White,
            logoBackgroundColor = Color(0xFF0FA6F6),
            homeLogoBackgroundColor = Color.Black,
            logoResId = R.drawable.mobiuz,
            websiteUrl = Constants.Links.MOBIUZ_WEBSITE,
            appAction = CarrierAppAction.OpenUrl(Constants.Links.MOBIUZ_APP),
            videoUrl = Constants.Links.MOBIUZ_VIDEO,
        ),
    )

    fun require(id: CarrierId): CarrierPageConfig =
        all.first { it.id == id }
}
