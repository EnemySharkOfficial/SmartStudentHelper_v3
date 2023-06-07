package com.example.smartstudenthelper.CourseTask.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.CourseTask.CourseTask;

import java.util.List;

public class CourseTaskMainViewModel extends ViewModel
{
    public LiveData<List<CourseTask>> getCourseTaskLiveData()
    {
        return courseTaskLiveData;
    }
    private LiveData<List<CourseTask>> courseTaskLiveData = App.getInstance().getCourseTaskDao().getAllLiveData();
}
