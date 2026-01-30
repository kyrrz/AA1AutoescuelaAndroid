package com.example.aa1autoescuelaandroid.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

@Dao
public interface AutoescuelaDao {


    @Insert
    void insert(Autoescuela autoescuela);
    @Update
    void update(Autoescuela autoescuela);
    @Delete
    void delete(Autoescuela autoescuela);
}
