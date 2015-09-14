package com.example.julia.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseObject;

public class StartingGameActivity extends ActionBarActivity {

    private TextView eimi;
    private TextView adp;
    private TextView hci;
  
    ParseObject object;

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


    }

      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.stats) {
            Intent i = new Intent(getApplicationContext(),Statistics.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
