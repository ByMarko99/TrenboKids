package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class IntentosCompletado {

    @PrimaryKey
    @NotNull
    public String fecha;
    public int intentos;
    public int completado;



    public IntentosCompletado(String fecha, int intentos,int completado) {
        this.fecha = fecha;
        this.intentos = intentos;
        this.completado = completado;
    }
}
