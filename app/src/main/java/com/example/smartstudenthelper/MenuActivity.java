package com.example.smartstudenthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartstudenthelper.screens.details.TaskDetailsActivity;
import com.example.smartstudenthelper.screens.main.MainActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button tasks = findViewById(R.id.Tasks);
        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, TaskDetailsActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        TextView tasksTextView = findViewById(R.id.TasksText);
        tasksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, MainActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
    }
}