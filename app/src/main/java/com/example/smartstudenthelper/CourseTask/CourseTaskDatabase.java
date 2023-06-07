package com.example.smartstudenthelper.CourseTask;

import androidx.room.Database;
import androidx.room.RoomDatabase;;

@Database(entities = {CourseTask.class}, version = 33, exportSchema = false)
public abstract class CourseTaskDatabase extends RoomDatabase
{
    public abstract CourseTaskDao courseTaskDao();
}
