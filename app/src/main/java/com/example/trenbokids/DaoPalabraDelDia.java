package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoPalabraDelDia {
    @Query("SELECT * FROM palabradeldia")
    List<PalabraDelDia> obtenerPalabrasDia();

    @Insert
    void InsertarPalabraDelDia(PalabraDelDia...palabraDelDias);

    // @Query("UPDATE macros SET fecha =:scorenum WHERE fecha =:id")
    //  void updateScore(int id,int scorenum);

    @Query("DELETE FROM palabradeldia")
    void borrarPalabraDias();
}
