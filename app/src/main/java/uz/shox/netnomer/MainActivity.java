package uz.shox.netnomer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView uzmobile,beeline,ucell,mobiuz;
    TextView ilova_haqda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beeline=findViewById(R.id.beeline_id);
        uzmobile=findViewById(R.id.uzmobile_id);
        ucell=findViewById(R.id.ucell_id);
        mobiuz=findViewById(R.id.mobiuz_id);
        ilova_haqda=findViewById(R.id.ilova_haqida);

        uzmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UzmobileActivity.class));
            }
        });
        beeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BeelineActivity.class));
            }
        });
        ucell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UcellActivity.class));
            }
        });
        mobiuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MobiuzActivity.class));
            }
        });

        ilova_haqda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // custom dialog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.dialog_tittle);
                text.setText("Ilova haqida");


                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_button1);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }
}