package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button takeTestButton;
    TextView scoresTextView;
    int mainActivityCounter = 0;

    /*public static Context getAppContext() {
        return null;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        takeTestButton = (Button)this.findViewById(R.id.take_test_button);
        scoresTextView = (TextView)this.findViewById(R.id.all_persons_score_data);
        //mainActivityCounter = (Integer) intent.getSerializableExtra("MainActivity");

    }

    @Override
    public void onResume() {

        super.onResume();
        setListners();

    }
    void setListners () {

        takeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ExamScreen.class);
                startActivity(intent);
                finish();

            }
        });

        scoresTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,PreviousScoresData.class);
                startActivity(intent);

            }

        });
        }
}
