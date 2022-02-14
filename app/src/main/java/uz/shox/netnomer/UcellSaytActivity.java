package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UcellSaytActivity extends AppCompatActivity {

    private WebView webViewUc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucell_sayt);

        webViewUc=findViewById(R.id.webviewUc);

        WebSettings webSettings= webViewUc.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewUc.setWebViewClient(new WebViewClient());
        webViewUc.loadUrl("https://my.ucell.uz");

    }

    @Override
    public void onBackPressed() {
        if (webViewUc.canGoBack()){
            webViewUc.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}