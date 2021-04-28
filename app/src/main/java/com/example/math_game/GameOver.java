package com.example.math_game;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;


public class GameOver extends AppCompatActivity {
    private SimpleDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Bounce interpolator = new Bounce(0.2, 20);
        bonce.setInterpolator(interpolator);

        ConstraintLayout ConsLayout =
                findViewById(R.id.croslau);
        final Button back =
                findViewById(R.id.backo);
        final Button leaderboard =
                findViewById(R.id.Leaderboard);
        final Button playagain =
                findViewById(R.id.plei);

        TextView current_score =
                findViewById(R.id.Current);
        TextView HIGHEST_score =
                findViewById(R.id.HIGH);

        ConsLayout.getBackground().setAlpha(150);

        back.startAnimation(bonce);
        leaderboard.startAnimation(bonce);
        playagain.startAnimation(bonce);

        SharedPreferences preferences = getSharedPreferences("Pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int Score = preferences.getInt("SCORE",0);
        String PlayerName = preferences.getString("Player_name","Player 1");
        current_score.setText(""+Score);

        db = new SimpleDatabase(this);
        db.addScore(PlayerName,Score);
        db.close();

        ArrayList<Integer> myArray;
        myArray = db.getAllScores();
        Collections.sort(myArray, Collections.reverseOrder());

        HIGHEST_score.setText(""+myArray.get(0));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                back.startAnimation(bonce);
                GameAct.fa.finish();
                finish();
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                leaderboard.startAnimation(bonce);
                GameAct.fa.finish();
                finish();
                Intent maklo = new Intent(GameOver.this, Learderboard.class);
                startActivity(maklo);
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                playagain.startAnimation(bonce);
                GameAct.fa.finish();
                Intent Plays = new Intent(GameOver.this,GameAct.class);
                startActivity(Plays);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
