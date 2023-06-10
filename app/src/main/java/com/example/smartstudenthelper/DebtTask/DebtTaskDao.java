package com.example.smartstudenthelper.DebtTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DebtTaskDao {

    @Query("SELECT * FROM DebtTask")
    List<DebtTask> getAll();

    @Query("SELECT * FROM DebtTask")
    LiveData<List<DebtTask>> getAllLiveData();

    @Query("SELECT * FROM DebtTask WHERE uid IN (:debtTasksIds)")
    List<DebtTask> loadAllByIds(int[] debtTasksIds);

    @Query("SELECT * FROM DebtTask WHERE uid = :uid LIMIT 1")
    DebtTask findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DebtTask debtTask);

    @Update
    void update(DebtTask debtTask);

    @Delete
    void delete(DebtTask debtTask);
}
