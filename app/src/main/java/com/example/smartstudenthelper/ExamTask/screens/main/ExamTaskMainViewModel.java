package com.example.smartstudenthelper.ExamTask.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.ExamTask.ExamTask;

import java.util.List;

public class ExamTaskMainViewModel extends ViewModel
{
    public LiveData<List<ExamTask>> getExamTaskLiveData() {
        return examTaskLiveData;
    }
    private LiveData<List<ExamTask>> examTaskLiveData = App.getInstance().getExamTaskDao().getAllLiveData();
}
