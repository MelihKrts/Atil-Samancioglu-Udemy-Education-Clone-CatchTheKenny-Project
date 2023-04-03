package com.example.para_yakala_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GamePlayScreen extends AppCompatActivity {

    ImageView play;

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen);
        play= (ImageView)findViewById(R.id.button_play);
        final MediaPlayer voice= MediaPlayer.create(GamePlayScreen.this, R.raw.appsound);
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

        timeText = (TextView) findViewById(R.id.timeText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        score = 0;

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Süre: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Süre Doldu");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(GamePlayScreen.this);
                alert.setTitle("Tekrar Başla");
                alert.setIcon(R.drawable.res);
                alert.setMessage("Oyuna Hazır Mısın");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Process.killProcess(Process.myPid());
                        GamePlayScreen.super.onDestroy();


                    }
                });
                alert.show();
            }
        }.start();

    }

    public void pointsScore(View view) {

        score++;

        scoreText.setText("Skor: " + score);
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 300);
            }
        };

        handler.post(runnable);
    }

}