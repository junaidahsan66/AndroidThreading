package com.aexample.threadingandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.aexample.threadingandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "jun";
    ActivityMainBinding binding;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                binding.textView.setText(msg.getData().getString("data"));
            }
        };


        binding.start.setOnClickListener(v -> {
            enableProgress(true);

            for (String car:CarsData.cars) {
                DownloadThread thread = new DownloadThread(car);
                thread.start();
            }

//
//            DownloadThread thread = new DownloadThread(CarsData.cars);
//            thread.start();

        });
        binding.end.setOnClickListener(v -> {
            enableProgress(false);
        });
    }

    private void scrollToEnd() {
        binding.scroll.post(new Runnable() {
            @Override
            public void run() {
                binding.scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void enableProgress(Boolean value){
        if (value){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.GONE);

        }

    }
}