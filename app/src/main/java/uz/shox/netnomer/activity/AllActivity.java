package uz.shox.netnomer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Apply the insets as a margin to the view. This solution sets only the
            // bottom, left, and right dimensions, but you can apply whichever insets are
            // appropriate to your layout. You can also update the view padding if that's
            // more appropriate.
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.topMargin = insets.top;
            mlp.leftMargin = insets.left;
            mlp.bottomMargin = insets.bottom;
            mlp.rightMargin = insets.right;
            v.setLayoutParams(mlp);

            // Return CONSUMED if you don't want the window insets to keep passing
            // down to descendant views.
            return WindowInsetsCompat.CONSUMED;
        });


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