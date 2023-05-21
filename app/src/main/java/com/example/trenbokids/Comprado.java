package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Comprado {

    @PrimaryKey
    @NotNull
    public int id;

    public int resta;
    public boolean comprado;


    public Comprado(int id, boolean comprado, int resta) {
        this.id = id;
        this.comprado = comprado;
        this.resta = resta;
    }
}
