package com.example.julia.test;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsDataProvider {

    public ArrayList<String> questionsList = new ArrayList<>();

    public QuestionsDataProvider (ArrayList<String> questionsList, Context context) {
        this.questionsList = questionsList;
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, "BUfWzIhsninLF29zGCoEz7puv93amubRhTUmfY63", "L36y1hNcNkMI5qVmz8ytD8DsrqnSwrYzmi9gmCYM");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Binar_HexaOperationen");
        query.whereExists("question");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List <ParseObject> list, ParseException e) {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ParseObject p = list.get(i);
                        String question = p.getString("question");


                        }}
            }});


    }

    public ArrayList<String> getQuestionsList() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+questionsList.size());
        return questionsList;
    }

















}
