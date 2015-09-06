package com.example.julia.test;

/**
 * Created by Julia on 06.09.2015.
 */
public class Statistics {
    private static Statistics ourInstance = new Statistics();

    public static Statistics getInstance() {
        return ourInstance;
    }

    private Statistics() {
    }
}
