package com.example.wangjingyang.ipc_aidl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ThreadActivity",""+Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ThreadActivity",""+Thread.currentThread().getName());
            }
        }).start();
    }
}
