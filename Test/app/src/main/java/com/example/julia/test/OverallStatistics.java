package com.example.julia.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


/**
/**
 * Created by Julia on 26.09.2015.
 */
public class OverallStatistics extends ActionBarActivity {

    private StatsDatabase db;
    private GridView myStatsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_statistics);
        openDB();
        myStatsList = (GridView)findViewById(R.id.stats_list_view);
        fillOverallStatsList();
        manipulateViews();
        handleClicksOnGrid();

    }

    private void manipulateViews() {


    }


    private void openDB() {
        db = new StatsDatabase(this);
        db.open();
    }

    private void fillOverallStatsList() {
        Cursor cursor = db.getData();
        String[] fromFieldNames = new String[] {StatsDatabase.KEY_SUBJECT, StatsDatabase.KEY_SETS, StatsDatabase.KEY_RATING};
        int[] toViewIDs = new int[] {R.id.subject_for_statsItem, R.id.set_for_statsItem, R.id.rating_for_statsItem};
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this,	R.layout.statistics_item, cursor, fromFieldNames,toViewIDs);
        myStatsList.setAdapter(myCursorAdapter);
    }



    private void handleClicksOnGrid(){
        myStatsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView subject = (TextView)v.findViewById(R.id.subject_for_statsItem);
                TextView set = (TextView)v.findViewById(R.id.set_for_statsItem);
                Intent i = new Intent(getApplicationContext(), Ques.class);
                i.putExtra("subject", subject.getText());
                i.putExtra("set", set.getText());
                startActivity(i);

            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_overall_statistics, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all_statistics_items) {
            db.deleteAll();
            fillOverallStatsList();
        }
        if (id == R.id.back_to_subjects_menu_item) {
            Intent i = new Intent(getApplicationContext(), StartingGameActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

}
