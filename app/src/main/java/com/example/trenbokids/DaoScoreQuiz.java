package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoScoreQuiz {
    @Query("SELECT * FROM scorequiz")
    List<ScoreQuiz> obtenerQScores();

    @Insert
    void InsertarQScores(ScoreQuiz...scoreQuizs);

    @Query("UPDATE scorequiz SET score =:score WHERE id =:id")
    void updateScore(int id,int score);

    @Query("DELETE FROM scorequiz")
    void borrarQScores();
}
