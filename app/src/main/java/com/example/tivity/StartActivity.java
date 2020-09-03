package com.example.tivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    private Button loginButton;
    private ImageView logo_green;
    private ConstraintLayout greenBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();

        logo_green = (ImageView) findViewById(R.id.logo_green);
        loginButton = (Button) findViewById(R.id.loginButton);
        greenBackground = (ConstraintLayout) findViewById(R.id.greenBackground);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, LoginActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String> (logo_green, "tivityLogo");
                pairs[1] = new Pair<View, String> (greenBackground, "greenBackground");


                ActivityOptions options = ActivityOptions.
                        makeSceneTransitionAnimation(StartActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }

}
