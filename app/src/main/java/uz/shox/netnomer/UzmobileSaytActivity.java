package uz.shox.netnomer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UzmobileSaytActivity extends AppCompatActivity {

    private WebView webViewuz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzmobile_sayt);

        webViewuz=findViewById(R.id.webUzmobile);

        ViewCompat.setOnApplyWindowInsetsListener(webViewuz, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Apply the insets as a margin to the view. This solution sets only the
            // bottom, left, and right dimensions, but you can apply whichever insets are
            // appropriate to your layout. You can also update the view padding if that's
            // more appropriate.
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.leftMargin = insets.left;
            mlp.bottomMargin = insets.bottom;
            mlp.rightMargin = insets.right;
            v.setLayoutParams(mlp);

            // Return CONSUMED if you don't want the window insets to keep passing
            // down to descendant views.
            return WindowInsetsCompat.CONSUMED;
        });

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