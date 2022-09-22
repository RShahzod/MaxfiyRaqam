package uz.shox.netnomer.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import uz.shox.netnomer.R;
import uz.shox.netnomer.UzmobileSaytActivity;

public class UzmobileFragment extends Fragment {

    private RelativeLayout saytOrqali,ilovaOrqali,videoOrqali;
    private AdView adView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_uzmobile, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);

        saytOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), UzmobileSaytActivity.class));
                Toast.makeText(requireContext(), "Shu yerda qandaydir xato bor!!!", Toast.LENGTH_LONG).show();
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
    private void findViews(View view) {
        saytOrqali=view.findViewById(R.id.sayt_orqali_uzmobile);
        ilovaOrqali=view.findViewById(R.id.ilova_orqali_uzmobile);
        videoOrqali=view.findViewById(R.id.video_orqali_uzmobile);
        adView1 = view.findViewById(R.id.adView1);
    }
}