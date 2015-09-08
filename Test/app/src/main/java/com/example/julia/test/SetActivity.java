package com.example.julia.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Philip on 07.09.2015.
 */
public class SetActivity extends Activity {

    private TextView leicht;
    private TextView mittel;
    private TextView schwer;



    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sets);

        setupLayoutElements();

    }

    private void setupLayoutElements(){

        leicht = (TextView) findViewById(R.id.set1);
        mittel = (TextView) findViewById(R.id.set2);
        schwer = (TextView) findViewById(R.id.set3);


        leicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Ques.class);
                if (getIntent().getBooleanExtra("EIMI", true)) {
                    i.putExtra("subject", "EIMI");
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

        mittel.setOnClickListener(new View.OnClickListener() {
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

            }}
        });

        schwer.setOnClickListener(new View.OnClickListener() {
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
