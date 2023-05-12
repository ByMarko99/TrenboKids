package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoIntentosCompletado {
    @Query("SELECT * FROM intentoscompletado")
    List<IntentosCompletado> obtenerintentos();

    @Insert
    void InsertarIntentos(IntentosCompletado...intentosCompletados);

     @Query("UPDATE intentoscompletado SET intentos =:intentos, completado =:completado WHERE fecha =:fecha")
     void updateIntentos(String fecha,int intentos,int completado);

    @Query("DELETE FROM intentoscompletado")
    void borrarIntentos();
}
