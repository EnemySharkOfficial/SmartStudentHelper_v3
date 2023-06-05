package com.example.smartstudenthelper.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Task>> taskLiveData = App.getInstance().getTaskDao().getAllLiveData();

    public LiveData<List<Task>> getTaskLiveData() {
        return taskLiveData;
    }
}
