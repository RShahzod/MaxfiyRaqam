package uz.shox.netnomer

object Constants {
    object Links {
        const val MARKET_SCHEME = "market://"
        const val APP_PLAY_STORE = "https://play.google.com/store/apps/details?id=uz.shox.maxfiy_raqam"
        const val APP_MARKET = "market://details?id=uz.shox.maxfiy_raqam"
        const val HELP_VIDEO = "https://youtube.com/@netnomer"

        const val YOUTUBE = "https://youtube.com/@netnomer"
        const val TELEGRAM = "https://t.me/Net_Nomer_YouTube"
        const val INSTAGRAM = "https://instagram.com/net_nomer_youtube"
        const val TIKTOK = "https://vm.tiktok.com/net_nomer"

        const val UZMOBILE_WEBSITE = "https://cabinet.uztelecom.uz/ps/scc/login.php?P_USER_LANG_ID=4"
        const val UZMOBILE_APP = "https://play.google.com/store/apps/details?id=uz.uztelecom.telecom"
        const val UZMOBILE_VIDEO = "https://youtube.com/@netnomer"

        const val UCELL_WEBSITE = "https://my.ucell.uz"
        const val UCELL_VIDEO = "https://youtube.com/@netnomer"

        const val BEELINE_WEBSITE = "https://beeline.uz/uz/signin"
        const val BEELINE_APP = "https://play.google.com/store/apps/details?id=uz.beeline.odp"
        const val BEELINE_VIDEO = "https://youtube.com/@netnomer"

        const val MOBIUZ_WEBSITE = "https://ip.mobi.uz"
        const val MOBIUZ_VIDEO = "https://youtube.com/@netnomer"

        fun playStoreUrl(packageName: String): String =
            "https://play.google.com/store/apps/details?id=$packageName"
    }
}
