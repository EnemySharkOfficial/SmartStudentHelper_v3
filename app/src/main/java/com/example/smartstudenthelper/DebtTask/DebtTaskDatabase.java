package com.example.smartstudenthelper.DebtTask;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DebtTask.class}, version = 33, exportSchema = false)
public abstract class DebtTaskDatabase extends RoomDatabase
{
    public abstract DebtTaskDao debtTaskDao();
}
