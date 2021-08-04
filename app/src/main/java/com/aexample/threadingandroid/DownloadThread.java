package com.aexample.threadingandroid;

import android.util.Log;

public class DownloadThread extends Thread {
    private static final String TAG = "jun";
    private String[] cars;
    private String singlecars = "";

    public DownloadThread(String[] cars) {
        this.cars = cars;
    }

    public DownloadThread(String singlecars) {
        this.singlecars = singlecars;
    }

    @Override
    public void run() {
        if (singlecars.equals(""))
            executeCode();
        else
            exucuteInMUltipleThread();

    }

    private void exucuteInMUltipleThread() {
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName() + " -->" + singlecars);

    }

    private void executeCode() {
        for (String car : cars) {
            Log.d(TAG, "onCreate: " + Thread.currentThread().getName() + " -->" + car);
        }

    }
}
