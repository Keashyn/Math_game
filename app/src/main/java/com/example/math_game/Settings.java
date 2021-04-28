package com.example.math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ImageButton Back =
                findViewById(R.id.backt);
        final ImageButton Bg_voice =
                findViewById(R.id.bg_voice);
        final ImageButton Sfx_voice =
                findViewById(R.id.sfx_voice);
        SeekBar Bg_sound =
                findViewById(R.id.bg_sound);
        SeekBar Sfx_sound =
                findViewById(R.id.sfx_sound);
        final EditText Player_name =
                findViewById(R.id.Pname);
        Button Pname_apply =
                findViewById(R.id.PnameApply);
        final RadioGroup Choice =
                findViewById(R.id.buble_choice);
        Button Choice_apply =
                findViewById(R.id.Choice_apply);
        final Animation bonce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        SharedPreferences preferences = getSharedPreferences("Pref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        String Player_Name = preferences.getString("Player_name","Player 1");
        int BG_sound = preferences.getInt("BG_SOUND",100);
        int SFX_sound = preferences.getInt("SFX_SOUND",100);
        final boolean[] BG_voice = {preferences.getBoolean("BG_VOICE", true)};
        final boolean[] SFX_voice = {preferences.getBoolean("SFX_VOICE", true)};
        int choice = preferences.getInt("CHOICE",0);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bounce interpolator = new Bounce(0.3, 25);
                bonce.setInterpolator(interpolator);
                Back.startAnimation(bonce);
                MainActivity.fa.finish();
                finish();
                startActivity(new Intent(Settings.this,MainActivity.class));
            }
        });

        if(BG_voice[0]){
            Bg_voice.setImageResource(R.drawable.voice);
        }else{
            Bg_voice.setImageResource(R.drawable.voiceoff);
        }
        editor.putBoolean("BG_VOICE", BG_voice[0]);
        editor.apply();
        Bg_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BG_voice[0]){
                    BG_voice[0] =false;
                    Bg_voice.setImageResource(R.drawable.voiceoff);
                    Toast.makeText(Settings.this, "Background voice is Muted.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    BG_voice[0] =true;
                    Bg_voice.setImageResource(R.drawable.voice);
                    Toast.makeText(Settings.this, "Background voice is On.",
                            Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("BG_VOICE", BG_voice[0]);
                editor.apply();
            }
        });

        if(SFX_voice[0]){
            Sfx_voice.setImageResource(R.drawable.voice);
        }else{
            Sfx_voice.setImageResource(R.drawable.voiceoff);
        }
        Sfx_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SFX_voice[0]){
                    Sfx_voice.setImageResource(R.drawable.voiceoff);
                    SFX_voice[0] =false;
                    Toast.makeText(Settings.this, "Sound Effect voice is Muted.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Sfx_voice.setImageResource(R.drawable.voice);
                    SFX_voice[0] =true;
                    Toast.makeText(Settings.this, "Sound Effect voice is On.",
                            Toast.LENGTH_SHORT).show();
                }
                editor.putBoolean("SFX_VOICE", SFX_voice[0]);
                editor.apply();
            }
        });

        Bg_sound.setProgress(BG_sound);
        Bg_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int number;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                number = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("BG_SOUND",number);
                editor.apply();
            }
        });

        Sfx_sound.setProgress(SFX_sound);
        Sfx_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int number;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                number = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("SFX_SOUND",number);
                editor.apply();
            }
        });


        Player_name.setText(Player_Name);
        Pname_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Applied.",
                        Toast.LENGTH_SHORT).show();
                editor.putString("Player_name",Player_name.getText().toString());
                editor.apply();
            }
        });

        if(choice==0){
            RadioButton normalBub =
                    findViewById(R.id.but1);
            normalBub.setChecked(true);
        }else if(choice == 1){
            RadioButton normalBub =
                    findViewById(R.id.but2);
            normalBub.setChecked(true);
        }else if(choice == 2){
            RadioButton normalBub =
                    findViewById(R.id.but3);
            normalBub.setChecked(true);
        }else {
            RadioButton normalBub =
                    findViewById(R.id.but4);
            normalBub.setChecked(true);
        }


        Choice_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnid= Choice.getCheckedRadioButtonId();
                View Viewer = Choice.findViewById(btnid);
                int checkedId = Choice.indexOfChild(Viewer);
                editor.putInt("CHOICE",checkedId);
                editor.apply();
                Toast.makeText(Settings.this, "Style Applied.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(Settings.this, "Player Name and Style are not saved.",
                Toast.LENGTH_SHORT).show();
    }

}
