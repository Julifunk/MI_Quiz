package com.example.julia.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
/**
 * Created by Julia on 26.09.2015.
 */
public class OverallStatistics extends ActionBarActivity {

    private StatsDatabase db;
    private ListView myStatsList;
    private TextView deleteAllItemsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_statistics);
        openDB();
        myStatsList = (ListView)findViewById(R.id.stats_list_view);
        deleteAllItemsButton = (TextView) findViewById(R.id.button_delete_all_statistics_items);
        deleteAllItemsButton.setVisibility(View.INVISIBLE);

        deleteAllItemsButton.setVisibility(View.VISIBLE);
        deleteAllItemsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteAll();
                    fillOverallStatsList();
                }
            });

        fillOverallStatsList();


    }


    private void openDB() {
        db = new StatsDatabase(this);
        db.open();
    }

    private void fillOverallStatsList() {
        Cursor cursor = db.getAllRows();
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[] {StatsDatabase.KEY_SUBJECT, StatsDatabase.KEY_SETS, StatsDatabase.KEY_RATING};
        int[] toViewIDs = new int[] {R.id.subject_for_statsItem, R.id.set_for_statsItem, R.id.rating_for_statsItem};

        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this,	R.layout.statistics_item, cursor, fromFieldNames,toViewIDs);


        // Set the adapter for the list view
        myStatsList.setAdapter(myCursorAdapter);
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
