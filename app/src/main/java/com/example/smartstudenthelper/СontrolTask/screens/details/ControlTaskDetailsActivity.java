package com.example.smartstudenthelper.СontrolTask.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartstudenthelper.App;
import com.example.smartstudenthelper.R;
import com.example.smartstudenthelper.Task.Task;
import com.example.smartstudenthelper.СontrolTask.ControlTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlTaskDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_CONTROLTASK = "ControlTaskDetailsActivity.EXTRA_CONTROLTASK";

    private ControlTask controlTask;
    private EditText name;
    private EditText commentary;
    private EditText deadline;
    private EditText executionTime;
    private EditText reminderDate;

    public static void start(Activity caller, ControlTask controlTask) {
        Intent intent = new Intent(caller, ControlTaskDetailsActivity.class);
        if (controlTask != null) {
            intent.putExtra(EXTRA_CONTROLTASK, controlTask);
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

        if (getIntent().hasExtra(EXTRA_CONTROLTASK))
        {
            controlTask = getIntent().getParcelableExtra(EXTRA_CONTROLTASK);
            name.setText(controlTask.getName());


            if(!TextUtils.isEmpty(controlTask.getCommentary()))
            {
                commentary.setText(controlTask.getCommentary());
                System.out.println("ало");
            }

            if(controlTask.getDeadline() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(controlTask.getDeadline())).toString();
                deadline.setText(dateString);
            }

            if(controlTask.getExecutionTime() != 0)
            {
                String dateString = DateFormat.format("h:mm", new Date(controlTask.getExecutionTime())).toString();
                executionTime.setText(dateString);
            }

            if(controlTask.getReminderDate() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(controlTask.getReminderDate())).toString();
                reminderDate.setText(dateString);
            }
        }
        else
        {
            controlTask = new ControlTask();
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
                    controlTask.setName(name.getText().toString());
                    controlTask.setDone(false);
                    controlTask.setTimestamp(System.currentTimeMillis());

                    if(commentary.getText().length() > 0)
                    {
                        controlTask.setCommentary(commentary.getText().toString());

                        System.out.println("=======================================================================");
                        System.out.println("Заметка сохранена");
                        System.out.println("=======================================================================");
                    }

                    if(deadline.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = deadline.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            controlTask.setDeadline(startDate);

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
                            controlTask.setExecutionTime(startDate);

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
                            controlTask.setReminderDate(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (getIntent().hasExtra(EXTRA_CONTROLTASK))
                    {
                        App.getInstance().getControlTaskDao().update(controlTask);
                    }
                    else
                    {
                        App.getInstance().getControlTaskDao().insert(controlTask);
                    }
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }

}
