package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoBenchScore {
    @Query("SELECT * FROM benchscore")
    List<BenchScore> obtenerBScores();

    @Insert
    void InsertarBScores(BenchScore...benchScores);

    @Query("UPDATE benchscore SET score =:score WHERE id =:id")
     void updateScore(int id,int score);

    @Query("DELETE FROM benchscore")
    void borrarBScores();
}
