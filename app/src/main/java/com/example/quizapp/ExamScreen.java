package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ExamScreen extends AppCompatActivity {


    EditText userInput;
    String userName;
    RadioGroup radioGroup;
    TextView question;
    RadioButton radioButton;
    ArrayList<ResultActivity.QuestionAndAnswers> arrayList = new ArrayList<>();
    LinearLayout linearLayoutHelloMsg;
    LinearLayout linearLayoutQuestions;
    LinearLayout linearLayoutSubmit;
    ArrayList<String> arrayList1 = new ArrayList<>();
    /*HashMap<Integer,String> ans;*/


    HashMap<Integer, String> updatedHashMap = new HashMap<Integer, String>();
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exam_screen);
        submitButton = findViewById(R.id.submit_button);
        dialouge_box();


    }

    public void setListners() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(updatedHashMap);
                /*System.out.println(ans);*/
                ResultActivity.QuestionAndAnswers q=new ResultActivity.QuestionAndAnswers();

                submitDialougeBox();


            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        setListners();


    }

    void helloText() {

        /*TextView textView1 = new TextView(this);
        LinearLayout l=findViewById(R.id.inner);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.weight = 1;
        textView1.setLayoutParams(params1);
        textView1.setText(userName);
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
        linearLayoutHelloMsg.addView(textView1);*/

        linearLayoutHelloMsg = findViewById(R.id.hello_layout);
        TextView helloTextView = findViewById(R.id.hello_text_view);
        helloTextView.setText(userName);

        /*TextView textView2 = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        textView2.setLayoutParams(layoutParams);
        textView2.setText(userName);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //textView2.setBackgroundColor(0xffffdbdb);
        linearLayoutHelloMsg.addView(textView2);*/

        exam();


    }

    void submitDialougeBox () {


        Context context = this;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        alertDialogBuilder.setTitle("Final Submission ");

        alertDialogBuilder
                .setMessage("Are you sure want to Submit?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                       // dialog.cancel();

                        Set<Integer> set=updatedHashMap.keySet();
                        int l=set.size();
                        System.out.println(l);

                        if (set.size() == arrayList.size()) {

                            Intent intent = new Intent(ExamScreen.this,ResultActivity.class);
                            intent.putExtra("responses",updatedHashMap);
                            /*intent.putExtra("answers",ans);*/
                            intent.putExtra("question",arrayList);
                            intent.putExtra("username",userInput.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                        else {
                            dialog.cancel();
                        }



                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();
    }



    void dialouge_box() {


        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View promptsView = li.inflate(R.layout.dialouge_box, null);
        userInput = (EditText) promptsView.findViewById(R.id.etUserInput);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Toast.makeText(getApplicationContext(), "Entered: " + userInput.getText().toString(), Toast.LENGTH_LONG).show();
                        userName = "Hello " + userInput.getText().toString() + " ,";

                        helloText();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void addElements() {

        arrayList.add(new ResultActivity.QuestionAndAnswers("What is your qualification ?", new String[]{"B.tech", "M.Tech", "Mba", "Diploma"}, 1,"B.tech"));
        arrayList.add(new ResultActivity.QuestionAndAnswers("What is your stream ?", new String[]{"ece", "eee", "mech", "cse"}, 2,"ece"));
        arrayList.add(new ResultActivity.QuestionAndAnswers("What is the capital of  Kerala?", new String[]{"Thiruvananta", "Kolkata", "Mumbai", "Bhopal"}, 3,"Thiruvanantapuram"));
        arrayList.add(new ResultActivity.QuestionAndAnswers("What is the capital of Madhya Pradesh?", new String[]{"Jammu", "Bhopal", "Kota", "Srinagar"}, 4,"Jammu"));
        //arrayList.add(new QuestionAndAnswers("What is your qualification ?", new String[]{"B.tech", "M.Tech", "Mba", "Diploma"}, 1));
        //arrayList.add(new QuestionAndAnswers("What is your stream ?", new String[]{"ece", "eee", "mech", "cse"}, 2));
        /*arrayList.add(new QuestionAndAnswers("What is the capital of the god's own country Kerala?", new String[]{"Thiruvanantapuram", "Kolkata", "Mumbai", "Bhopal"}, 3));
        arrayList.add(new QuestionAndAnswers("What is the capital of India's heart, Madhya Pradesh?", new String[]{"Jammu", "Bhopal", "Kota", "Srinagar"}, 4));*/


    }


    void exam() {


        addElements();

        //linearLayoutQuestions = findViewById(R.id.question_layout); //new

        LinearLayout parentLinearLayout = findViewById(R.id.question_layout);

        for (int i = 0; i < arrayList.size(); i++) {


            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.exam_screen_questions, null);

            final TextView questionTextView = view.findViewById(R.id.exam_question_text_view1);
            final RadioGroup radioGroup = view.findViewById(R.id.questions_radiogroup1);
            RadioButton questionOption1 = view.findViewById(R.id.question_option_1_radiobutton);
            RadioButton questionOption2 = view.findViewById(R.id.question_option_2_radiobutton);
            RadioButton questionOption3 = view.findViewById(R.id.question_option_3_radiobutton);
            RadioButton questionOption4 = view.findViewById(R.id.question_option_4_radiobutton);
            questionTextView.setText(arrayList.get(i).getQuestionID() + ".  " + arrayList.get(i).getQuestion());
            String options[] = arrayList.get(i).getOptions();
            questionOption1.setText(options[0]);
            questionOption2.setText(options[1]);
            questionOption3.setText(options[2]);
            questionOption4.setText(options[3]);
            parentLinearLayout.addView(view);





            /*final TextView question = new TextView(this);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
            question.setLayoutParams(layoutParams);
            question.setTextColor(Color.parseColor("#bdbdbd"));
            question.setText(arrayList.get(i).getQuestionID() + ".  " + arrayList.get(i).getQuestion());
            question.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            final RadioGroup radioGroup = new RadioGroup(this);
            LayoutParams layoutParams1 = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            layoutParams1.setMargins(10, 10, 10, 10);
            radioGroup.setLayoutParams(layoutParams1);

            radioGroup.setTag(409);
            LinearLayout childLayout = new LinearLayout(this);
            childLayout.setOrientation(LinearLayout.VERTICAL);
            childLayout.setLayoutParams(layoutParams);
            childLayout.addView(question);*/

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    String a = (String) questionTextView.getText();
                    char a1 = a.charAt(0);
                    int a2 = Integer.parseInt(String.valueOf(a1));

                    int id1 = radioGroup.getCheckedRadioButtonId();
                    if (id1 != -1) {
                        RadioButton b = (RadioButton) radioGroup.findViewById(id1);
                        CharSequence s = b.getText();
                        updatedHashMap.put(a2, s.toString());

                    }
                }
            });



            /*String options[] = arrayList.get(i).getOptions();
            RadioButton[] radioButtons=new RadioButton[4];
            for (int j = 0; j < arrayList.get(i).getOptions().length; j++) {

                radioButtons[j] = new RadioButton(this);
                radioButtons[j].setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                radioButtons[j].setText(options[j]);
                radioButtons[j].setTextColor(Color.parseColor("#bdbdbd"));
                radioButtons[j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                radioGroup.addView(radioButtons[j]);
            }
            childLayout.addView(radioGroup);
            linearLayoutQuestions.addView(childLayout);*/

        }
    }

    /*class QuestionAndAnswers implements Serializable {

        public String question;
        String[] options;
        int questionID;
        String answer;
        QuestionAndAnswers()
        {

        }
        QuestionAndAnswers( String question,String[] options ,int questionID,String answer )  {


            this.question = question;
            this.options = options;
            this.questionID = questionID;
            this.answer = answer;
        }
        public  String getQuestion() {

            return question;
        }
        public String[] getOptions() {

            return options;
        }
        public int getQuestionID() {

            return questionID;
        }
        public HashMap<Integer,String> getAnswers() {

            HashMap<Integer,String> hashMap = new HashMap<>();
            for (int i = 0;i < arrayList.size();i++) {

                hashMap.put(arrayList.get(i).questionID,arrayList.get(i).answer);
            }

            return hashMap;
        }

    }*/

}


