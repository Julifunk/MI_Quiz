package com.example.julia.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marcus on 06.09.2015.
 */
public class StartScreen extends Activity{
    private TextView startingGame;
    private TextView stats;
    private TextView networkError;
    private ImageView logo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isNetworkAvailable()) {
            setupLayoutElements();
        }
        else{
            showNetworkConnectionError();
        }
    }

    //alerting user, if no network connection is available
    private void showNetworkConnectionError() {
        stats = (TextView) findViewById(R.id.stats);
        stats.setVisibility(View.INVISIBLE);
        startingGame = (TextView)findViewById(R.id.starting_game);
        startingGame.setVisibility(View.INVISIBLE);
        networkError = (TextView)findViewById(R.id.network_error);
        networkError.setText(R.string.error_no_network_connection);
        networkError.setVisibility(View.VISIBLE);
    }

    private void setupLayoutElements(){
        startingGame = (TextView) findViewById(R.id.starting_game);
        stats = (TextView) findViewById(R.id.stats);
        logo = (ImageView)findViewById(R.id.logo);
        logo.requestLayout();
        logo.getLayoutParams().width = logo.getLayoutParams().height;

        startingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StartingGameActivity.class);
                startActivity(i);
            }
        });
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OverallStatistics.class);
                startActivity(i);
            }
        });


    }
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
               logo.setVisibility(View.INVISIBLE);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            logo.setVisibility(View.VISIBLE);
        }
        super.onConfigurationChanged(newConfig);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
