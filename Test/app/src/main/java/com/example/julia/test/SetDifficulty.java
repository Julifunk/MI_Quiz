package com.example.julia.test;

/**
 * Created by Marcus on 10.09.2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SetDifficulty extends Activity {

    private TextView easy;
    private TextView middle;
    private TextView difficult;
    private View.OnClickListener cL;
    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_choose_difficult);
        setupUIComponents();
        handleClicks();



    }

    private void handleClicks() {

        cL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.equals(easy)){
                    Intent i = new Intent(getApplicationContext(), Ques.class);
                    i.putExtra("set", "leicht");
                    i.putExtra("subject", getIntent().getStringExtra("subject"));
                    startActivity(i);
                }
                if(v.equals(middle)){
                    Intent i = new Intent(getApplicationContext(), Ques.class);
                    i.putExtra("set", "mittel");
                    i.putExtra("subject", getIntent().getStringExtra("subject"));
                    startActivity(i);
                }
                if(v.equals(middle)) {
                    Intent i = new Intent(getApplicationContext(), Ques.class);
                    i.putExtra("set", "schwer");
                    i.putExtra("subject", getIntent().getStringExtra("subject"));
                    startActivity(i);
                }
            }
        };
        easy.setOnClickListener(cL);
        middle.setOnClickListener(cL);
        difficult.setOnClickListener(cL);


    }

    private void setupUIComponents() {
        easy = (TextView)findViewById(R.id.easy);

        middle = (TextView)findViewById(R.id.middle);

        difficult = (TextView)findViewById(R.id.difficult);


    }


}
