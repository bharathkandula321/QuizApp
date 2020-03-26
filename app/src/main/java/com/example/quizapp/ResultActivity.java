package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.io.Serializable;


public class ResultActivity extends AppCompatActivity {
    static ArrayList<QuestionAndAnswers> arrayList = new ArrayList<>();
    private static Context appContext;
    HashMap<Integer, String> responseHashMap = new HashMap<Integer, String>();
    int score = 0;
    TextView totalScore, obtainedScore;
    ArrayList<QuestionAndAnswers> arrayList1 = new ArrayList<>();
    HashMap<Integer, String> ans = new HashMap<Integer, String>();
    Button takeTestAgainButton;
    String userName;
    TextView scroesTextView;
    int resultCounter = 0;
    DataStoring dataStoring;
    Queue<UserData> q ;
    /*public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        ResultActivity.appContext = appContext;
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        responseHashMap = (HashMap<Integer, String>) intent.getSerializableExtra("responses");
        /*answerHashMap = (HashMap<Integer,String>) intent.getSerializableExtra("answers");*/
        arrayList1 = (ArrayList<QuestionAndAnswers>) intent.getSerializableExtra("question");
        userName = (String) intent.getSerializableExtra("username");
        obtainedScore = findViewById(R.id.obtained_score_text_view);
        totalScore = findViewById(R.id.total_score_text_view);
        takeTestAgainButton = findViewById(R.id.take_test);
        scroesTextView = findViewById(R.id.all_persons_data);
        dataStoring = DataStoring.getInstance(this);

        //resultCounter = (Integer) intent.getSerializableExtra("ResultActivity");
        addAnswers();
        //sorting();
        score();
        printing();
        try {
            dataStoring.data1(userName,score);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void listeniers() {

        takeTestAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,ExamScreen.class);
                startActivity(intent);
                finish();
            }
        });

        scroesTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this,PreviousScoresData.class);
                startActivity(intent);

            }

        });
    }

    void printing() {

        System.out.println(responseHashMap);
        System.out.println("answers hash map" + ans);
        System.out.println("score " + score);

        totalScore.setText(String.valueOf(arrayList1.size()));
        obtainedScore.setText(String.valueOf(score));

        LinearLayout parentLinearLayout = findViewById(R.id.result_inner_layout);
       /* LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);*/

        for (int i = 0; i < arrayList1.size(); i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_result_question, null);

            TextView questionTextView = view.findViewById(R.id.result_question_text_view);
            ImageView responseCorrectImageView = view.findViewById(R.id.result_correct_image_view);
            ImageView responseIncorrectImageView = view.findViewById(R.id.result_incorrect_image_view);
            TextView option1TextView = view.findViewById(R.id.result_option_1_text_view);
            TextView option2TextView = view.findViewById(R.id.result_option_2_text_view);
            TextView option3TextView = view.findViewById(R.id.result_option_3_text_view);
            TextView option4TextView = view.findViewById(R.id.result_option_4_text_view);
            TextView resultTextView = view.findViewById(R.id.result_actual_response_text_view);



            int qid = arrayList1.get(i).getQuestionID();
            if (!ans.get(qid).equals(responseHashMap.get(qid))) {

                responseIncorrectImageView.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("Your answer: " + responseHashMap.get(qid) + " | Actual answer: " + ans.get(qid));
            } else {

                responseCorrectImageView.setVisibility(View.VISIBLE);

            }

            questionTextView.setText(arrayList1.get(i).getQuestionID() + ". " + arrayList1.get(i).getQuestion());

            String[] options = arrayList1.get(i).getOptions();

            option1TextView.setText("a. " + options[0]);
            option2TextView.setText("b. " + options[1]);
            option3TextView.setText("c. " + options[2]);
            option4TextView.setText("d. " + options[3]);

            parentLinearLayout.addView(view);

        }


    }

    /*void sorting() {

        sortedAnswerHashMap = new TreeMap<>();
        sortedAnswerHashMap.putAll(ans);

        sortedResponseHashMap = new TreeMap<>();
        sortedResponseHashMap.putAll(responseHashMap);
    }
*/
    void score() {



        Set<Integer> qIds = ans.keySet();
        Iterator<Integer> it = qIds.iterator();
        for (int i = 0; i < qIds.size(); i++) {
            Integer qid = it.next();
            if (ans.get(qid).equals(responseHashMap.get(qid)))
                score++;
            it.hasNext();
        }

    }


    private void addAnswers() {

        ans.put(1, "B.tech");
        ans.put(2, "ece");
        ans.put(3, "Thiruvananta");
        ans.put(4, "Jammu");

    }

    static class QuestionAndAnswers implements Serializable {

        public String question;
        String[] options;
        int questionID;
        String answer;

        QuestionAndAnswers() {

        }

        QuestionAndAnswers(String question, String[] options, int questionID, String answer) {


            this.question = question;
            this.options = options;
            this.questionID = questionID;
            this.answer = answer;
        }

        public String getQuestion() {

            return question;
        }

        public String[] getOptions() {

            return options;
        }

        public int getQuestionID() {

            return questionID;
        }

    }

    @Override
    public void onResume() {

        super.onResume();


        listeniers();

    }
    @Override
    public void onPause() {

        super.onPause();


    }

}