package com.example.smartstudenthelper.DebtTask.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartstudenthelper.DebtTask.DebtTask;
import com.example.smartstudenthelper.MenuActivity;
import com.example.smartstudenthelper.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DebtTaskMainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(DebtTaskMainActivity.this, MenuActivity.class);
                DebtTaskMainActivity.this.startActivity(myIntent);
            }
        });

        DebtTaskMainViewModel mainViewModel = new ViewModelProvider(this).get(DebtTaskMainViewModel.class);
        mainViewModel.getDebtTaskLiveData().observe(this, new Observer<List<DebtTask>>() {
            @Override
            public void onChanged(List<DebtTask> tasks) {
                adapter.setItems(tasks);
            }
        });
    }
}
