package com.example.julia.test;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsDataProvider {

    private ArrayList<QuestionsObject> questionsList = new ArrayList<QuestionsObject>();

    public QuestionsDataProvider(ArrayList<QuestionsObject> questionsList) {
        this.questionsList = questionsList;
    }


}
