package com.example.trenbokids;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.trenbokids.databinding.ActivityNavegadorBinding;

import java.util.List;

public class Navegador extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavegadorBinding binding;
    Button button;
    Button button2;
    Button button4, button3;
    TextView textTrembo;
    ImageView imagenReset;
    TextView textView24;
    TextView textView25;
    TextView textView26;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavegadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<SquatScore> squatScoreList;
        List<BenchScore> benchScoreList;
        List<ScoreQuiz> scoreQuizList;
        List<Macro> macroList;
        List<ArnoldScore> joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego;
        List<Comprado> brooooooooo;

        imagenReset = findViewById(R.id.imagenReset);

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        textTrembo = findViewById(R.id.textTrembo);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        squatScoreList = appDatabase.daoSquatScore().obtenerSScores();
        benchScoreList = appDatabase.daoBenchScore().obtenerBScores();
        scoreQuizList = appDatabase.daoScoreQuiz().obtenerQScores();
        macroList = appDatabase.daoMacros().obtenerMacros();
        brooooooooo = appDatabase.daoComprado().obtenerComprados();
        joder_aratz_no_puede_costar_tanto_añadir_mas_puntos_mas_he_modificado_ya_89_clases_para_añadir_un_minijuego = appDatabase.daoArnoldScore().obtenerAnosScores();
        int scoreSquat = 0;

        int scoreBench = 0;
        int scoreQuiz = 0;
        int calo = 0;
        int prote = 0;
        int car = 0;
        int lithuanian_bussy=0;
        int compra_resta = 0;
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
        if(brooooooooo.isEmpty()){

        }else{
            for (int i = 0; i < brooooooooo.size() ; i++) {
                compra_resta = compra_resta + brooooooooo.get(i).resta;

            }
        }
        int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy - compra_resta;
        textTrembo.setText("TREMBOPUNTOS : "+scoreult);

        if(macroList.isEmpty()){

        }else{
            calo = macroList.get(0).calorias;
            prote = macroList.get(0).proteinas;
            car = macroList.get(0).carbos;
        }

        textView24.setText(String.valueOf(calo));
        textView25.setText(String.valueOf(prote));
        textView26.setText(String.valueOf(car));

        setSupportActionBar(binding.appBarNavegador.toolbar);
       // binding.appBarNavegador.fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //                 .setAction("Action", null).show();
        //    }
        //});
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navegador);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        imagenReset.setOnClickListener(v ->
        {
            int lithuanian_bussy2=0;
            int compra_resta2 = 0;
            int scoreSquat2 = 0;
            int scoreBench2 = 0;
            int scoreQuiz2 = 0;
            if(appDatabase.daoSquatScore().obtenerSScores().isEmpty()){

            }else{
                scoreSquat2 = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
            }


            if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){

            }else{
                scoreBench2 = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
            }

            if(appDatabase.daoScoreQuiz().obtenerQScores().isEmpty()){

            }else{
                scoreQuiz2 = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score;
            }

            if(appDatabase.daoArnoldScore().obtenerAnosScores().isEmpty()){

            }else{
                lithuanian_bussy2 = appDatabase.daoArnoldScore().obtenerAnosScores().get(0).score;
            }
            if(appDatabase.daoComprado().obtenerComprados().isEmpty()){

            }else{
                for (int i = 0; i < appDatabase.daoComprado().obtenerComprados().size() ; i++) {
                    compra_resta2 = compra_resta2 + appDatabase.daoComprado().obtenerComprados().get(i).resta;

                }
            }
            int scoreult2 = scoreBench2+scoreSquat2+scoreQuiz2+lithuanian_bussy2- compra_resta2;
            textTrembo.setText("TREMBOPUNTOS : "+scoreult2);

            int calo2 = 0;
            int prote2 = 0;
            int car2 = 0;

            if(appDatabase.daoMacros().obtenerMacros().isEmpty()){ //

            }else{
                calo2 = appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                prote2 = appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                car2 = appDatabase.daoMacros().obtenerMacros().get(0).carbos;
            }

            textView24.setText(String.valueOf(calo2));
            textView25.setText(String.valueOf(prote2));
            textView26.setText(String.valueOf(car2));

        });
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                // In this section we are going
                // to control what fragment has
                // to be opened when you click on an icon
                if (id==R.id.nav_home){
                    Intent intent = new Intent(Navegador.this, Calculadora.class);
                    startActivity(intent);
                }else if (id==R.id.nav_gallery){
                    Intent intent = new Intent(Navegador.this, Mapa.class);
                    startActivity(intent);
                }else if (id==R.id.nav_slideshow){
                    Intent intent = new Intent(Navegador.this, discount_shop.class);
                    startActivity(intent);

                }
                int lithuanian_bussy=0;
                int compra_resta = 0;
                int scoreSquat = 0;
                int scoreBench = 0;
                int scoreQuiz = 0;
                if(appDatabase.daoSquatScore().obtenerSScores().isEmpty()){

                }else{
                    scoreSquat = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
                }


                if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){

                }else{
                    scoreBench = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
                }

                if(appDatabase.daoScoreQuiz().obtenerQScores().isEmpty()){

                }else{
                    scoreQuiz = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score;
                }
                if(brooooooooo.isEmpty()){

                }else{
                    for (int i = 0; i < brooooooooo.size() ; i++) {
                        compra_resta = compra_resta + brooooooooo.get(i).resta;

                    }
                }
                int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy - compra_resta; // TODO NO FUCKING WAY YOURE DOING IT TIHS WAY MY BROTHER IN JERUSALEM MADE A FINAL VARIABLE ABOVE ACCESIBLE FROM EVERY FUNCTIO NAND OVERWRITE IT
                textTrembo.setText("TREMBOPUNTOS : "+scoreult);

                int calo = 0;
                int prote = 0;
                int car = 0;

                if(appDatabase.daoMacros().obtenerMacros().isEmpty()){

                }else{
                    calo = appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                    prote = appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                    car = appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                }

                textView24.setText(String.valueOf(calo));
                textView25.setText(String.valueOf(prote));
                textView26.setText(String.valueOf(car));



                return true;
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navegador.this, SquatGame.class);
                startActivity(intent);
                int lithuanian_bussy=0;
                int compra_resta = 0;
                int scoreSquat = 0;
                int scoreBench = 0;
                int scoreQuiz = 0;
                if(appDatabase.daoSquatScore().obtenerSScores().isEmpty()){

                }else{
                    scoreSquat = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
                }


                if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){

                }else{
                    scoreBench = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
                }

                if(appDatabase.daoScoreQuiz().obtenerQScores().isEmpty()){

                }else{
                    scoreQuiz = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score;
                }
                if(brooooooooo.isEmpty()){

                }else{
                    for (int i = 0; i < brooooooooo.size() ; i++) {
                        compra_resta = compra_resta + brooooooooo.get(i).resta;

                    }
                }
                int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy - compra_resta;
                textTrembo.setText("TREMBOPUNTOS : "+scoreult);

                int calo = 0;
                int prote = 0;
                int car = 0;

                if(appDatabase.daoMacros().obtenerMacros().isEmpty()){

                }else{
                    calo = appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                    prote = appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                    car = appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                }

                textView24.setText(String.valueOf(calo));
                textView25.setText(String.valueOf(prote));
                textView26.setText(String.valueOf(car));
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navegador.this, Banca.class);
                startActivity(intent);
                int lithuanian_bussy=0;
                int compra_resta = 0;
                int scoreSquat = 0;
                int scoreBench = 0;
                int scoreQuiz = 0;
                if(appDatabase.daoSquatScore().obtenerSScores().isEmpty()){

                }else{
                    scoreSquat = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
                }


                if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){

                }else{
                    scoreBench = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
                }

                if(appDatabase.daoScoreQuiz().obtenerQScores().isEmpty()){

                }else{
                    scoreQuiz = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score;
                }
                if(brooooooooo.isEmpty()){

                }else{
                    for (int i = 0; i < brooooooooo.size() ; i++) {
                        compra_resta = compra_resta + brooooooooo.get(i).resta;

                    }
                }
                int scoreult = scoreBench+scoreSquat+scoreQuiz + lithuanian_bussy - compra_resta;
                textTrembo.setText("TREMBOPUNTOS : "+scoreult);

                int calo = 0;
                int prote = 0;
                int car = 0;

                if(appDatabase.daoMacros().obtenerMacros().isEmpty()){

                }else{
                    calo = appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                    prote = appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                    car = appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                }

                textView24.setText(String.valueOf(calo));
                textView25.setText(String.valueOf(prote));
                textView26.setText(String.valueOf(car));
            }
        });
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navegador.this, gnome_deadlift.class);
                startActivity(intent);

            }
        });


        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navegador.this, GymQuiz.class);
                startActivity(intent);
                int lithuanian_bussy=0;
                int compra_resta = 0;
                int scoreSquat = 0;
                int scoreBench = 0;
                int scoreQuiz = 0;
                if(appDatabase.daoSquatScore().obtenerSScores().isEmpty()){

                }else{
                    scoreSquat = appDatabase.daoSquatScore().obtenerSScores().get(0).score;
                }


                if(appDatabase.daoBenchScore().obtenerBScores().isEmpty()){

                }else{
                    scoreBench = appDatabase.daoBenchScore().obtenerBScores().get(0).score;
                }

                if(appDatabase.daoScoreQuiz().obtenerQScores().isEmpty()){

                }else{
                    scoreQuiz = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score;
                }
                if(brooooooooo.isEmpty()){

                }else{
                    for (int i = 0; i < brooooooooo.size() ; i++) {
                        compra_resta = compra_resta + brooooooooo.get(i).resta;

                    }
                }
                int scoreult = scoreBench+scoreSquat+scoreQuiz +lithuanian_bussy - compra_resta; // 7 score result gaw dam CEO of hardcodding????
                textTrembo.setText("TREMBOPUNTOS : "+scoreult);
                int calo = 0;
                int prote = 0;
                int car = 0;

                if(appDatabase.daoMacros().obtenerMacros().isEmpty()){

                }else{
                    calo = appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                    prote = appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                    car = appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                }

                textView24.setText(String.valueOf(calo));
                textView25.setText(String.valueOf(prote));
                textView26.setText(String.valueOf(car));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegador, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navegador);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}