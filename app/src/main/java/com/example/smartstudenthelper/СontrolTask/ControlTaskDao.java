package com.example.smartstudenthelper.СontrolTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ControlTaskDao {

    @Query("SELECT * FROM ControlTask")
    List<ControlTask> getAll();

    @Query("SELECT * FROM ControlTask")
    LiveData<List<ControlTask>> getAllLiveData();

    @Query("SELECT * FROM ControlTask WHERE uid IN (:controlTasksIds)")
    List<ControlTask> loadAllByIds(int[] controlTasksIds);

    @Query("SELECT * FROM ControlTask WHERE uid = :uid LIMIT 1")
    ControlTask findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ControlTask controlTask);

    @Update
    void update(ControlTask controlTask);

    @Delete
    void delete(ControlTask controlTask);
}
