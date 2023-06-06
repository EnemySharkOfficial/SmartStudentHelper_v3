package com.example.smartstudenthelper;

import android.app.Application;

import androidx.room.Room;

import com.example.smartstudenthelper.Task.AppDatabase;
import com.example.smartstudenthelper.Task.TaskDao;
import com.example.smartstudenthelper.СontrolTask.ControlTaskDao;
import com.example.smartstudenthelper.СontrolTask.ControlTaskDatabase;

public class App extends Application {

    private AppDatabase taskDB;
    private ControlTaskDatabase controlTaskDB;
    private TaskDao taskDao;
    private ControlTaskDao controlTaskDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        taskDB = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        controlTaskDB = Room.databaseBuilder(getApplicationContext(),
                        ControlTaskDatabase.class, "control-task-database")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        taskDao = taskDB.taskDao();
        controlTaskDao = controlTaskDB.controlTaskDao();
    }

    public AppDatabase getTaskDB() {
        return taskDB;
    }
    public ControlTaskDatabase getControlTaskDB() {
        return controlTaskDB;
    }
    public void setTaskDB(AppDatabase taskDB) {
        this.taskDB = taskDB;
    }
    public void setControlTaskDB(ControlTaskDatabase controlTaskDB) {
        this.controlTaskDB = controlTaskDB;
    }
    public TaskDao getTaskDao() {
        return taskDao;
    }
    public ControlTaskDao getControlTaskDao() {
        return controlTaskDao;
    }
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    public void setControlTaskDao(ControlTaskDao controlTaskDao) {
        this.controlTaskDao = controlTaskDao;
    }
}
