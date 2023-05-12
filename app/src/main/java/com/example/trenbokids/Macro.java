package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Macro {

    @PrimaryKey
    @NotNull
    public String fecha;
    public int calorias;
    public int carbos;
    public int proteinas;

    public Macro(String fecha, int calorias, int carbos, int proteinas) {
        this.fecha = fecha;
        this.calorias = calorias;
        this.carbos = carbos;
        this.proteinas = proteinas;
    }
}
