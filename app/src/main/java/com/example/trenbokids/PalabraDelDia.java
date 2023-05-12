package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class PalabraDelDia {

    @PrimaryKey
    @NotNull
    public String fecha;
    public int idPalabra;



    public PalabraDelDia(String fecha, int idPalabra) {
        this.fecha = fecha;
        this.idPalabra = idPalabra;
    }
}
