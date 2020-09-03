package com.example.tivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class LoadingActivity extends AppCompatActivity {


    private ImageView logo_green;
    private ConstraintLayout greenBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getSupportActionBar().hide();

        logo_green = (ImageView) findViewById(R.id.logo_green);
        greenBackground = (ConstraintLayout) findViewById(R.id.greenBackground);



        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(3500);

                    Intent intent = new Intent(LoadingActivity.this, StartActivity.class);

                    /*

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(logo_green, "tivityLogo");
                    pairs[1] = new Pair<View, String>(greenBackground, "greenBackground");

                    ActivityOptions options = ActivityOptions.
                        makeSceneTransitionAnimation(LoadingActivity.this, pairs);

                    , options.toBundle()
                     */
                    startActivity(intent);

                    finish();

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        myThread.start();

    }
}





