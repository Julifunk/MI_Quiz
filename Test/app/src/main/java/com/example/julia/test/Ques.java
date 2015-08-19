package com.example.julia.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.julia.test.R;

/**
 * Created by Julia on 19.08.2015.
 */
public class Ques extends ActionBarActivity {

    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;

    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView (R.layout.activity_questions);

        question = (TextView) findViewById(R.id.question);
        option_a = (TextView) findViewById(R.id.option_a);
        option_b = (TextView) findViewById(R.id.option_c);
        option_d = (TextView) findViewById(R.id.option_d);
    }
}
