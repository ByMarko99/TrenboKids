package com.example.trenbokids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class discount_shop extends AppCompatActivity {

    TextView trenbopuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_shop);
        trenbopuntos = findViewById(R.id.trenbopuntos);

        List<SquatScore> squatScoreList;
        List<BenchScore> benchScoreList;
        List<ScoreQuiz> scoreQuizList;
        List<ArnoldScore> joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego;

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();


        squatScoreList = appDatabase.daoSquatScore().obtenerSScores();
        benchScoreList = appDatabase.daoBenchScore().obtenerBScores();
        scoreQuizList = appDatabase.daoScoreQuiz().obtenerQScores();
        joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego = appDatabase.daoArnoldScore().obtenerAnosScores();


        int scoreSquat = 0;

        int scoreBench = 0;
        int scoreQuiz = 0;
        int lithuanian_bussy = 0;

        if(squatScoreList.isEmpty()){

        }else{
            scoreSquat = squatScoreList.get(0).score;
        }

        if(joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego.isEmpty()){

        }else{
            lithuanian_bussy = joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego.get(0).score;
        }


        if(benchScoreList.isEmpty()){

        }else{
            scoreBench = benchScoreList.get(0).score;
        }

        if(scoreQuizList.isEmpty()){

        }else{
            scoreQuiz = scoreQuizList.get(0).score;
        }

        int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy;
        trenbopuntos.setText(String.valueOf(scoreult));
        GifImageView gifImageView = findViewById(R.id.gnomesarerealfuckoff);
        Glide.with(this).load(R.raw.furro).into(gifImageView);



        Animation animation = AnimationUtils.loadAnimation(this, R.anim.furro);
        gifImageView.startAnimation(animation);

        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the popup layout

                View popupView = LayoutInflater.from(discount_shop.this).inflate(R.layout.activity_soscaryuwu, null);
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(discount_shop.this, R.raw.hehehaw);
                mediaPlayer.start();
                // Create the popup window
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);

                // Show the popup window
                popupWindow.showAsDropDown(gifImageView);

                // Hide the popup after 1 second
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                }, 1000);

                // Make the jumpscare image visible
                ImageView popupImageView = popupView.findViewById(R.id.popupImageView);
                popupImageView.setVisibility(View.VISIBLE);
            }
        });
    }
}