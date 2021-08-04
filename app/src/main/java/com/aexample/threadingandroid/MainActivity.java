package com.aexample.threadingandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.aexample.threadingandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "jun";
    ActivityMainBinding binding;
    DownloadThread thread;

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

        thread = new DownloadThread();
        thread.start();


        binding.start.setOnClickListener(v -> {
            for (String car :CarsData.cars
                 ) {

                /**
                 * Return a new Message instance from the global pool. Allows us to
                 * avoid allocating new objects in many cases.
                 */
                Message message = Message.obtain();
                message.obj=car;
                thread.nDownHandler.sendMessage(message);

            }
            enableProgress(true);

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