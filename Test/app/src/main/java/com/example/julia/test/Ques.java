package com.example.julia.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 19.08.2015.
 */
public class Ques extends ActionBarActivity implements QuestionsDataProvider.QuestionsDataProviderListener {

    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;
    public int counterOfAnsweredQuestions;
    public int counterOfCorrectlyAnsweredQuestion;

    ArrayList<QuestionsObject> correctlyAnsweredQuestions = new ArrayList<>();
    ArrayList<QuestionsObject> questions = new ArrayList<>();

    int currentQuestion = 1;

    QuestionsDataProvider questionsDataProvider = new QuestionsDataProvider();


    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_questions);
        getUIReferences();
        String subject = getIntent().getStringExtra("subject");
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
                    counterOfCorrectlyAnsweredQuestion++;
                    option_a.setBackground(getDrawable(R.drawable.background_right_answer));
                    }
                else{
                    option_a.setBackground(getDrawable(R.drawable.background_wrong_answer));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_a.setBackground(getDrawable(R.drawable.background_answer));
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
                    counterOfCorrectlyAnsweredQuestion++;
                    option_b.setBackground(getDrawable(R.drawable.background_right_answer));
                }
                else{
                    option_b.setBackground(getDrawable(R.drawable.background_wrong_answer));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_b.setBackground(getDrawable(R.drawable.background_answer));
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
                    counterOfCorrectlyAnsweredQuestion++;
                    option_c.setBackground(getDrawable(R.drawable.background_right_answer));
                }
                else{
                    option_c.setBackground(getDrawable(R.drawable.background_wrong_answer));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_c.setBackground(getDrawable(R.drawable.background_answer));
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
                    counterOfCorrectlyAnsweredQuestion++;
                    option_d.setBackground(getDrawable(R.drawable.background_right_answer));
                }
                else{
                    option_d.setBackground(getDrawable(R.drawable.background_wrong_answer));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        option_d.setBackground(getDrawable(R.drawable.background_answer));
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
            // nächste Activity
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
}
