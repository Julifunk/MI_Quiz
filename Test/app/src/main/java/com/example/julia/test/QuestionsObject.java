package com.example.julia.test;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsObject {
    String question;
    String option_a;
    String option_b;
    String option_c;
    String option_d;
    String correct_answer;


    public QuestionsObject(String question, String option_a, String option_b, String option_c, String option_d, String correct_answer){
        this.question= question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct_answer = correct_answer;

    }

    public String getQuestion(){
        return question;
    }
    public String getOptionA(){
        return option_a;
    }
    public String getOptionB(){
        return option_b;
    }
    public String getOptionC(){
        return option_c;
    }
    public String getOptionD(){
        return option_d;
    }
    public String getCorrectAnswer(){
        return correct_answer;
    }

}
