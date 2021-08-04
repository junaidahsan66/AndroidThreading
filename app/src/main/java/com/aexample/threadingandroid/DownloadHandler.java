package com.aexample.threadingandroid;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {
    private static final String TAG ="jun" ;


    private void getThatCat(String singlecars) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: " + Thread.currentThread().getName() + " -->" + singlecars);

    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        getThatCat(msg.obj.toString());

    }
}
