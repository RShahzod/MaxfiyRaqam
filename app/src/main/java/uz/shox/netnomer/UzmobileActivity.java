package uz.shox.netnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UzmobileActivity extends AppCompatActivity {

     private  RelativeLayout saytOrqali,ilovaOrqali,videoOrqali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzmobile);

        saytOrqali=findViewById(R.id.sayt_orqali_uzmobile);
        ilovaOrqali=findViewById(R.id.ilova_orqali_uzmobile);
        videoOrqali=findViewById(R.id.video_orqali_uzmobile);

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