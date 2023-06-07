package com.example.smartstudenthelper.ExamTask.screens.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.ExamTask.ExamTask;
import com.example.smartstudenthelper.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamTaskDetailsActivity extends AppCompatActivity
{
    private static final String EXTRA_EXAMTASK = "ExamTaskDetailsActivity.EXTRA_EXAMTASK";

    private ExamTask examTask;
    private EditText name;
    private EditText commentary;
    private EditText deadline;
    private EditText reminderDate;

    public static void start(Activity caller, ExamTask examTask) {
        Intent intent = new Intent(caller, ExamTaskDetailsActivity.class);
        if (examTask != null) {
            intent.putExtra(EXTRA_EXAMTASK, examTask);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.task_details_title));

        name = findViewById(R.id.examTaskName);
        commentary = findViewById(R.id.examTaskCommentary);
        deadline = findViewById(R.id.examTaskDeadline);
        reminderDate = findViewById(R.id.examTaskReminderDate);


        if (getIntent().hasExtra(EXTRA_EXAMTASK))
        {
            examTask = getIntent().getParcelableExtra(EXTRA_EXAMTASK);
            name.setText(examTask.getName());


            if(!TextUtils.isEmpty(examTask.getCommentary()))
            {
                commentary.setText(examTask.getCommentary());
                System.out.println("ало");
            }

            if(examTask.getDeadline() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(examTask.getDeadline())).toString();
                deadline.setText(dateString);
            }

            if(examTask.getReminderDate() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(examTask.getReminderDate())).toString();
                reminderDate.setText(dateString);
            }
        }
        else
        {
            examTask = new ExamTask();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (name.getText().length() > 0)
                {
                    examTask.setName(name.getText().toString());
                    examTask.setDone(false);
                    examTask.setTimestamp(System.currentTimeMillis());

                    if(commentary.getText().length() > 0)
                    {
                        examTask.setCommentary(commentary.getText().toString());
                    }

                    if(deadline.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = deadline.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            examTask.setDeadline(startDate);

                        }
                        catch (ParseException e)
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
                            examTask.setReminderDate(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (getIntent().hasExtra(EXTRA_EXAMTASK))
                    {
                        App.getInstance().getExamTaskDao().update(examTask);
                    }
                    else
                    {
                        App.getInstance().getExamTaskDao().insert(examTask);
                    }
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }

}