package com.example.smartstudenthelper.PsychologicalHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.smartstudenthelper.R;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        TextView textView = findViewById(R.id.Books_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}