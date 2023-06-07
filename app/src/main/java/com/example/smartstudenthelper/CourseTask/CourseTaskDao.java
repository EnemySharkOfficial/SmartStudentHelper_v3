package com.example.smartstudenthelper.CourseTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseTaskDao {

    @Query("SELECT * FROM CourseTask")
    List<CourseTask> getAll();

    @Query("SELECT * FROM CourseTask")
    LiveData<List<CourseTask>> getAllLiveData();

    @Query("SELECT * FROM CourseTask WHERE uid IN (:courseTasksIds)")
    List<CourseTask> loadAllByIds(int[] courseTasksIds);

    @Query("SELECT * FROM CourseTask WHERE uid = :uid LIMIT 1")
    CourseTask findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseTask courseTask);

    @Update
    void update(CourseTask courseTask);

    @Delete
    void delete(CourseTask courseTask);
}

