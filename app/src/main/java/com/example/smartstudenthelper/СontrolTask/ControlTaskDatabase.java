package com.example.smartstudenthelper.СontrolTask;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smartstudenthelper.Task.Task;

@Database(entities = {ControlTask.class}, version = 33, exportSchema = false)
public abstract class ControlTaskDatabase extends RoomDatabase
{
    public abstract ControlTaskDao controlTaskDao();
}
