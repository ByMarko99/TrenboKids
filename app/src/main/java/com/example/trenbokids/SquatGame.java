package com.example.trenbokids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class SquatGame extends AppCompatActivity {

    ImageView squatimage, sweat1, sweat2;
    MediaPlayer mediaPlayer;
    TextView reps_text, harder_text;
    Boolean check = false;
    Boolean check2 = true;
    View.OnClickListener initialClickListener;
    Animation animShake;
    int i, rep_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squatgame);
        reps_text = findViewById(R.id.reps);
        harder_text = findViewById(R.id.harder);
        sweat1 = findViewById(R.id.sweat1);
        sweat2 = findViewById(R.id.sweat2);
        List<SquatScore> squatScoreList;

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        squatScoreList = appDatabase.daoSquatScore().obtenerSScores();

        if (squatScoreList.isEmpty()) {
            reps_text.setText("Reps: 0");
            SquatScore b = new SquatScore(0, 0);
            appDatabase.daoSquatScore().InsertarSScores(b);
        } else {
            int score;
            score = squatScoreList.get(0).score;

            reps_text.setText("Reps: " + Integer.toString(score));
        }

        Random r = new Random();
        int low = 1;
        i = 0;
        int high = 12; // 3
        squatimage = findViewById(R.id.upsquat);
        animShake = AnimationUtils.loadAnimation(this, R.anim.shake); // ESTA ES LA ANIMACION DE SHAKE

        initialClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = r.nextInt(high - low) + low;

                if (!check) {
                    squatimage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.squat_down_removebg_preview));
                    sweat1.setVisibility(View.VISIBLE);
                    sweat2.setVisibility(View.VISIBLE);


                    check = true;

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (check2) {
                                handleSquatClick(result);
                            }
                        }
                    }, 50);
                } else {


                    sweat1.setVisibility(View.INVISIBLE);
                    sweat2.setVisibility(View.INVISIBLE);
                    harder_text.setTextColor(Color.parseColor("#808080"));
                    squatimage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.squat_up_removebg_preview));
                    check = false;
                    check2 = true;
                    rep_count++;
                    int score;
                    score = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
                    int suma;
                    suma = score + 1;
                    appDatabase.daoSquatScore().updateSScore(0, suma);
                    reps_text.setText("Reps: " + String.valueOf(suma));
                }
            }
        };

        squatimage.setOnClickListener(initialClickListener);


    }

    private void handleSquatClick(int result) {
        squatimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check2) {
                    return;
                }
                squatimage.startAnimation(animShake);
                harder_text.startAnimation(animShake);
                harder_text.setTextColor(getResources().getColor(R.color.red));
                Random t = new Random();
                int orgasm = t.nextInt(5 - 1) + 1;
                if (orgasm == 3) {
                    if (mediaPlayer != null) {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                        {
                            @Override
                            public void onCompletion(MediaPlayer mp)
                            {
                                mediaPlayer = MediaPlayer.create(SquatGame.this, R.raw.puffi);
                                mediaPlayer.start();
                            }
                        });
                    } else {
                        mediaPlayer = MediaPlayer.create(SquatGame.this, R.raw.puffi);
                        mediaPlayer.start();
                    }
                    harder_text.setText("ARRIBA!");


                } else if (orgasm == 2) {
                    if (mediaPlayer != null) {

                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                        {
                            @Override
                            public void onCompletion(MediaPlayer mp)
                            {
                                mediaPlayer = MediaPlayer.create(SquatGame.this, R.raw.puffi);
                                mediaPlayer.start();
                            }
                        });

                    }else{
                        mediaPlayer = MediaPlayer.create(SquatGame.this, R.raw.puffi);
                        mediaPlayer.start();                    }
                    harder_text.setText("HARDER!!!!!");
                }
                if (i == result) {
                    squatimage.setOnClickListener(initialClickListener);
                    i = 0;
                    check2 = false;

                    squatimage.clearAnimation();
                    harder_text.clearAnimation();

                    return;
                }
                i++;
            }
        });
    }
}
