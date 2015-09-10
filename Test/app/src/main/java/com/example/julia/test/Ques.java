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
import java.util.Random;

/**
 * Created by Julia on 19.08.2015.
 */
public class Ques extends ActionBarActivity implements QuestionsDataProvider.QuestionsDataProviderListener {

    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;
    private String  correctAnswer;
    private int counterOfAnsweredQuestions;
    private int counterOfCorrectAnswers;
    private String subject;
    private String set;

    ArrayList<QuestionsObject> questions = new ArrayList<>();
    Random rGen = new Random();
    int currentQuestion = 1;
    int randomIndex = 1;


    QuestionsDataProvider questionsDataProvider = new QuestionsDataProvider();


    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_questions);
        getUIReferences();
        subject = getIntent().getStringExtra("subject");
        set = getIntent().getStringExtra("set");

        questionsDataProvider = new QuestionsDataProvider();
        questionsDataProvider.setQuestionsDataProviderListener(this);
        questionsDataProvider.getQuestionsFromSubjectAndSet(subject, set);

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

                    counterOfCorrectAnswers++;
                    option_a.setBackground(getDrawable(R.drawable.progress_bar_positive));
                    }
                else{

                    option_a.setBackground(getDrawable(R.drawable.progress_bar_negative));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_a.setBackgroundColor(Color.WHITE);
                        currentQuestion++;
                        setTextFieldWithQuestionAtIndex();
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

                    counterOfCorrectAnswers++;
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
                        setTextFieldWithQuestionAtIndex();
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

                    counterOfCorrectAnswers++;
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
                        setTextFieldWithQuestionAtIndex();
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

                    counterOfCorrectAnswers++;
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
                        setTextFieldWithQuestionAtIndex();
                        counterOfAnsweredQuestions++;
                    }
                }, 500);

            }
        });


    }

    private void setTextFieldWithQuestionAtIndex() {
        if (questions.size() < 1) {
            Intent i = new Intent(getApplicationContext(), Statistics.class);
            i.putExtra("NumberQuestions", counterOfAnsweredQuestions );
            i.putExtra("CorrectAnswers", counterOfCorrectAnswers);
            i.putExtra("Subject", subject);
            startActivity(i);
            return;
        }
        rGen = new Random();

        if(this.questions.size()>0) {
            randomIndex = rGen.nextInt(this.questions.size());
        }
        QuestionsObject currentQuestion = this.questions.get(randomIndex);
        question.setText(currentQuestion.getQuestion());
        option_a.setText(currentQuestion.getOptionA());
        option_b.setText(currentQuestion.getOptionB());
        option_c.setText(currentQuestion.getOptionC());
        option_d.setText(currentQuestion.getOptionD());
        correctAnswer = currentQuestion.getCorrectAnswer();
        System.out.println("Korrekte Antwort von currentQuestion:       " + correctAnswer);
        questions.remove(randomIndex);
        System.out.println(this.questions.size());
    }





    private boolean isCorrectAnswer(String selectedAnswer) {
        return selectedAnswer.equals(correctAnswer);
    }


    // Interface Methods
    @Override
    public void onQuestionsDownloaded(ArrayList<QuestionsObject> questions) {
        this.questions = questions;
        System.out.println(this.questions.size());
        setTextFieldWithQuestionAtIndex();
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
            i.putExtra("CorrectAnswers", counterOfCorrectAnswers);
            i.putExtra("Subject", subject);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
