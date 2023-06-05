package com.example.smartstudenthelper.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smartstudenthelper.model.Task;

@Database(entities = {Task.class}, version = 32, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
