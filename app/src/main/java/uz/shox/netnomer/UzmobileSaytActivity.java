package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UzmobileSaytActivity extends AppCompatActivity {

    private WebView webViewuz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzmobile_sayt);

        webViewuz=findViewById(R.id.webUzmobile);

        WebSettings webSettings= webViewuz.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewuz.setWebViewClient(new WebViewClient());

        webViewuz.loadUrl("https://cabinet.uztelecom.uz/ps/scc/login.php?P_USER_LANG_ID=4");

    }
    @Override
    public void onBackPressed() {
        if (webViewuz.canGoBack()){
            webViewuz.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}