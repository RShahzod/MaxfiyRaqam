package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MobiuzSaytActivity extends AppCompatActivity {

    private WebView webViewMobi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobiuz_sayt);

        webViewMobi=findViewById(R.id.webviewMobi);

        WebSettings webSettings= webViewMobi.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewMobi.setWebViewClient(new WebViewClient());
        webViewMobi.loadUrl("https://ip.mobi.uz");
    }

    @Override
    public void onBackPressed() {
        if (webViewMobi.canGoBack()){
            webViewMobi.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}