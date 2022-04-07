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

public class UcellActivity extends AppCompatActivity {

    private RelativeLayout saytOrqali,ilovaOrqali,videoOrqali;
    private AdView adView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucell);

        saytOrqali=findViewById(R.id.sayt_orqali_ucell);
        ilovaOrqali=findViewById(R.id.ilova_orqali_ucell);
        videoOrqali=findViewById(R.id.video_orqali_ucell);

        MobileAds.initialize(UcellActivity.this, new OnInitializationCompleteListener() {
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
                startActivity(new Intent(UcellActivity.this,UcellSaytActivity.class));
            }
        });
        ilovaOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UcellActivity.this, "Hozircha UCellda ilova orqali aniqlash mumkin emas!!!", Toast.LENGTH_LONG).show();

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