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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import uz.shox.netnomer.BeelineSaytActivity;
import uz.shox.netnomer.R;

public class BeelineFragment extends Fragment {

    private RelativeLayout saytOrqali, ilovaOrqali, videoOrqali;
    private AdView adView1;
    private InterstitialAd mInterstitialAd;
    private boolean showAd = false;
    private boolean adShown = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MobileAds.initialize(requireContext(), initializationStatus -> {

        });

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireContext(), "ca-app-pub-7532241080505290/4775827416", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beeline, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);

        saytOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), BeelineSaytActivity.class));
            }
        });
        ilovaOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/details?id=uz.beeline.odp";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        videoOrqali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://youtu.be/MMDXSPdXH8k";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void findViews(View view) {
        saytOrqali = view.findViewById(R.id.sayt_orqali_beeline);
        ilovaOrqali = view.findViewById(R.id.ilova_orqali_beeline);
        videoOrqali = view.findViewById(R.id.video_orqali_beeline);
        adView1 = view.findViewById(R.id.adView1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!showAd) {
            showAd = true;
        } else if (!adShown) {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(requireActivity());
                adShown = true;
            }
        }
    }
}