package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BeelineSaytActivity extends AppCompatActivity {

    private WebView webViewBee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beeline_sayt);


        webViewBee=findViewById(R.id.webviewBee);

        WebSettings webSettings= webViewBee.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewBee.setWebViewClient(new WebViewClient());
        webViewBee.loadUrl("https://beeline.uz/uz/signin");
    }

    @Override
    public void onBackPressed() {
        if (webViewBee.canGoBack()){
            webViewBee.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}