package com.yaramobile.batman.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BatmanDao {

    @Query("SELECT * FROM batman")
    List<BatmanEntity> getAll();

    @Insert
    void insert(BatmanEntity entity);
}
