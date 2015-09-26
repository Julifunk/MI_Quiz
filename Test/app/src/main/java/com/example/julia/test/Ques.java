package com.example.julia.test;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
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
    private TextView shownQuestion;
    private String correctAnswer;
    private int counterOfAnsweredQuestions;
    private int counterOfCorrectAnswers;
    private String subject;
    private ProgressBar downloadProgress;
    private String set;
    private int initialNumberOfQuestionsInList;
    private View.OnClickListener cL;


    ArrayList<QuestionsObject> questions = new ArrayList<QuestionsObject>();
    Random rGen = new Random();
    int currentQuestion = 1;
    int randomIndex = 1;


    QuestionsDataProvider questionsDataProvider = new QuestionsDataProvider();


    @Override
    protected void onCreate(Bundle saveInstanceState) {
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
        downloadProgress = (ProgressBar)findViewById(R.id.progressBar);
        shownQuestion= (TextView) findViewById(R.id.current_question);

    }

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
                            shownQuestion.setText(""+(counterOfAnsweredQuestions + 1)+"/" + initialNumberOfQuestionsInList);
                        }
                    }, 1000);




            };
        };}

    private void showCorrectAnswer() {
        int delay = 300;
            ColorDrawable frame1 = new ColorDrawable(getResources().getColor(R.color.GrünHell));
            ColorDrawable frame2 = new ColorDrawable(getResources().getColor(R.color.GrünDunkler));
            AnimationDrawable a = new AnimationDrawable();
            a.addFrame(frame1, delay);
            a.addFrame(frame2, delay);
            a.setOneShot(true);

            if (isCorrectAnswer(option_a.getText().toString())) {
                option_a.setBackgroundDrawable(a);
                a.start();
            } else if (isCorrectAnswer(option_b.getText().toString())) {
                 option_b.setBackgroundDrawable(a);
                 a.start();
            } else if (isCorrectAnswer(option_c.getText().toString())) {
                 option_c.setBackgroundDrawable(a);
                 a.start();
            } else if (isCorrectAnswer(option_d.getText().toString())) {
                 option_d.setBackgroundDrawable(a);
                 a.start();
            }
    }


            //gets a random Question from ArrayList<QuestionsObject> and removes it after usage
            private void setTextFieldWithQuestionAtIndex() {
                if (questions.size() < 1) {
                    Intent i = new Intent(getApplicationContext(), Statistics.class);
                    i.putExtra("numberQuestions", counterOfAnsweredQuestions);
                    i.putExtra("correctAnswers", counterOfCorrectAnswers);
                    i.putExtra("subject", subject);
                    i.putExtra("set", set);
                    startActivity(i);
                    return;
                }
                rGen = new Random();
                if (questions.size() > 0) {
                    randomIndex = rGen.nextInt(questions.size());
                }
                QuestionsObject currentQuestion = questions.get(randomIndex);
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
                initialNumberOfQuestionsInList = questions.size();
                shownQuestion.setText(""+(counterOfAnsweredQuestions + 1)+"/" + initialNumberOfQuestionsInList);
                System.out.println(questions.size());
                downloadProgress.setVisibility(View.INVISIBLE);
                setTextFieldWithQuestionAtIndex();
                option_a.setOnClickListener(cL);
                option_b.setOnClickListener(cL);
                option_c.setOnClickListener(cL);
                option_d.setOnClickListener(cL);
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
                    Intent i = new Intent(getApplicationContext(), Statistics.class);
                    i.putExtra("numberQuestions", counterOfAnsweredQuestions);
                    i.putExtra("correctAnswers", counterOfCorrectAnswers);
                    i.putExtra("subject", subject);
                    i.putExtra("set", set);
                    startActivity(i);
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
        }

