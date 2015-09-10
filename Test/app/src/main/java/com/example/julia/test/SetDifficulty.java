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



    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_choose_difficult);
        setupLayoutElements();

    }

    private void setupLayoutElements(){

        easy = (TextView) findViewById(R.id.easy);
        middle = (TextView) findViewById(R.id.middle);
        difficult = (TextView) findViewById(R.id.difficult);


        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ques.class);
                if (getIntent().getBooleanExtra("EIMI", true)) {
                    i.putExtra("set", "leicht");
                    startActivity(i);
                }

                if (getIntent().getBooleanExtra("ADP", true)) {
                    i.putExtra("subject", "ADP");
                    i.putExtra("set", "leicht");
                    startActivity(i);
                }

                if (getIntent().getBooleanExtra("HCI", true)) {
                    i.putExtra("subject", "HCI");
                    i.putExtra("set", "leicht");
                    startActivity(i);
                }
            }
        });

       middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ques.class);

                if (getIntent().getBooleanExtra("EIMI", true)) {
                    i.putExtra("subject", "EIMI");
                    i.putExtra("set", "mittel");
                    startActivity(i);
                }

                if (getIntent().getBooleanExtra("ADP", true)) {
                    i.putExtra("subject", "ADP");
                    i.putExtra("set", "mittel");
                    startActivity(i);
                }

                if (getIntent().getBooleanExtra("HCI", true)) {
                    i.putExtra("subject", "HCI");
                    i.putExtra("set", "mittel");
                    startActivity(i);

                }
            }
        });

        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ques.class);

                if (getIntent().getBooleanExtra("EIMI", true)) {
                    i.putExtra("subject", "EIMI");
                    i.putExtra("set", "schwer");
                    startActivity(i);
                }


                if (getIntent().getBooleanExtra("ADP", true)) {
                    i.putExtra("subject", "ADP");
                    i.putExtra("set", "schwer");
                    startActivity(i);
                }

                if (getIntent().getBooleanExtra("HCI", true)) {
                    i.putExtra("subject", "HCI");
                    i.putExtra("set", "schwer");
                    startActivity(i);
                }}
        });

    }





}
