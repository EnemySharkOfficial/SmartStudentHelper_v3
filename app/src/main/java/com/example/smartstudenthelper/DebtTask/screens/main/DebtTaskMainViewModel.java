package com.example.smartstudenthelper.DebtTask.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.DebtTask.DebtTask;

import java.util.List;

public class DebtTaskMainViewModel extends ViewModel
{
    public LiveData<List<DebtTask>> getDebtTaskLiveData()
    {
        return debtTaskLiveData;
    }
    private LiveData<List<DebtTask>> debtTaskLiveData = App.getInstance().getDebtTaskDao().getAllLiveData();
}
