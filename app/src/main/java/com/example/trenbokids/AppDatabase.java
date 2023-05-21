package com.example.trenbokids;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Macro.class,BenchScore.class,SquatScore.class,PalabrasFit.class,PalabraDelDia.class,IntentosCompletado.class,ScoreQuiz.class, ArnoldScore.class},
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoMacros daoMacros();
    public abstract DaoBenchScore daoBenchScore();
    public abstract DaoSquatScore daoSquatScore();
    public abstract DaoPalabrasFit daoPalabrasFit();
    public abstract DaoPalabraDelDia daoPalabraDelDia();
    public abstract DaoIntentosCompletado daoIntentosCompletado();
    public abstract DaoScoreQuiz daoScoreQuiz();
    public abstract DaoArnoldScore daoArnoldScore();

}