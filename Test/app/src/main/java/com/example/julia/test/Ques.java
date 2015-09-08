package com.example.julia.test;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julia on 19.08.2015.
 */
public class Ques extends ActionBarActivity implements QuestionsDataProvider.QuestionsDataProviderListener {

    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;
    private int counterOfAnsweredQuestions;
    private int counterOfCorrectlyAnsweredQuestions;
    private String subject;

    ArrayList<QuestionsObject> correctlyAnsweredQuestions = new ArrayList<>();
    ArrayList<QuestionsObject> questions = new ArrayList<>();

    int currentQuestion = 1;

    QuestionsDataProvider questionsDataProvider = new QuestionsDataProvider();


    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_questions);
        getUIReferences();
        subject = getIntent().getStringExtra("subject");
        String set = getIntent().getStringExtra("set");

        questionsDataProvider = new QuestionsDataProvider();
        questionsDataProvider.setQuestionsDataProviderListener(this);
        questionsDataProvider.getQuestionsFromSubject(subject);
    }

    private void getUIReferences() {
        question = (TextView) findViewById(R.id.question);
        option_a = (TextView) findViewById(R.id.option_a);
        option_b = (TextView) findViewById(R.id.option_b);
        option_c = (TextView) findViewById(R.id.option_c);
        option_d = (TextView) findViewById(R.id.option_d);



        option_a.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override

            public void onClick(View view) {
                if (isCorrectAnswer(option_a.getText().toString())) {
                    correctlyAnsweredQuestions.add(questions.get(currentQuestion - 1));
                    counterOfCorrectlyAnsweredQuestions++;
                    option_a.setBackground(getDrawable(R.drawable.progress_bar_positive));
                    }
                else{
                    option_a.setBackground(getDrawable(R.drawable.progress_bar_positive));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_a.setBackgroundColor(Color.WHITE);
                        currentQuestion++;
                        setTextFieldWithQuestionAtIndex(currentQuestion);
                        counterOfAnsweredQuestions++;
                    }
                }, 500);

            }
        });


        option_b.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (isCorrectAnswer(option_b.getText().toString())) {
                    correctlyAnsweredQuestions.add(questions.get(currentQuestion - 1));
                    counterOfCorrectlyAnsweredQuestions++;
                    option_b.setBackground(getDrawable(R.drawable.progress_bar_positive));
                }
                else{
                    option_b.setBackground(getDrawable(R.drawable.progress_bar_negative));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_b.setBackgroundColor(Color.WHITE);
                        currentQuestion++;
                        setTextFieldWithQuestionAtIndex(currentQuestion);
                        counterOfAnsweredQuestions++;
                    }
                }, 500);

            }
        });

        option_c.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override

            public void onClick(View view) {
                if (isCorrectAnswer(option_c.getText().toString())) {
                    correctlyAnsweredQuestions.add(questions.get(currentQuestion - 1));
                    counterOfCorrectlyAnsweredQuestions++;
                    option_c.setBackground(getDrawable(R.drawable.progress_bar_positive));
                }
                else{
                    option_c.setBackground(getDrawable(R.drawable.progress_bar_negative));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_c.setBackgroundColor(Color.WHITE);
                        currentQuestion++;
                        setTextFieldWithQuestionAtIndex(currentQuestion);
                        counterOfAnsweredQuestions++;
                    }
                }, 500);

            }
        });

        option_d.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override

            public void onClick(View view) {
                if (isCorrectAnswer(option_d.getText().toString())) {
                    correctlyAnsweredQuestions.add(questions.get(currentQuestion - 1));
                    counterOfCorrectlyAnsweredQuestions++;
                    option_d.setBackground(getDrawable(R.drawable.progress_bar_positive));
                }
                else{
                    option_d.setBackground(getDrawable(R.drawable.progress_bar_negative));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_d.setBackgroundColor(Color.WHITE);
                        currentQuestion++;
                        setTextFieldWithQuestionAtIndex(currentQuestion);
                        counterOfAnsweredQuestions++;
                    }
                }, 500);

            }
        });


    }

    private void setTextFieldWithQuestionAtIndex(int index) {
        if (index > questions.size()) {
            Intent i = new Intent(getApplicationContext(), Statistics.class);
            i.putExtra("NumberQuestions", counterOfAnsweredQuestions );
            i.putExtra("CorrectAnswers", counterOfCorrectlyAnsweredQuestions);
            i.putExtra("Subject", subject);
            startActivity(i);
            return;
        }
        QuestionsObject currentQuestion = this.questions.get(index - 1);
        question.setText(currentQuestion.getQuestion());
        option_a.setText(currentQuestion.getOptionA());
        option_b.setText(currentQuestion.getOptionB());
        option_c.setText(currentQuestion.getOptionC());
        option_d.setText(currentQuestion.getOptionD());
    }

    private boolean isCorrectAnswer(String selectedAnswer) {
        return selectedAnswer.equals(getAnswerOfCurrentQuestion());
    }

    private String getAnswerOfCurrentQuestion() {
        QuestionsObject currentQuestion = questions.get(this.currentQuestion-1);
        return currentQuestion.getCorrectAnswer();
    }


    // Interface Methods
    @Override
    public void onQuestionsDownloaded(ArrayList<QuestionsObject> questions) {
        this.questions = questions;
        System.out.println(this.questions.size());
        setTextFieldWithQuestionAtIndex(this.currentQuestion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(),Statistics.class);
            i.putExtra("NumberQuestions", counterOfAnsweredQuestions );
            i.putExtra("CorrectAnswers", counterOfCorrectlyAnsweredQuestions);
            i.putExtra("Subject", subject);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
