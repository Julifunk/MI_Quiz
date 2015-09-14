package com.example.julia.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

    //setting clickable Layout-Items on Clicklistener
    //handling Clicks
    private void handleClicks() {
          cL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               if(v.equals(backToSets)){
                   Intent i = new Intent(getApplicationContext(), SetDifficulty.class);
                   i.putExtra("subject", subject.getText());
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

    //setting up the rating-bar and the percentage
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
       numberOfAnsweredQuestions = getIntent().getIntExtra("NumberQuestions", 0);
       correctAnswers.requestLayout();
       wrongAnswers.requestLayout();
       if(numberOfAnsweredQuestions != 0) {
           correctAnswers.getLayoutParams().width = (display.getWidth() * numberOfcorrectlyAnsweredQuestions / numberOfAnsweredQuestions);
       }
       else{
           percentage.setText(R.string.error_no_answerded_questions);
       }
    }
}
