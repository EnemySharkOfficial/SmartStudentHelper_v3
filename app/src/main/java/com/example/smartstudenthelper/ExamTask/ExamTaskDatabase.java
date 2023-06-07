package com.example.smartstudenthelper.ExamTask;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ExamTask.class}, version = 33, exportSchema = false)
public abstract class ExamTaskDatabase extends RoomDatabase
{
    public abstract ExamTaskDao examTaskDao();
}
