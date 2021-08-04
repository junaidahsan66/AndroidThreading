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
//        binding.textView.setText(R.string.lorem);


        binding.start.setOnClickListener(v -> {
            enableProgress(true);
            Log.d(TAG, "onCreate: "+Thread.currentThread().getName());

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "onCreate: "+Thread.currentThread().getName());

                        Thread.sleep(4000);
                        Log.d(TAG, "COmplete: "+Thread.currentThread().getName());
                        Log.d(TAG, "DONE");
                        Message message = new Message();
                        Bundle bundle=new Bundle();
                        bundle.putString("data","AAAAAAAaa");
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Thread.sleep(4000);

                        Message message2= new Message();
                        Bundle bundle2=new Bundle();
                        bundle2.putString("data","BBBB");
                        message2.setData(bundle2);
                        handler.sendMessage(message2);

                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();

            scrollToEnd();
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