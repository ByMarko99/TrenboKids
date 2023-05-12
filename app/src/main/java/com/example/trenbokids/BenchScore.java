package com.example.trenbokids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class BenchScore {

    @PrimaryKey
    @NotNull
    public int id;
    public int score;


    public BenchScore(int id, int score) {
        this.id = id;
        this.score = score;
    }
}
