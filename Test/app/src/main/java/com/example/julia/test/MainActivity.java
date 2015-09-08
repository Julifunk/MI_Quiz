package com.example.julia.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Marcus on 06.09.2015.
 */
public class MainActivity extends Activity{
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
                Intent i = new Intent(getApplicationContext(), StartingGameActivity.class);
                startActivity(i);
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
