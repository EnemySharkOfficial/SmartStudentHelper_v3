package com.example.smartstudenthelper.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.smartstudenthelper.model.Note;

@Database(entities = {Note.class}, version = 30, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
