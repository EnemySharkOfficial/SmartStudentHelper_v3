package com.example.smartstudenthelper.Task;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllLiveData();

    @Query("SELECT * FROM Task WHERE uid IN (:tasksIds)")
    List<Task> loadAllByIds(int[] tasksIds);

    @Query("SELECT * FROM Task WHERE uid = :uid LIMIT 1")
    Task findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
