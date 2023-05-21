package com.example.trenbokids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trenbokids.BenchScore;

import java.util.List;

@Dao
public interface DaoArnoldScore {
    @Query("SELECT * FROM arnoldscore")
    List<ArnoldScore> obtenerAnosScores();

    @Insert
    void InsertarAnosScores(ArnoldScore...arnoldScores);

    @Query("UPDATE arnoldscore SET score =:score WHERE id =:id")
     void updateScore(int id,int score);

    @Query("DELETE FROM arnoldscore")
    void borrarAnosScores();
}
