package com.example.julia.test;

import android.app.Application;

import com.parse.Parse;

/**
 * Source: http://stackoverflow.com/questions/28721509/unable-to-start-activity-componentinfo-parse[12.09.2015]
 */
    public class ParseApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "BUfWzIhsninLF29zGCoEz7puv93amubRhTUmfY63", "L36y1hNcNkMI5qVmz8ytD8DsrqnSwrYzmi9gmCYM");
        }
}

