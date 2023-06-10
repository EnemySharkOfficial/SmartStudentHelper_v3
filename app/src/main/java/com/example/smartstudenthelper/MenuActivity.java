package com.example.smartstudenthelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartstudenthelper.CourseTask.screens.details.CourseTaskDetailsActivity;
import com.example.smartstudenthelper.CourseTask.screens.main.CourseTaskMainActivity;
import com.example.smartstudenthelper.DebtTask.screens.details.DebtTaskDetailsActivity;
import com.example.smartstudenthelper.DebtTask.screens.main.DebtTaskMainActivity;
import com.example.smartstudenthelper.ExamTask.screens.details.ExamTaskDetailsActivity;
import com.example.smartstudenthelper.ExamTask.screens.main.ExamTaskMainActivity;
import com.example.smartstudenthelper.Task.screens.details.TaskDetailsActivity;
import com.example.smartstudenthelper.Task.screens.main.TaskMainActivity;
import com.example.smartstudenthelper.СontrolTask.screens.details.ControlTaskDetailsActivity;
import com.example.smartstudenthelper.СontrolTask.screens.main.ControlTaskMainActivity;

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
                Intent myIntent = new Intent(MenuActivity.this, TaskMainActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        Button controlTasks = findViewById(R.id.controlTasks);
        controlTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, ControlTaskDetailsActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        TextView controlTasksTextView = findViewById(R.id.controlTasksText);
        controlTasksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, ControlTaskMainActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        Button examTasks = findViewById(R.id.examTasks);
        examTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, ExamTaskDetailsActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        TextView examTasksTextView = findViewById(R.id.examTaskText);
        examTasksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, ExamTaskMainActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        Button addCourseTask = findViewById(R.id.addCourseTask);
        addCourseTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, CourseTaskDetailsActivity.class);
                MenuActivity.this.startActivity(myIntent);
                System.out.println("");
            }
        });

        TextView showCourseTasks = findViewById(R.id.showCourseTasks);
        showCourseTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, CourseTaskMainActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        Button addDebtTask = findViewById(R.id.addDebtTask);
        addDebtTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, DebtTaskDetailsActivity.class);
                MenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showDebtTasks = findViewById(R.id.showDebtTasks);
        showDebtTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MenuActivity.this, DebtTaskMainActivity.class);
                MenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });
    }
}