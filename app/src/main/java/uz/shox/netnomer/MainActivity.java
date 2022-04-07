package uz.shox.netnomer;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView uzmobile, beeline, ucell, mobiuz;
    ImageView nav_open;
    DrawerLayout drawerLayout;
    Button btn_Video;
    Button dialod_btn_yopish;
    private Toolbar toolbar_view;
    private AdView adView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beeline = findViewById(R.id.beeline_id);
        uzmobile = findViewById(R.id.uzmobile_id);
        ucell = findViewById(R.id.ucell_id);
        mobiuz = findViewById(R.id.mobiuz_id);
        drawerLayout = findViewById(R.id.drawer_Lay);
        nav_open = findViewById(R.id.toolbar_nav_open);
        toolbar_view = findViewById(R.id.tollbar_for);


        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        adView1 = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);

        toolbar_view.setTitle("");
        setSupportActionBar(toolbar_view);

        nav_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        uzmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UzmobileActivity.class));
            }
        });
        beeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BeelineActivity.class));
            }
        });
        ucell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UcellActivity.class));
            }
        });
        mobiuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MobiuzActivity.class));
            }
        });
        dialog_for();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_baholash:
                String url_baholash = "https://play.google.com/store/apps/details?id=uz.shox.netnomer";
                Intent i_baholash = new Intent(Intent.ACTION_VIEW);
                i_baholash.setData(Uri.parse(url_baholash));
                startActivity(i_baholash);
                break;
            case R.id.nav_ulashish:

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Maxfiy Raqamni Aniqlash \nhttps://play.google.com/store/apps/details?id=uz.shox.netnomer");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            case R.id.nav_haqida:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ilova haqida");
                builder.setMessage("Bu ilova orqali siz Maxfiy raqamdan kelgan qo'ng'iroqning " +
                        "raqamini aniqlashingiz mumkin bo'ladi! \nDasturchi: Shahzod Ro'zimboyev");
                builder.setPositiveButton("Yopish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();

                break;
            case R.id.menu_youtube:
                String url_youtube = "https://www.youtube.com/c/NETNOMER";
                Intent i_youtube = new Intent(Intent.ACTION_VIEW);
                i_youtube.setData(Uri.parse(url_youtube));
                startActivity(i_youtube);
                break;
            case R.id.menu_telegram:
                String url_telegram = "https://t.me/Net_Nomer_YouTube";
                Intent i_telegram = new Intent(Intent.ACTION_VIEW);
                i_telegram.setData(Uri.parse(url_telegram));
                startActivity(i_telegram);
                break;
            case R.id.menu_instagram:
                String url_instagram = "https://instagram.com/net_nomer_youtube";
                Intent i_instagram = new Intent(Intent.ACTION_VIEW);
                i_instagram.setData(Uri.parse(url_instagram));
                startActivity(i_instagram);
                break;
            case R.id.menu_tiktok:
                String url_tiktok = "https://vm.tiktok.com/net_nomer";
                Intent i_tiktok = new Intent(Intent.ACTION_VIEW);
                i_tiktok.setData(Uri.parse(url_tiktok));
                startActivity(i_tiktok);
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit_app: {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ilovadan chiqish!");
            builder.setMessage("Siz haqiqatdan ham ilovadan chiqmoqchimisiz?");
            builder.setCancelable(false);
            builder.setPositiveButton("Chiqish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("Bekor qilish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create();
            builder.show();
            }
            case R.id.rate_app: {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void dialog_for() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_uchun, null);
        builder.setView(view1);
        final AlertDialog dialog = builder.create();
        btn_Video = view1.findViewById(R.id.dialod_btn_video);
        dialod_btn_yopish = view1.findViewById(R.id.dialod_btn_yopish);

        btn_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_dialog = "https://youtu.be/U8cxgQax7tM";
                Intent i_dialog = new Intent(Intent.ACTION_VIEW);
                i_dialog.setData(Uri.parse(url_dialog));
                startActivity(i_dialog);
            }
        });
        dialod_btn_yopish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}