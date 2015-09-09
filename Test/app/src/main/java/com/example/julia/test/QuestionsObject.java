package com.example.julia.test;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsObject {
    String option_a;
    String option_b;
    String option_c;
    String option_d;
    String correct_answer;

    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER_OPTIONS = "answerOptions";
    private static final String KEY_CORRECT_ANSWER = "correctAnwser";

    ParseObject parseObject;

    public QuestionsObject(ParseObject parseObject) {
        this.parseObject = parseObject;
        List<String> answerOptions = parseObject.getList(KEY_ANSWER_OPTIONS);
        this.option_a = answerOptions.get(0);
        this.option_b = answerOptions.get(1);
        this.option_c = answerOptions.get(2);
        this.option_d = answerOptions.get(3);
        this.correct_answer = parseObject.getString(KEY_CORRECT_ANSWER);


    }

    public String getQuestion(){
        String question= parseObject.getString(KEY_QUESTION);
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
