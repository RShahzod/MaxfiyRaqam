package uz.shox.netnomer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import uz.shox.netnomer.R;
import uz.shox.netnomer.fragments.BeelineFragment;
import uz.shox.netnomer.fragments.MobiuzFragment;
import uz.shox.netnomer.fragments.UcellFragment;
import uz.shox.netnomer.fragments.UzmobileFragment;

public class AllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);


        String names = getIntent().getStringExtra("key");

        Fragment fragment= null;

        switch (names){
            case "uzmobile":
                fragment= new UzmobileFragment();
                break;
            case "beeline":
                fragment= new BeelineFragment();
                break;
            case "ucell":
                fragment= new UcellFragment();
                break;
            case "mobiuz":
                fragment= new MobiuzFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }
}