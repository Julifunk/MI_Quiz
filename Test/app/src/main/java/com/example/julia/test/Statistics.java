package com.example.julia.test;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by Julia on 06.09.2015.
 */
public class Statistics extends ActionBarActivity{
    private int numberOfAnsweredQuestions;
    private int numberOfcorrectlyAnsweredQuestions;
    private ImageView correctAnswers;
    private ImageView wrongAnswers;
    private Display display;
    private TextView subject;
    private TextView percentage;
    private TextView backToSets;
    private TextView backToSubjects;
    private View.OnClickListener cL;

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_statistics);
        setupUIComponents();
        getStatisticsFromQuiz();
        setTextView();
        handleClicks();

    }

    private void setupUIComponents() {
        display = getWindowManager().getDefaultDisplay();
        correctAnswers = (ImageView) findViewById(R.id.progress);
        wrongAnswers = (ImageView) findViewById(R.id.missing);
        subject = (TextView)findViewById(R.id.Subject);
        percentage = (TextView)findViewById(R.id.Percentage);
        backToSets = (TextView)findViewById(R.id.back_to_sets);
        backToSubjects = (TextView)findViewById(R.id.back_to_subjects);


    }

    private void handleClicks() {
          cL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               if(v.equals(backToSets)){
                   Intent i = new Intent(getApplicationContext(), SetDifficulty.class);
                   startActivity(i);
               }
               if(v.equals(backToSubjects)){
                   Intent i = new Intent(getApplicationContext(), StartingGameActivity.class);
                   startActivity(i);
               }
            }
        };

        backToSets.setOnClickListener(cL);
        backToSubjects.setOnClickListener(cL);

    }

    private void setTextView() {
        subject.setText(getIntent().getStringExtra("Subject")+ ": ");
        if(numberOfAnsweredQuestions != 0) {
            percentage.setText(" " + numberOfcorrectlyAnsweredQuestions * 100 / numberOfAnsweredQuestions + "%");
        }
        else{
            percentage.setText(R.string.error_no_answerded_questions);

        }

    }

    private void getStatisticsFromQuiz() {
       numberOfcorrectlyAnsweredQuestions = getIntent().getIntExtra("CorrectAnswers", 0);
        System.out.println("Anzahl richtig beantwortete Fragen: " + numberOfcorrectlyAnsweredQuestions);
       numberOfAnsweredQuestions = getIntent().getIntExtra("NumberQuestions", 0);
        System.out.println("Anzahl Fragen:  " + numberOfAnsweredQuestions);
        correctAnswers.requestLayout();
        wrongAnswers.requestLayout();
        if(numberOfAnsweredQuestions != 0) {
            correctAnswers.getLayoutParams().width = Integer.valueOf(display.getWidth() * numberOfcorrectlyAnsweredQuestions / numberOfAnsweredQuestions);
        }
        else{
            percentage.setText(R.string.error_no_answerded_questions);
        }
    }
}
