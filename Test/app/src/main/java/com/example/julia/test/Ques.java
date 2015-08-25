package com.example.julia.test;

import android.os.Bundle;
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
    private int counter;

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
        questionsDataProvider.getQuestionsFromSubjectAndSet(subject, set);
    }

    private void getUIReferences() {
        question = (TextView) findViewById(R.id.question);
        option_a = (TextView) findViewById(R.id.option_a);
        option_b = (TextView) findViewById(R.id.option_b);
        option_c = (TextView) findViewById(R.id.option_c);
        option_d = (TextView) findViewById(R.id.option_d);


        option_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCorrectAnswer(option_a.getText().toString())) {
                   correctlyAnsweredQuestions.add(questions.get(currentQuestion - 1));
                }

                currentQuestion++;
                setTextFieldWithQuestionAtIndex(currentQuestion);
            }
        });


        option_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        option_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        option_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
        QuestionsObject currentQuestion = questions.get(this.currentQuestion);
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
