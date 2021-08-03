package com.aexample.threadingandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;

import com.aexample.threadingandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.textView.setText(R.string.lorem);


        binding.start.setOnClickListener(v -> {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    enableProgress(false);
                }
            };

            Handler handler = new Handler();
            handler.postDelayed(runnable,4000);

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