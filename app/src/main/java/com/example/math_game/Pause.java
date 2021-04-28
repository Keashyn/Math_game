package com.example.math_game;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class Pause extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        ConstraintLayout background =
                findViewById(R.id.backgr);
        final ImageButton Back =
                findViewById(R.id.bekhome);
        final Button Contine =
                findViewById(R.id.continues);

        background.getBackground().setAlpha(150);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        Back.setAnimation(bonce);
        Contine.setAnimation(bonce);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Back.startAnimation(bonce);
                GameAct.fa.finish();
                finish();
            }
        });

        Contine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Contine.startAnimation(bonce);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
    }
}
