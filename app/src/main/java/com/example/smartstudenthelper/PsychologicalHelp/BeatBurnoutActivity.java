package com.example.smartstudenthelper.PsychologicalHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.smartstudenthelper.R;

public class BeatBurnoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat_burnout);

        TextView textView = findViewById(R.id.Beat_Burnout_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}