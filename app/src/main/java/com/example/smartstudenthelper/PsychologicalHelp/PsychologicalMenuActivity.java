package com.example.smartstudenthelper.PsychologicalHelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.smartstudenthelper.Info.PrinciplesActivity;
import com.example.smartstudenthelper.MenuActivity;
import com.example.smartstudenthelper.R;

public class PsychologicalMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychological_menu);

        TextView showHowToBeatStress = findViewById(R.id.HowToBeatStress);
        showHowToBeatStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, BeatStressActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showBooksToRead = findViewById(R.id.WhichBooksToRead);
        showBooksToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, BooksActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showChatBotsAndApps = findViewById(R.id.ChatBots);
        showChatBotsAndApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, ChatBotsAndAppsActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showProblemsWithStudy = findViewById(R.id.ProblemsWithStudy);
        showProblemsWithStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, StudyQuestionsActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showNoMotivationToStudy = findViewById(R.id.NoMotivationToStudy);
        showNoMotivationToStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, NoMotivationActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });

        TextView showHowToBeatBurnout = findViewById(R.id.HowToBeatBurnout);
        showHowToBeatBurnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(PsychologicalMenuActivity.this, BeatBurnoutActivity.class);
                PsychologicalMenuActivity.this.startActivity(myIntent);
                System.out.println("кнопка работает");
            }
        });
    }
}