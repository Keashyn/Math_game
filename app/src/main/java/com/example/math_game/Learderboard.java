package com.example.math_game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Learderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ImageButton back =
                findViewById(R.id.back);
        ListView Score =
                findViewById(R.id.scores);

        SimpleDatabase db;
        db = new SimpleDatabase(this);

        ArrayList<Integer> myArray;
        ArrayList<String> myName;
        ArrayList<String> ranking = new ArrayList<>();
        myArray = db.getAllScores();
        myName = db.getNameasWell();

        //Sorting it
        Collections.sort(myArray, Collections.reverseOrder());
        Collections.sort(myName, Collections.reverseOrder());

        int enumerator = 1;
        if(myArray.size()<5) {
            if(myArray.size()==0){
                ranking.add("No Highscore yet!");
            }else{
                int count = 0;
                for (int points : myArray) {
                    ranking.add(String.format(Locale.getDefault(), "%1$2s.  %2$3s ", enumerator,myName.get(count)+" : "+ points ));
                    enumerator++;
                    count++;
                }
                count = 0;
            }
        } else {
            for(int x = 0 ; x<5 ; x++){
                ranking.add(String.format(Locale.getDefault(), "%1$2s.  %2$3s ", enumerator,myName.get(x)+" : "+ myArray.get ( x ) ));
                enumerator++;
            }
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.leaderboard_view,ranking);
        Score.setAdapter(myAdapter);
        Score.setDivider(null);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}