package com.example.para_yakala_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

ImageView play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btnBasla);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GamePlayScreen.class);
                startActivity(intent);
            }
        });
        play= (ImageView)findViewById(R.id.button_play);
        final MediaPlayer voice= MediaPlayer.create(MainActivity.this, R.raw.appsound);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voice.isPlaying()){
                    voice.pause();
                    play.setBackgroundResource(R.drawable.play);
                }

                else {
                    voice.start();
                    play.setBackgroundResource(R.drawable.pause);
                }
            }
        });

    }

}