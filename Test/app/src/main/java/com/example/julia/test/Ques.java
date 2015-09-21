package com.example.julia.test;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
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
    private View.OnClickListener cL;

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
        setupDataProvider();
        handleUserInput();


    }

    private void setupDataProvider() {
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
    }

   //will detect which answer User has clicked and if it is right or wrong
    private void handleUserInput() {
        cL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(option_a)) {
                    if (isCorrectAnswer(option_a.getText().toString())) {
                        counterOfCorrectAnswers++;
                        option_a.setBackgroundColor(getResources().getColor(R.color.GrünDunkler));
                    } else {
                        option_a.setBackgroundColor(getResources().getColor(R.color.HeidenelkenrotMittelhell));
                    }
                }

                if (v.equals(option_b)) {
                    if (isCorrectAnswer(option_b.getText().toString())) {
                        counterOfCorrectAnswers++;
                        option_b.setBackgroundColor(getResources().getColor(R.color.GrünDunkler));
                    } else {
                        option_b.setBackgroundColor(getResources().getColor(R.color.HeidenelkenrotMittelhell));
                    }

                }
                if (v.equals(option_c)) {
                    if (isCorrectAnswer(option_c.getText().toString())) {
                        counterOfCorrectAnswers++;
                        option_c.setBackgroundColor(getResources().getColor(R.color.GrünDunkler));
                    } else {

                        option_c.setBackgroundColor(getResources().getColor(R.color.HeidenelkenrotMittelhell));
                    }
                }
                if (v.equals(option_d)) {
                    if (isCorrectAnswer(option_d.getText().toString())) {
                        counterOfCorrectAnswers++;
                        option_d.setBackgroundColor(getResources().getColor(R.color.GrünDunkler));
                    } else {
                        option_d.setBackgroundColor(getResources().getColor(R.color.HeidenelkenrotMittelhell));
                    }
                }
                showCorrectAnswer();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       option_a.setBackgroundColor(Color.WHITE);
                       option_b.setBackgroundColor(Color.WHITE);
                       option_c.setBackgroundColor(Color.WHITE);
                       option_d.setBackgroundColor(Color.WHITE);
                       currentQuestion++;
                       setTextFieldWithQuestionAtIndex();
                       counterOfAnsweredQuestions++;
                    }
                }, 800);
            }
        };
        option_a.setOnClickListener(cL);
        option_b.setOnClickListener(cL);
        option_c.setOnClickListener(cL);
        option_d.setOnClickListener(cL);

    }

    private void showCorrectAnswer() {
        int delay = 300;
        ColorDrawable f = new ColorDrawable(getResources().getColor(R.color.GrünHell));
        ColorDrawable f2 = new ColorDrawable(getResources().getColor(R.color.GrünDunkler));
              AnimationDrawable a = new AnimationDrawable();
        a.addFrame(f, delay);
        a.addFrame(f2, delay);
        a.setOneShot(false);



        if(isCorrectAnswer(option_a.getText().toString())){
            option_a.setBackgroundDrawable(a); // This method is deprecated in API 16
            // fondo.setBackground(a); // Use this method if you're using API 16
            a.start();

        }

        else if(isCorrectAnswer(option_b.getText().toString())){
            option_b.setBackgroundDrawable(a);

        }
        else if(isCorrectAnswer(option_c.getText().toString())){
            option_c.setBackgroundDrawable(a);
        }

        else if(isCorrectAnswer(option_d.getText().toString())){
            option_d.setBackgroundDrawable(a);
        }
    }


    //gets a random Question from ArrayList<QuestionsObject> and removes it after usage
    private void setTextFieldWithQuestionAtIndex() {
        if (questions.size() < 1) {
            Intent i = new Intent(getApplicationContext(), Statistics.class);
            i.putExtra("numberQuestions", counterOfAnsweredQuestions );
            i.putExtra("correctAnswers", counterOfCorrectAnswers);
            i.putExtra("subject", subject);
            startActivity(i);
            return;
        }
        rGen = new Random();
        if(this.questions.size()>0) {
            randomIndex = rGen.nextInt(this.questions.size());
        }
        QuestionsObject currentQuestion = this.questions.get(randomIndex);
        displayQuestion(currentQuestion);
        questions.remove(randomIndex);
       }

    //displays the current question
    private void displayQuestion(QuestionsObject currentQuestion) {
        question.setText(currentQuestion.getQuestion());
        option_a.setText(currentQuestion.getOptionA());
        option_b.setText(currentQuestion.getOptionB());
        option_c.setText(currentQuestion.getOptionC());
        option_d.setText(currentQuestion.getOptionD());
        correctAnswer = currentQuestion.getCorrectAnswer();

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
            i.putExtra("numberQuestions", counterOfAnsweredQuestions );
            i.putExtra("correctAnswers", counterOfCorrectAnswers);
            i.putExtra("subject", subject);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
