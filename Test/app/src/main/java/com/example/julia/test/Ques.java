package com.example.julia.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Julia on 19.08.2015.
 */
public class Ques extends ActionBarActivity {

    private TextView question;
    private TextView option_a;
    private TextView option_b;
    private TextView option_c;
    private TextView option_d;

    private ParseObject parseObject;

    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView (R.layout.activity_questions);

        String objectId = getIntent().getStringExtra("objectId");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Binar_HexaOperationen");
        query.fromLocalDatastore();
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject parseObject, ParseException e) {

                question = (TextView) findViewById(R.id.question);
                option_a = (TextView) findViewById(R.id.option_a);
                option_b = (TextView) findViewById(R.id.option_b);
                option_c = (TextView) findViewById(R.id.option_c);
                option_d = (TextView) findViewById(R.id.option_d);

                option_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView response = (TextView) findViewById(R.id.response);
                        if (option_d.getText().toString().equals(parseObject.getString("correctAnswer"))) {
                            response.setText("Correct");
                        }
                    }
                });

                List answerOptions = parseObject.getList("answerOptions");
                question.setText(parseObject.getString("question"));
                option_a.setText(answerOptions.get(0).toString());
                option_b.setText(parseObject.getList("answerOptions").get(1).toString());
                option_c.setText(parseObject.getList("answerOptions").get(2).toString());
                option_d.setText(parseObject.getList("answerOptions").get(3).toString());


                parseObject.unpinInBackground();
            }
        });




    }



}
