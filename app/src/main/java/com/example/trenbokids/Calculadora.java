package com.example.trenbokids;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calculadora extends AppCompatActivity {
    Spinner spinnerdesayuno;
    Spinner spinnercomida;
    Spinner spinnercena;
    Button botondesayuno;
    Button botoncomida;
    Button botoncena;
    Button botonReset;

    TextView TextCalorias;
    TextView TextProteinas;
    TextView TextCarbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcu);
        getSupportActionBar().hide();

        List<Macro> listMacros;

        spinnerdesayuno = findViewById(R.id.spinnerdesayuno);
        spinnercomida = findViewById(R.id.spinnercomida);
        spinnercena = findViewById(R.id.spinnercena);
        TextCalorias = findViewById(R.id.textCalorias);
        TextProteinas = findViewById(R.id.TextCalorias);
        TextCarbo = findViewById(R.id.textCarbo);

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        listMacros = appDatabase.daoMacros().obtenerMacros();

        if(listMacros.isEmpty()){
            TextCalorias.setText("0");
            TextProteinas.setText("0");
            TextCarbo.setText("0");
        }else {
            Date d = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());
            String fec = s.toString();

            String fechas = "";
            int cal = 0;
            int car =0;
            int pro =0;
            for (int i = 0; i < listMacros.size(); i++) {
                fechas = listMacros.get(i).fecha;
                cal = listMacros.get(i).calorias;
                car=listMacros.get(i).carbos;
                pro = listMacros.get(i).proteinas;

                TextCalorias.setText(Integer.toString(cal));
                TextProteinas.setText(Integer.toString(car));
                TextCarbo.setText(Integer.toString(pro));
            }

            if(fec.equals(fechas)){


            }else{
                appDatabase.daoMacros().borrarMacros();
                TextCalorias.setText("0");
                TextProteinas.setText("0");
                TextCarbo.setText("0");
            }
        }
        ArrayList<String> desayuno = new ArrayList<>();
        desayuno.add("CAFE / 1 TAZA");
        desayuno.add("CEREAL / 1 BOL");
        desayuno.add("TOSTADAS CON MERMELADA / 1 TOSTADA");
        desayuno.add("TOSTADAS CON MANTEQUILLA / 1 TOSTADA");
        desayuno.add("BOLLO / 1 UNIDAD");
        desayuno.add("FRUTA / 1 PIEZA");
        desayuno.add("COLACAO / 1 TAZA");
        desayuno.add("LECHE / 1 TAZA");

        ArrayList<String> comida = new ArrayList<>();
        comida.add("ARROZ / 1 BOL");
        comida.add("POLLO / 100 GR");
        comida.add("PESCADO / 100 GR");
        comida.add("TERNERA / 100 GR");
        comida.add("CERDO / 100 GR");
        comida.add("CALDO / 1 TAZA");
        comida.add("ALUBIAS / 100 GR");
        comida.add("ESPAGUETIS / 100 GR");
        comida.add("MACARRONES / 100 GR");
        comida.add("ENSALADA / 100 GR");
        comida.add("HAMBUERUESA / 1 UNIDAD");

        ArrayList<String> cena = new ArrayList<>();
        cena.add("BURRITO DE POLLO / 1 UNIDAD");
        cena.add("POLLO / 100 GR");
        cena.add("PESCADO / 100 GR");
        cena.add("TERNERA / 100 GR");
        cena.add("CERDO / 100 GR");
        cena.add("CALDO / 1 TAZA");
        cena.add("ENSALADA / 100 GR");
        cena.add("PIZZA / 1 SLICE");
        cena.add("HAMBUERUESA / 1 UNIDAD");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,desayuno);
        spinnerdesayuno.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,comida);
        spinnercomida.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cena);
        spinnercena.setAdapter(adapter3);

        botondesayuno=findViewById(R.id.botondesayuno);
        botondesayuno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String a = spinnerdesayuno.getSelectedItem().toString();
                if(a.equals("CAFE / 1 TAZA")){
                        String e = TextCalorias.getText().toString();
                        String d = TextProteinas.getText().toString();
                        String b = TextCarbo.getText().toString();
                        int ter = Integer.parseInt(e);
                        int ter2 = Integer.parseInt(d);
                        int ter3 = Integer.parseInt(b);
                        int x;
                        int x2;
                        int x3;
                        x=ter+2 ;
                        x2=ter2;
                        x3=ter3;

                        Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                         String fec = s.toString();
                        if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                            Macro mac = new Macro(fec,x,x3,x2);
                            appDatabase.daoMacros().InsertarMacros(mac);
                            TextCalorias.setText(Integer.toString(x));
                            TextCalorias.setText(Integer.toString(x));
                            TextProteinas.setText(Integer.toString(x2));
                            TextCarbo.setText(Integer.toString(x3));
                        }else{
                            ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                            ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                            ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                            x=ter+2;
                            x2=ter2;
                            x3=ter3;
                            appDatabase.daoMacros().borrarMacros();
                            Macro mac = new Macro(fec,x,x3,x2);
                            appDatabase.daoMacros().InsertarMacros(mac);
                            TextCalorias.setText(Integer.toString(x));
                            TextProteinas.setText(Integer.toString(x2));
                            TextCarbo.setText(Integer.toString(x3));
                        }


                } else if (a.equals("CEREAL / 1 BOL")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+372;
                    x2=ter2+7;
                    x3=ter3+85;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+372;
                        x2=ter2+7;
                        x3=ter3+85;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("TOSTADAS CON MERMELADA / 1 TOSTADA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+379 ;
                    x2=ter2+10;
                    x3=ter3+67;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+379 ;
                        x2=ter2+10;
                        x3=ter3+67;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }

                }else if (a.equals("TOSTADAS CON MANTEQUILLA / 1 TOSTADA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+380;
                    x2=ter2+13;
                    x3=ter3+44;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+380;
                        x2=ter2+13;
                        x3=ter3+44;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("BOLLO / 1 UNIDAD")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+278 ;
                    x2=ter2+1;
                    x3=ter3+56;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+278 ;
                        x2=ter2+1;
                        x3=ter3+56;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("FRUTA / 1 PIEZA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+12;
                    x2=ter2+0;
                    x3=ter3+3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+12;
                        x2=ter2+0;
                        x3=ter3+3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("COLACAO / 1 TAZA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+79 ;
                    x2=ter2;
                    x3=ter3+11;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+79 ;
                        x2=ter2;
                        x3=ter3+11;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("LECHE / 1 TAZA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+66;
                    x2=ter2+3;
                    x3=ter3+4;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+66;
                        x2=ter2+3;
                        x3=ter3+4;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }
            }
        });

        botoncomida=findViewById(R.id.botoncomida);
        botoncomida.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String a = spinnercomida.getSelectedItem().toString();
                if(a.equals("ARROZ / 1 BOL")){
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+350;
                    x2=ter2+8;
                    x3=ter3+73;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+350;
                        x2=ter2+8;
                        x3=ter3+73;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }

                } else if (a.equals("POLLO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+145;
                    x2=ter2+20;
                    x3=ter3+3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+145;
                        x2=ter2+20;
                        x3=ter3+3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("PESCADO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+80;
                    x2=ter2+10;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;

                        x=ter+80;
                        x2=ter2+10;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("TERNERA / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+146 ;
                    x2=ter2+20;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+146 ;
                        x2=ter2+20;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("CERDO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+220;
                    x2=ter2+20;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+220;
                        x2=ter2+20;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("CALDO / 1 TAZA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+5;
                    x2=ter2+1;
                    x3=ter3+1;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+5;
                        x2=ter2+1;
                        x3=ter3+1;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("ALUBIAS / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+346;
                    x2=ter2+22;
                    x3=ter3+61;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+346;
                        x2=ter2+22;
                        x3=ter3+61;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("ESPAGUETIS / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+157;
                    x2=ter2;
                    x3=ter3+74 ;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+157;
                        x2=ter2;
                        x3=ter3+74 ;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("MACARRONES / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+157;
                    x2=ter2;
                    x3=ter3+74;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+157;
                        x2=ter2;
                        x3=ter3+74;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("ENSALADA / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+180;
                    x2=ter2+10;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+180;
                        x2=ter2+10;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("HAMBUERUESA / 1 UNIDAD")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+300;
                    x2=ter2+15;
                    x3=ter3+28;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+300;
                        x2=ter2+15;
                        x3=ter3+28;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }
            }
        });

        botoncena=findViewById(R.id.botondena);
        botoncena.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String a = spinnercena.getSelectedItem().toString();
                if(a.equals("BURRITO DE POLLO / 1 UNIDAD")){
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+271;
                    x2=ter2+23;
                    x3=ter3+24;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+271;
                        x2=ter2+23;
                        x3=ter3+24;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }

                } else if (a.equals("POLLO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+145;
                    x2=ter2+20;
                    x3=ter3+3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+145;
                        x2=ter2+20;
                        x3=ter3+3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("PESCADO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+80;
                    x2=ter2+10;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+80;
                        x2=ter2+10;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("TERNERA / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+146 ;
                    x2=ter2+20;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+146 ;
                        x2=ter2+20;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("CERDO / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+220;
                    x2=ter2+20;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+220;
                        x2=ter2+20;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("CALDO / 1 TAZA")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+5;
                    x2=ter2+1;
                    x3=ter3+1;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+5;
                        x2=ter2+1;
                        x3=ter3+1;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("ENSALADA / 100 GR")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+180;
                    x2=ter2+10;
                    x3=ter3;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+180;
                        x2=ter2+10;
                        x3=ter3;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("PIZZA / 1 SLICE")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+237;
                    x2=ter2+10;
                    x3=ter3+26;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+237;
                        x2=ter2+10;
                        x3=ter3+26;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }else if (a.equals("HAMBUERUESA / 1 UNIDAD")) {
                    String e = TextCalorias.getText().toString();
                    String d = TextProteinas.getText().toString();
                    String b = TextCarbo.getText().toString();
                    int ter = Integer.parseInt(e);
                    int ter2 = Integer.parseInt(d);
                    int ter3 = Integer.parseInt(b);
                    int x;
                    int x2;
                    int x3;
                    x=ter+300;
                    x2=ter2+15;
                    x3=ter3+28;

                    Date da = new Date(); CharSequence s = DateFormat.format("MMMM d, yyyy ", da.getTime());
                    String fec = s.toString();
                    if(appDatabase.daoMacros().obtenerMacros().isEmpty()){
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }else{
                        ter=appDatabase.daoMacros().obtenerMacros().get(0).calorias;
                        ter2=appDatabase.daoMacros().obtenerMacros().get(0).proteinas;
                        ter3=appDatabase.daoMacros().obtenerMacros().get(0).carbos;
                        x=ter+300;
                        x2=ter2+15;
                        x3=ter3+28;
                        appDatabase.daoMacros().borrarMacros();
                        Macro mac = new Macro(fec,x,x3,x2);
                        appDatabase.daoMacros().InsertarMacros(mac);
                        TextCalorias.setText(Integer.toString(x));
                        TextProteinas.setText(Integer.toString(x2));
                        TextCarbo.setText(Integer.toString(x3));
                    }
                }
            }
        });
        botonReset=findViewById(R.id.botonReset);
        botonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                appDatabase.daoMacros().borrarMacros();
                TextCalorias.setText("0");
                TextProteinas.setText("0");
                TextCarbo.setText("0");
            }
        });
    }
}