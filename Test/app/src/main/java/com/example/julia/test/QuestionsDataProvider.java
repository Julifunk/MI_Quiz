package com.example.julia.test;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsDataProvider {

    private QuestionsDataProviderListener listener;

    public QuestionsDataProvider() {

    }

    public void setQuestionsDataProviderListener(QuestionsDataProviderListener listener) {
        this.listener = listener;
    }

    public void getQuestionsFromSubject(String subject) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.whereEqualTo("subject", subject);
        query.orderByAscending("objectId");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    listener.onQuestionsDownloaded(parseQuestionsAsArrayList(list));
                }
            }
        });
    }

    public void getQuestionsFromSubjectAndSet(String subject, String set) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.whereEqualTo("subject", subject);
        query.whereEqualTo("set", set);
        query.orderByAscending("objectId");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    listener.onQuestionsDownloaded(parseQuestionsAsArrayList(list));
                }
            }
        });
    }

    private ArrayList<QuestionsObject> parseQuestionsAsArrayList(List<ParseObject> parseQuestions) {
        ArrayList<QuestionsObject> questions = new ArrayList<>();

        for (ParseObject object : parseQuestions) {
            questions.add(new QuestionsObject(object));
        }
        System.out.println(questions.size());
        return questions;
    }



    public interface QuestionsDataProviderListener {
        void onQuestionsDownloaded(ArrayList<QuestionsObject> questions);
    }


}
