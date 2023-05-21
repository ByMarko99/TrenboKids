package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoComprado {
    @Query("SELECT * FROM comprado")
    List<Comprado> obtenerComprados();

    @Insert
    void InsertarComprados(Comprado...comprados);

    @Query("UPDATE comprado SET comprado = :comprado, resta = :resta WHERE id = :id")
    void updateComprado(int id, boolean comprado, int resta);

}
