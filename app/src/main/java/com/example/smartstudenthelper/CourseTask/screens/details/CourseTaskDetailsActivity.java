package com.example.smartstudenthelper.CourseTask.screens.details;

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
import com.example.smartstudenthelper.CourseTask.CourseTask;
import com.example.smartstudenthelper.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseTaskDetailsActivity extends AppCompatActivity
{
    private static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    private CourseTask courseTask;

    private EditText name;
    private EditText commentary;
    private EditText deadline;
    private EditText executionTime;
    private EditText reminderDate;

    public static void start(Activity caller, CourseTask courseTask) {
        Intent intent = new Intent(caller, CourseTaskDetailsActivity.class);
        if (courseTask != null) {
            intent.putExtra(EXTRA_TASK, courseTask);
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
            courseTask = getIntent().getParcelableExtra(EXTRA_TASK);
            name.setText(courseTask.getName());


            if(!TextUtils.isEmpty(courseTask.getCommentary()))
            {
                commentary.setText(courseTask.getCommentary());
            }

            if(courseTask.getDeadline() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(courseTask.getDeadline())).toString();
                deadline.setText(dateString);
            }

            if(courseTask.getExecutionTime() != 0)
            {
                String dateString = DateFormat.format("h:mm", new Date(courseTask.getExecutionTime())).toString();
                executionTime.setText(dateString);
            }

            if(courseTask.getReminderDate() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(courseTask.getReminderDate())).toString();
                reminderDate.setText(dateString);
            }
        }
        else
        {
            courseTask = new CourseTask();
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
                    courseTask.setName(name.getText().toString());
                    courseTask.setDone(false);
                    courseTask.setTimestamp(System.currentTimeMillis());

                    if(commentary.getText().length() > 0)
                    {
                        courseTask.setCommentary(commentary.getText().toString());
                    }

                    if(deadline.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = deadline.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            courseTask.setDeadline(startDate);

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
                            courseTask.setExecutionTime(startDate);

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
                            courseTask.setReminderDate(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (getIntent().hasExtra(EXTRA_TASK))
                    {
                        App.getInstance().getCourseTaskDao().update(courseTask);
                    }
                    else
                    {
                        App.getInstance().getCourseTaskDao().insert(courseTask);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
