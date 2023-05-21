package com.example.trenbokids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Comprado> compras;

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        Map<String, Button> n = new HashMap<>(); //Dynamic ID's hashmap fumada del siglo más abajo

        squatScoreList = appDatabase.daoSquatScore().obtenerSScores();
        benchScoreList = appDatabase.daoBenchScore().obtenerBScores();
        scoreQuizList = appDatabase.daoScoreQuiz().obtenerQScores();
        joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego = appDatabase.daoArnoldScore().obtenerAnosScores();

       compras = appDatabase.daoComprado().obtenerComprados();




        for (int i = 1; i <= 10; i++) {


            n.put("button_item_" + i + "_buy", (Button) findViewById(getResources().getIdentifier("button_item_" + i + "_buy", "id", getPackageName())));

            int finalI = i;


            n.get("button_item_" + i + "_buy").setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TextView puntos_dynamic = (TextView) findViewById(getResources().getIdentifier("text_item_" + finalI + "_price", "id", getPackageName()));
                    int indexOfSpace = puntos_dynamic.getText().toString().indexOf(' ');

                    if(Integer.parseInt(puntos_dynamic.getText().toString().substring(0, indexOfSpace)) > Integer.parseInt( trenbopuntos.getText().toString())){
                        Toast.makeText(discount_shop.this, Html.fromHtml("<font color='#FF0000' >" + "No tienes suficientes puntos" + "</font>"), Toast.LENGTH_LONG).show();

                    }else{
                        if(finalI == 8){
                            MediaPlayer mediaPlayer;
                            mediaPlayer = MediaPlayer.create(discount_shop.this, R.raw.luigi);
                            mediaPlayer.start();
                        }else if(finalI == 10){
                            MediaPlayer mediaPlayer;
                            mediaPlayer = MediaPlayer.create(discount_shop.this, R.raw.jojo);
                            mediaPlayer.start();
                        }else{


                        MediaPlayer mediaPlayer;
                        mediaPlayer = MediaPlayer.create(discount_shop.this, R.raw.compra);
                        mediaPlayer.start();
                        }
                        Toast.makeText(discount_shop.this, Html.fromHtml("<font color='#008000' >" + "Comprado!" + "</font>"), Toast.LENGTH_LONG).show();
                        n.get("button_item_" + finalI  + "_buy").setBackgroundColor(Color.rgb(0,100,0));
                        n.get("button_item_" + finalI  + "_buy").setEnabled(false);
                        trenbopuntos.setText(String.valueOf(Integer.parseInt(trenbopuntos.getText().toString())-Integer.parseInt(puntos_dynamic.getText().toString().substring(0, indexOfSpace))));
                        appDatabase.daoComprado().updateComprado(finalI, true, Integer.parseInt(puntos_dynamic.getText().toString().substring(0, indexOfSpace)));

                    }


                }
            });
        } //Onclick for each button
        if (compras.isEmpty()){
            for (int i = 1; i <= 10; i++) {
                appDatabase.daoComprado().InsertarComprados(new Comprado(i, false, 0));

            }
        }else {
            for (int i = 1; i <= 10; i++) {
                if(compras.get(i-1).comprado){
                    n.get("button_item_" + i  + "_buy").setBackgroundColor(Color.rgb(0,100,0));
                    n.get("button_item_" + i  + "_buy").setEnabled(false);

                }

            }
        }


        int scoreSquat = 0;
        int lithuanian_bussy=0;
        int compra_resta = 0;
        int scoreBench = 0;
        int scoreQuiz = 0;

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
        if(compras.isEmpty()){

        }else{
            for (int i = 0; i < compras.size() ; i++) {
                compra_resta = compra_resta + compras.get(i).resta;

            }
        }
        int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy - compra_resta ;
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