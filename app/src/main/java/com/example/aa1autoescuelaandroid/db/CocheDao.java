package com.example.aa1autoescuelaandroid.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

@Dao
public interface CocheDao {
    @Query("SELECT * FROM cocheentity")
    List<CocheEntity> findAll();

    @Query("SELECT * FROM cocheentity WHERE marca = :marca")
    List<CocheEntity> findByMarca(String marca);

    @Insert
    void insert(CocheEntity cocheEntity);

    @Update
    void update(CocheEntity cocheEntity);

    @Delete
    void delete(CocheEntity cocheEntity);

    @Query("DELETE FROM cocheentity WHERE modelo = :modelo")
    void deleteByModelo(String modelo);
}
