package com.example.smartstudenthelper.ExamTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExamTaskDao
{
    @Query("SELECT * FROM ExamTask")
    List<ExamTask> getAll();

    @Query("SELECT * FROM ExamTask")
    LiveData<List<ExamTask>> getAllLiveData();

    @Query("SELECT * FROM ExamTask WHERE uid IN (:examTasksIds)")
    List<ExamTask> loadAllByIds(int[] examTasksIds);

    @Query("SELECT * FROM ExamTask WHERE uid = :uid LIMIT 1")
    ExamTask findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ExamTask examTask);

    @Update
    void update(ExamTask examTask);

    @Delete
    void delete(ExamTask examTask);
}
