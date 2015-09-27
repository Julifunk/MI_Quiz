package com.example.julia.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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

    private String sets;
    private String subjects;
    private String rating;
    private StatsDatabase db;

    private View.OnClickListener cL;

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_statistics);
        openDB();
        setupUIComponents();
        getStatisticsFromQuiz();
        setTextView();
        rating = percentage.getText().toString();
        sets = getIntent().getStringExtra("set");
        subjects = getIntent().getStringExtra("subject");
        if(!db.rowExists(subjects, sets)) {
            db.insertRow(subjects, sets, rating);
        }
        else {
            db.updateRow(subjects, sets, rating);
        }
        handleClicks();

    }

    private void openDB() {
        db = new StatsDatabase(this);
        db.open();
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
                   i.putExtra("subject", getIntent().getStringExtra("subject"));
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
        subject.setText(getIntent().getStringExtra("subject")+ ": ");
        if(numberOfAnsweredQuestions != 0) {
            percentage.setText(" " + numberOfcorrectlyAnsweredQuestions * 100 / numberOfAnsweredQuestions + "%");
        }
        else{
            percentage.setText(R.string.error_no_answerded_questions);

        }
    }

    //getting correctly and alltogether answered questions from intent
    //calculating percentage
    //setting Imageview accordingly
    private void getStatisticsFromQuiz() {
       numberOfcorrectlyAnsweredQuestions = getIntent().getIntExtra("correctAnswers", 0);
       numberOfAnsweredQuestions = getIntent().getIntExtra("numberQuestions", 0)-1;
       correctAnswers.requestLayout();
       wrongAnswers.requestLayout();
       if(numberOfAnsweredQuestions != 0) {
           correctAnswers.getLayoutParams().width = (display.getWidth() * numberOfcorrectlyAnsweredQuestions / numberOfAnsweredQuestions);
       }
       else{
           percentage.setText(R.string.error_no_answerded_questions);
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_statistics) {
            Intent i = new Intent(getApplicationContext(), OverallStatistics.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
