package com.example.aa1autoescuelaandroid.db;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.aa1autoescuelaandroid.util.Converters;


@Database(entities = {CocheEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract CocheDao cocheDao();

}
