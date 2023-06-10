package com.example.smartstudenthelper.DebtTask.screens.details;

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
import com.example.smartstudenthelper.DebtTask.DebtTask;
import com.example.smartstudenthelper.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebtTaskDetailsActivity extends AppCompatActivity
{
    private static final String EXTRA_DEBTTASK = "DebtDetailsActivity.EXTRA_DEBTTASK";

    private DebtTask debtTask;

    private EditText name;
    private EditText commentary;
    private EditText deadline;
    private EditText executionTime;
    private EditText reminderDate;

    public static void start(Activity caller, DebtTask debtTask) {
        Intent intent = new Intent(caller, DebtTaskDetailsActivity.class);
        if (debtTask != null) {
            intent.putExtra(EXTRA_DEBTTASK, debtTask);
        }
        caller.startActivity(intent);
    }

    //Отображение заметки
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_debt_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.task_details_title));

        name = findViewById(R.id.debtTaskName);
        commentary = findViewById(R.id.debtTaskCommentary);
        deadline = findViewById(R.id.debtTaskDeadline);
        executionTime = findViewById(R.id.debtTaskExecutionTime);
        reminderDate = findViewById(R.id.debtTaskReminderDate);

        if (getIntent().hasExtra(EXTRA_DEBTTASK))
        {
            debtTask = getIntent().getParcelableExtra(EXTRA_DEBTTASK);
            name.setText(debtTask.getName());


            if(!TextUtils.isEmpty(debtTask.getCommentary()))
            {
                commentary.setText(debtTask.getCommentary());
            }

            if(debtTask.getDeadline() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(debtTask.getDeadline())).toString();
                deadline.setText(dateString);
            }

            if(debtTask.getExecutionTime() != 0)
            {
                String dateString = DateFormat.format("h:mm", new Date(debtTask.getExecutionTime())).toString();
                executionTime.setText(dateString);
            }

            if(debtTask.getReminderDate() != 0)
            {
                String dateString = DateFormat.format("dd.MM.yyyy", new Date(debtTask.getReminderDate())).toString();
                reminderDate.setText(dateString);
            }
        }
        else
        {
            debtTask = new DebtTask();
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
                    debtTask.setName(name.getText().toString());
                    debtTask.setDone(false);
                    debtTask.setTimestamp(System.currentTimeMillis());

                    if(commentary.getText().length() > 0)
                    {
                        debtTask.setCommentary(commentary.getText().toString());
                    }

                    if(deadline.getText().length() > 0)
                    {
                        try
                        {
                            String dateString = deadline.getText().toString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = sdf.parse(dateString);
                            long startDate = date.getTime();
                            debtTask.setDeadline(startDate);

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
                            debtTask.setExecutionTime(startDate);

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
                            debtTask.setReminderDate(startDate);

                        } catch (ParseException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (getIntent().hasExtra(EXTRA_DEBTTASK))
                    {
                        App.getInstance().getDebtTaskDao().update(debtTask);
                    }
                    else
                    {
                        App.getInstance().getDebtTaskDao().insert(debtTask);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
