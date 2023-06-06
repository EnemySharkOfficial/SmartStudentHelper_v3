package com.example.smartstudenthelper.Task.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.R;
import com.example.smartstudenthelper.Task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    private Task task;

    private EditText name;
    private EditText commentary;
    private EditText deadline;
    private EditText executionTime;
    private EditText reminderDate;

    public static void start(Activity caller, Task task) {
        Intent intent = new Intent(caller, TaskDetailsActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    //Отображение заметки
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.task_details_title));

        name = findViewById(R.id.taskName);
        commentary = findViewById(R.id.taskCommentary);
        deadline = findViewById(R.id.taskDeadline);
        executionTime = findViewById(R.id.taskExecutionTime);
        reminderDate = findViewById(R.id.taskReminderDate);

        if (getIntent().hasExtra(EXTRA_TASK))
        {
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            name.setText(task.getName());


            if(!TextUtils.isEmpty(task.getCommentary()))
            {
                commentary.setText(task.getCommentary());
            }

            if(task.getDeadline() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(task.getDeadline())).toString();
                deadline.setText(dateString);
            }

            if(task.getExecutionTime() != 0)
            {
                String dateString = DateFormat.format("h:mm", new Date(task.getExecutionTime())).toString();
                executionTime.setText(dateString);
            }

            if(task.getReminderDate() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(task.getReminderDate())).toString();
                reminderDate.setText(dateString);
            }
        }
        else
        {
            task = new Task();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Сохранение изменений в заметке в поля класса
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (name.getText().length() > 0)
                {
                    task.setName(name.getText().toString());
                    task.setDone(false);
                    task.setTimestamp(System.currentTimeMillis());

                    if(commentary.getText().length() > 0)
                    {
                        task.setCommentary(commentary.getText().toString());
                    }

                    if(deadline.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = deadline.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            task.setDeadline(startDate);

                        }
                        catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if(executionTime.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = executionTime.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            task.setExecutionTime(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if(reminderDate.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = reminderDate.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            task.setReminderDate(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (getIntent().hasExtra(EXTRA_TASK))
                    {
                        App.getInstance().getTaskDao().update(task);
                    }
                    else
                    {
                        App.getInstance().getTaskDao().insert(task);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
