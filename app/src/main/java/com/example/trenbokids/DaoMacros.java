package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoMacros {
    @Query("SELECT * FROM macro")
    List<Macro> obtenerMacros();

    @Insert
    void InsertarMacros(Macro...macros);

   // @Query("UPDATE macros SET fecha =:scorenum WHERE fecha =:id")
  //  void updateScore(int id,int scorenum);

    @Query("DELETE FROM macro")
    void borrarMacros();
}
