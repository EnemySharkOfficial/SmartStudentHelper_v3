package com.example.smartstudenthelper;

import android.app.Application;

import androidx.room.Room;

import com.example.smartstudenthelper.CourseTask.CourseTaskDao;
import com.example.smartstudenthelper.CourseTask.CourseTaskDatabase;
import com.example.smartstudenthelper.ExamTask.ExamTaskDao;
import com.example.smartstudenthelper.ExamTask.ExamTaskDatabase;
import com.example.smartstudenthelper.Task.AppDatabase;
import com.example.smartstudenthelper.Task.TaskDao;
import com.example.smartstudenthelper.СontrolTask.ControlTaskDao;
import com.example.smartstudenthelper.СontrolTask.ControlTaskDatabase;

public class App extends Application {

    private AppDatabase taskDB;
    private TaskDao taskDao;
    private ControlTaskDatabase controlTaskDB;
    private ControlTaskDao controlTaskDao;
    private ExamTaskDatabase examTaskDB;
    private ExamTaskDao examTaskDao;
    private CourseTaskDatabase courseTaskDB;
    private CourseTaskDao courseTaskDao;

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

        examTaskDB = Room.databaseBuilder(getApplicationContext(),
                        ExamTaskDatabase.class, "exam-task-database")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        courseTaskDB = Room.databaseBuilder(getApplicationContext(),
                        CourseTaskDatabase.class, "course-task-database")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        taskDao = taskDB.taskDao();
        controlTaskDao = controlTaskDB.controlTaskDao();
        examTaskDao = examTaskDB.examTaskDao();
        courseTaskDao = courseTaskDB.courseTaskDao();
    }

    //Task
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

    //ControlTask
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

    //ExamTask
    public ExamTaskDatabase getExamTaskDB() {
        return examTaskDB;
    }
    public void setExamTaskDB(ExamTaskDatabase examTaskDB) {
        this.examTaskDB = examTaskDB;
    }
    public ExamTaskDao getExamTaskDao() {
        return examTaskDao;
    }
    public void setExamTaskDao(ExamTaskDao examTaskDao) {
        this.examTaskDao = examTaskDao;
    }

    //CourseTask
    public CourseTaskDatabase getCourseTaskDB() {
        return courseTaskDB;
    }
    public void setCourseTaskDB(CourseTaskDatabase courseTaskDB) {
        this.courseTaskDB = courseTaskDB;
    }
    public CourseTaskDao getCourseTaskDao() {
        return courseTaskDao;
    }
    public void setCourseTaskDao(CourseTaskDao courseTaskDao) {
        this.courseTaskDao = courseTaskDao;
    }
}
