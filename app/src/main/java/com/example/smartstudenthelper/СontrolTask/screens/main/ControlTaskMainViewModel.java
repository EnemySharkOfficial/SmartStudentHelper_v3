package com.example.smartstudenthelper.СontrolTask.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.СontrolTask.ControlTask;

import java.util.List;

public class ControlTaskMainViewModel extends ViewModel {
    public LiveData<List<ControlTask>> getControlTaskLiveData() {
        return controlTaskLiveData;
    }
    private LiveData<List<ControlTask>> controlTaskLiveData = App.getInstance().getControlTaskDao().getAllLiveData();
}
