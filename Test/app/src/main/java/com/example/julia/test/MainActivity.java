package com.example.julia.test;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marcus on 06.09.2015.
 */
public class MainActivity extends Activity{

    private TextView startingGame;
    private TextView stats;
    private ImageView logo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayoutElements();
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


}
