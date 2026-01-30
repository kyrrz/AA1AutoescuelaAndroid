package com.example.aa1autoescuelaandroid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

@Database(entities = {Autoescuela.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutoescuelaDao autoescuelaDao();
}