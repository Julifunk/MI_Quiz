package com.example.julia.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Button eimi;
    ParseObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BUfWzIhsninLF29zGCoEz7puv93amubRhTUmfY63", "L36y1hNcNkMI5qVmz8ytD8DsrqnSwrYzmi9gmCYM");
        setContentView(R.layout.activity_main);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Binar_HexaOperationen");
        query.whereExists("question");
        query.findInBackground(new FindCallback<ParseObject>() {
          @Override
            public void done(List<ParseObject> list, ParseException e) {
              if (list.size() > 0) {
                  for (int i = 0; i < list.size(); i++) {

                      object = list.get(0);
                      object.pinInBackground();

                      eimi = (Button) findViewById (R.id.button);
                      eimi.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent i = new Intent(getApplicationContext(),Ques.class);
                              i.putExtra("objectId", object.getObjectId());
                              startActivity(i);
                          }
                      });

                      ParseObject p = list.get(i);
                      String question = p.getString("question");
                      System.out.println(question);




                  }}
        }});


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
