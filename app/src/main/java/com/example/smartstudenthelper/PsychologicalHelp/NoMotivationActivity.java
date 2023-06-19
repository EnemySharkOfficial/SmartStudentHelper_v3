package com.example.smartstudenthelper.PsychologicalHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.smartstudenthelper.R;

public class NoMotivationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_motivation);

        TextView textView = findViewById(R.id.motivation_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}