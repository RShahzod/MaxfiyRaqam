package uz.shox.netnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class UzmobileActivity extends AppCompatActivity {

    private  RelativeLayout saytOrqali,ilovaOrqali,videoOrqali;
    private AdView adView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzmobile);

        saytOrqali=findViewById(R.id.sayt_orqali_uzmobile);
        ilovaOrqali=findViewById(R.id.ilova_orqali_uzmobile);
        videoOrqali=findViewById(R.id.video_orqali_uzmobile);

        MobileAds.initialize(UzmobileActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        adView1 = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);

        saytOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UzmobileActivity.this,UzmobileSaytActivity.class));
                Toast.makeText(UzmobileActivity.this, "Shu yerda qandaydir xato bor!!!", Toast.LENGTH_LONG).show();
            }
        });
        ilovaOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/details?id=uz.uztelecom.telecom";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        videoOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/c/NETNOMER";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}