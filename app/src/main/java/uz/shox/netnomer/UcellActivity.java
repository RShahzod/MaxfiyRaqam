package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UcellActivity extends AppCompatActivity {

    private RelativeLayout saytOrqali,ilovaOrqali,videoOrqali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucell);

        saytOrqali=findViewById(R.id.sayt_orqali_ucell);
        ilovaOrqali=findViewById(R.id.ilova_orqali_ucell);
        videoOrqali=findViewById(R.id.video_orqali_ucell);

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