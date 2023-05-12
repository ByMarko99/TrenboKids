package com.example.trenbokids;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class Banca extends AppCompatActivity {
    AnimationDrawable rocketAnimation;
    AnimationDrawable rocketAnimation2;
    TextView textViewDescripcion;
    ImageView imageViewInfo;
    TextView textViewAnimos;

    int i=0;
    int a=0;

    TextView reps2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banca);

        List<BenchScore> benchScoreList;

        reps2= findViewById(R.id.reps2);

        ImageView rocketImage = (ImageView) findViewById(R.id.imageViewBanca);
        rocketImage.setBackgroundResource(R.drawable.animacionbajar);
        rocketAnimation2 = (AnimationDrawable) rocketImage.getBackground();
        textViewDescripcion = (TextView) findViewById(R.id.textViewDescripcion2);
        imageViewInfo = findViewById(R.id.imageViewInfo);
        textViewAnimos = findViewById(R.id.textViewAnimos);


        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        benchScoreList = appDatabase.daoBenchScore().obtenerBScores();

        if(benchScoreList.isEmpty()){
            reps2.setText("Reps: 0");
            BenchScore b = new BenchScore(0,0);
            appDatabase.daoBenchScore().InsertarBScores(b);
        }else {
            int score;
            score = benchScoreList.get(0).score;

            reps2.setText("Reps: "+Integer.toString(score));
        }

        imageViewInfo.setOnClickListener(v ->
        {
            reps2.setText("Que es el Press Banca ?");
            textViewDescripcion.setVisibility(View.VISIBLE);
            rocketImage.setVisibility(View.INVISIBLE);
            textViewAnimos.setVisibility(View.INVISIBLE);

        });

        textViewDescripcion.setOnClickListener(v ->
        {
            textViewDescripcion.setVisibility(View.INVISIBLE);
            rocketImage.setVisibility(View.VISIBLE);
            textViewAnimos.setVisibility(View.VISIBLE);
            if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){
                reps2.setText("Reps: 0");
                BenchScore b = new BenchScore(0,0);
                appDatabase.daoBenchScore().InsertarBScores(b);
            }else {
                int score;
                score = appDatabase.daoBenchScore().obtenerBScores().get(0).score;

                reps2.setText("Reps: "+Integer.toString(score));
            }
        });

        rocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    rocketImage.setBackgroundResource(R.drawable.animacionbajar);
                    rocketAnimation2 = (AnimationDrawable) rocketImage.getBackground();
                    rocketAnimation2.start();
                    i++;
                }else{
                    if(a==5){
                        rocketImage.setBackgroundResource(R.drawable.animacionsubir);
                        rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
                        rocketAnimation.start();
                        textViewAnimos.setVisibility(View.VISIBLE);
                        int b = (int)(Math.random()*(4-0+1)+0);
                        int c = (int)(Math.random()*(4-0+1)+0);
                        if(b==0){
                            textViewAnimos.setText("¡¡¡ VAMOS !!!");
                            textViewAnimos.startAnimation(animShake);
                            if(c==0){
                                textViewAnimos.setTextColor(getResources().getColor(R.color.red));

                            }else{
                                textViewAnimos.setTextColor(getResources().getColor(R.color.black));
                            }
                        }else if(b==1){
                            textViewAnimos.setText("¡¡¡ MAS FUERTE !!!");
                            textViewAnimos.startAnimation(animShake);
                            if(c==0){
                                textViewAnimos.setTextColor(getResources().getColor(R.color.red));

                            }else{
                                textViewAnimos.setTextColor(getResources().getColor(R.color.black));
                            }
                        }else if(b==2){
                            textViewAnimos.setText("¡¡¡ TU PUEDES !!!");
                            textViewAnimos.startAnimation(animShake);
                            if(c==0){
                                textViewAnimos.setTextColor(getResources().getColor(R.color.red));

                            }else{
                                textViewAnimos.setTextColor(getResources().getColor(R.color.black));
                            }
                        }else if(b==3){
                            textViewAnimos.setText("¡¡¡ SIGUE !!!");
                            textViewAnimos.startAnimation(animShake);
                            if(c==0){
                                textViewAnimos.setTextColor(getResources().getColor(R.color.red));

                            }else{
                                textViewAnimos.setTextColor(getResources().getColor(R.color.black));
                            }
                        }else if(b==4){
                            textViewAnimos.setText("¡¡¡ HALAL SUS !!!");
                            textViewAnimos.startAnimation(animShake);
                            if(c==0){
                                textViewAnimos.setTextColor(getResources().getColor(R.color.red));

                            }else{
                                textViewAnimos.setTextColor(getResources().getColor(R.color.black));
                            }
                        }

                        a=0;
                        i=0;
                        int score;
                        score = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
                        int suma;
                        suma = score+1;
                        appDatabase.daoBenchScore().updateScore(0,suma);
                        reps2.setText("Reps: "+Integer.toString(suma));

                    }else{
                        rocketImage.startAnimation(animShake);
                        a++;
                    }
                }



            }
        });

    }
}