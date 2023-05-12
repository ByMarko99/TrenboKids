package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoSquatScore {
    @Query("SELECT * FROM squatscore")
    List<SquatScore> obtenerSScores();

    @Insert
    void InsertarSScores(SquatScore...squatScores);

    @Query("UPDATE squatscore SET score =:score WHERE id =:id")
    void updateSScore(int id,int score);

    @Query("DELETE FROM squatscore")
    void borrarSScores();
}
