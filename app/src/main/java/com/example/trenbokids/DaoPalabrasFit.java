package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoPalabrasFit {
    @Query("SELECT * FROM palabrasfit")
    List<PalabrasFit> obtenerPalabras();

    @Insert
    void InsertarPalabras(PalabrasFit...palabrasFits);

    @Query("UPDATE palabrasfit SET nombre =:nombre , descripcion =:descripcion WHERE id =:id")
    void updatePalabras(int id,String nombre, String descripcion);

    @Query("DELETE FROM palabrasfit")
    void borrarPalabras();
}
