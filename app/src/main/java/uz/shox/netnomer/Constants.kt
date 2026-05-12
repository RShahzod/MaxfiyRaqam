package uz.shox.netnomer

object Constants {
    object Links {
        const val MARKET_SCHEME = "market://"
        const val APP_PLAY_STORE = "https://play.google.com/store/apps/details?id=uz.shox.netnomer"
        const val APP_MARKET = "market://details?id=uz.shox.netnomer"
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

    object AdUnits {
        const val HOME_BANNER = "ca-app-pub-7532241080505290/8984885060"

        const val UZMOBILE_BANNER = "ca-app-pub-7532241080505290/7208768968"
        const val UZMOBILE_INTERSTITIAL = "ca-app-pub-7532241080505290/9951545741"

        const val UCELL_BANNER = "ca-app-pub-7532241080505290/5975578342"
        const val UCELL_INTERSTITIAL = "ca-app-pub-7532241080505290/8523500731"

        const val BEELINE_BANNER = "ca-app-pub-7532241080505290/7097088327"
        const val BEELINE_INTERSTITIAL = "ca-app-pub-7532241080505290/4775827416"

        const val MOBIUZ_BANNER = "ca-app-pub-7532241080505290/8330278944"
        const val MOBIUZ_INTERSTITIAL = "ca-app-pub-7532241080505290/6831430521"
    }
}
