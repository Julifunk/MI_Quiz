package com.example.julia.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseObject;

public class StartingGameActivity extends Activity {

    private TextView eimi;
    private TextView adp;
    private TextView hci;
    private TextView mmt;
    private TextView mmdb;
  


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_a_game);

        setupLayoutElements();

       }



    private void setupLayoutElements(){

        eimi = (TextView) findViewById (R.id.eimi);
        adp = (TextView) findViewById(R.id.adp);
        hci = (TextView) findViewById(R.id.hci);
        mmt = (TextView) findViewById(R.id.mmt);
        mmdb = (TextView) findViewById(R.id.mmdb);

        eimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SetDifficulty.class);
                i.putExtra("subject", "EIMI");
                startActivity(i);
            }
        });
        adp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SetDifficulty.class);
                i.putExtra("subject", "ADP");
                startActivity(i);
            }
        });
        hci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SetDifficulty.class);
                i.putExtra("subject","HCI");
                startActivity(i);
            }
        });
        mmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SetDifficulty.class);
                i.putExtra("subject","MMT");
                startActivity(i);
            }
        });
        mmdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SetDifficulty.class);
                i.putExtra("subject","MMDB");
                startActivity(i);
            }
        });


    }

}
