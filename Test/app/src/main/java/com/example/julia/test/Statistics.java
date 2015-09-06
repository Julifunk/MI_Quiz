package com.example.julia.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.os.Handler;


/**
 * Created by Julia on 06.09.2015.
 */
public class Statistics extends Activity {

    private ProgressBar quesProgress;
    private int mProgressStatus = 0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.statistics);

        quesProgress = (ProgressBar) findViewById(R.id.progressBar);

       /*new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 15) {

                    // Update the progress bar
                    myHandler.post(new Runnable() {
                        public void run() {
                            quesProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();*/

    }

}
