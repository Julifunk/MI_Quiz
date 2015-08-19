package com.example.julia.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julia on 19.08.2015.
 */
public class QuestionsAdapter extends ArrayAdapter<QuestionsObject> {
    private ArrayList<QuestionsObject> questions;
    private Context context;

    public QuestionsAdapter(Context context, ArrayList<QuestionsObject> questions) {
        super(context, R.layout.activity_questions, questions);
        this.context = context;
        this.questions = questions;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_questions, null);

        }

        QuestionsObject ques = questions.get(position);

        if (ques != null) {
            TextView question = (TextView) v.findViewById(R.id.question);
            TextView option_a = (TextView) v.findViewById(R.id.option_a);
            TextView option_b = (TextView) v.findViewById(R.id.option_b);
            TextView option_c = (TextView) v.findViewById(R.id.option_c);
            TextView option_d = (TextView) v.findViewById(R.id.option_d);


        }

        return v;
    }
}
