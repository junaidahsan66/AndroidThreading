package com.aexample.threadingandroid;

import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread {
    private static final String TAG = "jun";
    public DownloadHandler nDownHandler;

    @Override
    public void run() {
        //Creates a Message Queue and looper for the thread
        Looper.prepare();
        nDownHandler=new DownloadHandler();
        Looper.loop();
    }




}
