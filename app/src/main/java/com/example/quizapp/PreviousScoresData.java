package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Queue;

public class PreviousScoresData extends AppCompatActivity {

    int counter = 0;

    Queue<UserData> queue;
    DataStoring dataStoring;
    private Object UserData;
    //private Object Queue;
    TextView nameTextView;
    TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_scores_data);
        dataStoring = new DataStoring(this);
        print();



    }

    void print() {

        LinearLayout parentLinearLayout = findViewById(R.id.scores_text_view_layout);
        queue = dataStoring.getData();

        if (queue != null) {


            for (UserData data : queue) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.previous_result_text_view, null);
                TextView nameTextView = view.findViewById(R.id.name_text);
                TextView scoreTextView = view.findViewById(R.id.score_text);

                nameTextView.setText(data.userName);
                scoreTextView.setText(String.valueOf(data.score));

                parentLinearLayout.addView(view);
            }

        }
        else {


            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.previous_result_text_view, null);
            TextView nameTextView = view.findViewById(R.id.name_text);
            TextView scoreTextView = view.findViewById(R.id.score_text);

            nameTextView.setText("no entries found");
            scoreTextView.setText("no entries found");
            parentLinearLayout.addView(view);

        }

    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();

        }
    }

