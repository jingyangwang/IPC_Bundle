package com.example.wangjingyang.ipc_aidl;

import android.util.Log;

public class Process implements Runnable {
    @Override
    public void run() {
        Log.i("ThreadActivity",""+Thread.currentThread().getName());
    }
}
