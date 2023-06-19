package com.example.smartstudenthelper.Info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.smartstudenthelper.R;

public class LifehacksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifehacks);

        TextView textView = findViewById(R.id.LifehacksText);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}