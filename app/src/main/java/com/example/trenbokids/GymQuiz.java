package com.example.trenbokids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymQuiz extends AppCompatActivity {

    Spinner spinerrespuesta;
    TextView textView18;
    Button buttonSisNon;
    TextView textView19;
    ImageView imagenRespuesta;
    TextView textViewRespuesta;
    TextView textView21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_quiz);

        List<PalabrasFit> palabrasFitList;
        List<PalabrasFit> palabrasFitList2;
        List<PalabraDelDia> palabraDelDiaList;
        List<PalabraDelDia> palabraDelDiaList2;
        List<IntentosCompletado> intentosCompletadoList;
        List<ScoreQuiz> scoreQuizList;
        ArrayList<String> nombres = new ArrayList<>();


        spinerrespuesta = findViewById(R.id.spinerrespuesta);
        textView18= findViewById(R.id.textView18);
        textView19= findViewById(R.id.textView19);
        textView21= findViewById(R.id.textView21);
        imagenRespuesta = findViewById(R.id.imagenRespuesta);
        textViewRespuesta = findViewById(R.id.textViewRespuesta);
        buttonSisNon = findViewById(R.id.botonSisNon);


        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        palabrasFitList = appDatabase.daoPalabrasFit().obtenerPalabras();
        intentosCompletadoList = appDatabase.daoIntentosCompletado().obtenerintentos();
        scoreQuizList = appDatabase.daoScoreQuiz().obtenerQScores();
        if(scoreQuizList.isEmpty()){
            ScoreQuiz nuevo = new ScoreQuiz(0,0);
            appDatabase.daoScoreQuiz().InsertarQScores(nuevo);
        }

        if(palabrasFitList.isEmpty()){
            PalabrasFit primera = new PalabrasFit(0,"Press banca","Este ejercicio físico es uno de los tres realizados en powerlifting, y también se utiliza en el culturismo como un ejercicio para el pecho, (principalmente músculos pectorales) el tríceps y el fascículo anterior del deltoides.");
            PalabrasFit segunda = new PalabrasFit(1,"Sentadilla"," Movimiento que se inicia de pie, mirando al frente y con la espalda recta, mientras los pies se separan del ancho de los hombros. La barra utilizada debe situarse justo encima de los trapecios, no debe apoyarse en el cuello");
            PalabrasFit tercera = new PalabrasFit(2,"Ronnie Coleman","Ganador del título de Mister Olympia durante ocho años consecutivos. Es considerado por muchos atletas y periodistas como el mejor culturista de toda la historia de este deporte,Además de sus ocho títulos en Mr. Olympia, ostentaba el récord de más victorias como profesional de la IFBB con 26 títulos, hasta que Dexter Jackson lo batió, otra de las cosas que lo hizo conocido son sus famosas frases como: <<Yeah Buddy>>, o <<Lightweight>>");
            PalabrasFit cuarta = new PalabrasFit(3,"Chris Bumsted","Actual campeón de Mr. Olympia Classic Physique, habiendo ganado la competición en 2019, 2020, 2021 y 2022. También fue subcampeón en 2017 y 2018. Conocido por sus apodos «CBum» o «Daddy CBum»,");
            PalabrasFit quinta = new PalabrasFit(4,"Jay Cutler"," Fisicoculturista estadounidense, miembro profesional de la Federación Internacional de Fisicoculturismo (IFBB).Actualmente radica en Las Vegas, Nevada. Mr Olympia 2006, 2007, 2009 y 2010. <<Twelve reps>>");
            PalabrasFit sexta = new PalabrasFit(5,"Arnold Schwarzenegger","Es un actor, empresario, político y exfisicoculturista profesional austroestadounidense. Ejerció como trigésimo octavo gobernador del Estado de California en dos mandatos desde 2003 hasta 2011. Su patrimonio neto es de 450 millones de dólares");
            PalabrasFit septima = new PalabrasFit(6,"Curl de biceps","Ejercicios que implican la ejercitación de dicho músculo. Como el bíceps trabaja en el giro de muñeca o contracción del brazo, es fácil inducir que los diferentes tipos de curls incluyan flexiones de brazos así como giros de muñeca.");
            PalabrasFit octava = new PalabrasFit(7,"Peso Muerto"," Ejercicio con pesas que consiste en levantar una barra desde el suelo hasta la cintura. El peso muerto es uno de los tres movimientos que forman parte del powerlifting. El récord mundial de peso muerto a más peso absoluto levantado lo tiene el strongman Zydrunas Zavickas con un peso muerto de 524 kg");
            PalabrasFit novena = new PalabrasFit(8,"Hip Thrust","Es un ejercicio que implica una hiperextensión de cadera mediante una elevación y retroversión máxima de la pelvis.");
            PalabrasFit decima = new PalabrasFit(9,"Press Militar","Ejercicio compuesto en el que participa todo el cuerpo. No solo los brazos y hombros, que empujan y levantan el peso. También las piernas y el tronco ejercen presión de forma isométrica.");


            appDatabase.daoPalabrasFit().InsertarPalabras(primera);
            appDatabase.daoPalabrasFit().InsertarPalabras(segunda);
            appDatabase.daoPalabrasFit().InsertarPalabras(tercera);
            appDatabase.daoPalabrasFit().InsertarPalabras(cuarta);
            appDatabase.daoPalabrasFit().InsertarPalabras(quinta);
            appDatabase.daoPalabrasFit().InsertarPalabras(sexta);
            appDatabase.daoPalabrasFit().InsertarPalabras(septima);
            appDatabase.daoPalabrasFit().InsertarPalabras(octava);
            appDatabase.daoPalabrasFit().InsertarPalabras(novena);
            appDatabase.daoPalabrasFit().InsertarPalabras(decima);


        }

        palabrasFitList2 = appDatabase.daoPalabrasFit().obtenerPalabras();
        palabraDelDiaList = appDatabase.daoPalabraDelDia().obtenerPalabrasDia();
        Date d = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());
        String fec = s.toString();

        if (palabraDelDiaList.isEmpty()){
            int b = (int)(Math.random()*(9-0+1)+0);
            PalabraDelDia p = new PalabraDelDia(fec,b);
            appDatabase.daoPalabraDelDia().InsertarPalabraDelDia(p);
        }

        palabraDelDiaList2 = appDatabase.daoPalabraDelDia().obtenerPalabrasDia();
        for (int i = 0; i < palabraDelDiaList2.size(); i++) {
            if(fec.equals(palabraDelDiaList2.get(i).fecha)){

            }else{
                appDatabase.daoPalabraDelDia().borrarPalabraDias();
                int b = (int)(Math.random()*(9-0+1)+0);
                PalabraDelDia p = new PalabraDelDia(fec,b);
                appDatabase.daoPalabraDelDia().InsertarPalabraDelDia(p);
            }
        }

        for (int i = 0; i < palabrasFitList2.size(); i++) {
            nombres.add(palabrasFitList2.get(i).nombre);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,nombres);
        spinerrespuesta.setAdapter(adapter);


        for (int i = 0; i < palabrasFitList2.size(); i++) {
            if(palabrasFitList2.get(i).id == palabraDelDiaList2.get(0).idPalabra){
                textView18.setText(palabrasFitList2.get(i).descripcion);
            }
        }

        if(intentosCompletadoList.isEmpty()){
            IntentosCompletado inte = new IntentosCompletado(fec,0,0);
            appDatabase.daoIntentosCompletado().InsertarIntentos(inte);
        }else{
            if(intentosCompletadoList.get(0).fecha.equals(fec)){

            }else{
                appDatabase.daoIntentosCompletado().borrarIntentos();
                IntentosCompletado inte = new IntentosCompletado(fec,0,0);
                appDatabase.daoIntentosCompletado().InsertarIntentos(inte);
            }

            if(intentosCompletadoList.get(0).completado==0){

            }else{
                imagenRespuesta.setVisibility(View.VISIBLE);
                textViewRespuesta.setVisibility(View.VISIBLE);
                String nombrefin = null;
                for (int i = 0; i < palabrasFitList2.size(); i++) {
                    if(palabrasFitList2.get(i).id == palabraDelDiaList2.get(0).idPalabra){
                        nombrefin=palabrasFitList2.get(i).nombre;
                    }
                }

                if(nombrefin.equals("Press banca")){
                    imagenRespuesta.setImageResource(R.drawable.banca);
                    textViewRespuesta.setText("La respuesta es Press Banca");
                } else if(nombrefin.equals("Sentadilla")){
                    imagenRespuesta.setImageResource(R.drawable.sentadilla);
                    textViewRespuesta.setText("La respuesta es Sentadilla");
                }else if(nombrefin.equals("Ronnie Coleman")){
                    imagenRespuesta.setImageResource(R.drawable.coleman);
                    textViewRespuesta.setText("La respuesta es Ronnie Coleman");
                }else if(nombrefin.equals("Chris Bumsted")){
                    imagenRespuesta.setImageResource(R.drawable.chris);
                    textViewRespuesta.setText("La respuesta es Chris Bumstead");
                }else if(nombrefin.equals("Jay Cutler")){
                    imagenRespuesta.setImageResource(R.drawable.jay);
                    textViewRespuesta.setText("La respuesta es Jay Cutler");
                }else if(nombrefin.equals("Arnold Schwarzenegger")){
                    imagenRespuesta.setImageResource(R.drawable.rnold);
                    textViewRespuesta.setText("La respuesta es Arnold Schwarzenegger");
                }else if(nombrefin.equals("Curl de biceps")){
                    imagenRespuesta.setImageResource(R.drawable.bic);
                    textViewRespuesta.setText("La respuesta es Curl de Biceps");
                }else if(nombrefin.equals("Peso Muerto")){
                    imagenRespuesta.setImageResource(R.drawable.pes);
                    textViewRespuesta.setText("La respuesta es Peso Muerto");
                }else if(nombrefin.equals("Hip Thrust")){
                    imagenRespuesta.setImageResource(R.drawable.hip);
                    textViewRespuesta.setText("La respuesta es Hip Thrust");
                }else if(nombrefin.equals("Press Militar")){
                    imagenRespuesta.setImageResource(R.drawable.mili);
                    textViewRespuesta.setText("La respuesta es Press Militar");
                }
                textView19.setVisibility(View.INVISIBLE);
                textView18.setVisibility(View.INVISIBLE);
                spinerrespuesta.setVisibility(View.INVISIBLE);
                buttonSisNon.setVisibility(View.INVISIBLE);
            }
        }



        buttonSisNon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String a = spinerrespuesta.getSelectedItem().toString();
                String nombre = "";
                int elid =palabraDelDiaList2.get(0).idPalabra;
                for (int i = 0; i < palabrasFitList2.size(); i++) {
                    if(elid==palabrasFitList2.get(i).id){
                        nombre = palabrasFitList2.get(i).nombre;
                    }
                }
                if(a.equals(nombre)){
                   imagenRespuesta.setVisibility(View.VISIBLE);
                   textViewRespuesta.setVisibility(View.VISIBLE);
                   if(a.equals("Press banca")){
                       imagenRespuesta.setImageResource(R.drawable.banca);
                       textViewRespuesta.setText("La respuesta es Press Banca");
                   } else if(a.equals("Sentadilla")){
                       imagenRespuesta.setImageResource(R.drawable.sentadilla);
                       textViewRespuesta.setText("La respuesta es Sentadilla");
                   }else if(a.equals("Ronnie Coleman")){
                       imagenRespuesta.setImageResource(R.drawable.coleman);
                       textViewRespuesta.setText("La respuesta es Ronnie Coleman");
                   }else if(a.equals("Chris Bumsted")){
                       imagenRespuesta.setImageResource(R.drawable.chris);
                       textViewRespuesta.setText("La respuesta es Chris Bumstead");
                   }else if(a.equals("Jay Cutler")){
                       imagenRespuesta.setImageResource(R.drawable.jay);
                       textViewRespuesta.setText("La respuesta es Jay Cutler");
                   }else if(a.equals("Arnold Schwarzenegger")){
                       imagenRespuesta.setImageResource(R.drawable.rnold);
                       textViewRespuesta.setText("La respuesta es Arnold Schwarzenegger");
                   }else if(a.equals("Curl de biceps")){
                       imagenRespuesta.setImageResource(R.drawable.bic);
                       textViewRespuesta.setText("La respuesta es Curl de Biceps");
                   }else if(a.equals("Peso Muerto")){
                       imagenRespuesta.setImageResource(R.drawable.pes);
                       textViewRespuesta.setText("La respuesta es Peso Muerto");
                   }else if(a.equals("Hip Thrust")){
                       imagenRespuesta.setImageResource(R.drawable.hip);
                       textViewRespuesta.setText("La respuesta es Hip Thrust");
                   }else if(a.equals("Press Militar")){
                       imagenRespuesta.setImageResource(R.drawable.mili);
                       textViewRespuesta.setText("La respuesta es Press Militar");
                   }
                   textView19.setVisibility(View.INVISIBLE);
                   textView18.setVisibility(View.INVISIBLE);
                   spinerrespuesta.setVisibility(View.INVISIBLE);
                   buttonSisNon.setVisibility(View.INVISIBLE);
                   appDatabase.daoIntentosCompletado().updateIntentos(fec,0,1);
                   imagenRespuesta.startAnimation(animScale);


                    textView21.setVisibility(View.VISIBLE);
                    textView21.setText("HAS GANADO 100 TREMBOPUNTOS");
                    int scorefinal = appDatabase.daoScoreQuiz().obtenerQScores().get(0).score +100;
                    appDatabase.daoScoreQuiz().updateScore(0,scorefinal);


                }
                else{

                    int contador = 0;
                    contador =appDatabase.daoIntentosCompletado().obtenerintentos().get(0).intentos +1;
                    appDatabase.daoIntentosCompletado().updateIntentos(fec,contador,0);
                    textView19.setText("Intentos Restantes: "+contador);
                }

                if(appDatabase.daoIntentosCompletado().obtenerintentos().get(0).intentos == 5){
                    imagenRespuesta.setVisibility(View.VISIBLE);
                    textViewRespuesta.setVisibility(View.VISIBLE);
                    textView21.setVisibility(View.VISIBLE);
                    String nombrefin = null;
                    for (int i = 0; i < palabrasFitList2.size(); i++) {
                        if(palabrasFitList2.get(i).id == palabraDelDiaList2.get(0).idPalabra){
                            nombrefin=palabrasFitList2.get(i).nombre;
                        }
                    }

                    if(nombrefin.equals("Press banca")){
                        imagenRespuesta.setImageResource(R.drawable.banca);
                        textViewRespuesta.setText("La respuesta es Press Banca");
                    } else if(nombrefin.equals("Sentadilla")){
                        imagenRespuesta.setImageResource(R.drawable.sentadilla);
                        textViewRespuesta.setText("La respuesta es Sentadilla");
                    }else if(nombrefin.equals("Ronnie Coleman")){
                        imagenRespuesta.setImageResource(R.drawable.coleman);
                        textViewRespuesta.setText("La respuesta es Ronnie Coleman");
                    }else if(nombrefin.equals("Chris Bumsted")){
                        imagenRespuesta.setImageResource(R.drawable.chris);
                        textViewRespuesta.setText("La respuesta es Chris Bumstead");
                    }else if(nombrefin.equals("Jay Cutler")){
                        imagenRespuesta.setImageResource(R.drawable.jay);
                        textViewRespuesta.setText("La respuesta es Jay Cutler");
                    }else if(nombrefin.equals("Arnold Schwarzenegger")){
                        imagenRespuesta.setImageResource(R.drawable.rnold);
                        textViewRespuesta.setText("La respuesta es Arnold Schwarzenegger");
                    }else if(nombrefin.equals("Curl de biceps")){
                        imagenRespuesta.setImageResource(R.drawable.bic);
                        textViewRespuesta.setText("La respuesta es Curl de Biceps");
                    }else if(nombrefin.equals("Peso Muerto")){
                        imagenRespuesta.setImageResource(R.drawable.pes);
                        textViewRespuesta.setText("La respuesta es Peso Muerto");
                    }else if(nombrefin.equals("Hip Thrust")){
                        imagenRespuesta.setImageResource(R.drawable.hip);
                        textViewRespuesta.setText("La respuesta es Hip Thrust");
                    }else if(nombrefin.equals("Press Militar")){
                        imagenRespuesta.setImageResource(R.drawable.mili);
                        textViewRespuesta.setText("La respuesta es Press Militar");
                    }
                    textView19.setVisibility(View.INVISIBLE);
                    textView18.setVisibility(View.INVISIBLE);
                    spinerrespuesta.setVisibility(View.INVISIBLE);
                    buttonSisNon.setVisibility(View.INVISIBLE);

                    appDatabase.daoIntentosCompletado().updateIntentos(fec,5,1);

                    textView21.setText("HAS FALLADO 💀");
                    imagenRespuesta.startAnimation(animScale);
                }
            }
        });
    }
}