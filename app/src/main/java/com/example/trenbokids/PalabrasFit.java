package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class PalabrasFit {

    @PrimaryKey
    @NotNull
    public int id;
    public String nombre;
    public String descripcion;


    public PalabrasFit(int id, String nombre,String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion=descripcion;
    }
}
