package com.example.math_game;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Difficulties extends AppCompatActivity {
    public int Difficulties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulities);

        ImageButton back =
                findViewById(R.id.back);
        final Button Easy =
                findViewById(R.id.easy);
        final Button Medium =
                findViewById(R.id.medium);
        final Button Hard =
                findViewById(R.id.hard);
        final TextView diff =
                findViewById(R.id.textViewso);

        Difficulties = 1;


        SharedPreferences preferences = getSharedPreferences("Pref",MODE_PRIVATE);
        int difficulties = preferences.getInt("DIFFICULTIES",1);
        if(difficulties == 1){
            diff.setText("Easy");
        }else if(difficulties == 2){
            diff.setText("Medium");
        }else if(difficulties == 3){
            diff.setText("Hard");
        }else{
            diff.setText("Easy");
        }

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        Easy.setAnimation(bonce);
        Medium.setAnimation(bonce);
        Hard.setAnimation(bonce);

        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Easy.startAnimation(bonce);
                Difficulties = 1;
                diff.setText("Easy");
                Toast.makeText(Difficulties.this, "Set on Easy.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Medium.startAnimation(bonce);
                Difficulties = 2;

                diff.setText("Medium");
                Toast.makeText(Difficulties.this, "Set on Medium.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Hard.startAnimation(bonce);
                Difficulties = 3;

                diff.setText("Hard");
                Toast.makeText(Difficulties.this, "Set on Hard.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("DIFFICULTIES",Difficulties);
        editor.apply();
    }
}
