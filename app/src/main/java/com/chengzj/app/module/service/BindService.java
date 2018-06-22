package com.chengzj.app.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.chengzj.app.util.Logger;

/**
 * 绑定服务
 *
 */
public class BindService extends Service {
    public final static String TAG = "BindService";

    public class MyBinder extends Binder {
        public BindService getService(){
            return BindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG,"onCreate, Thread: " + Thread.currentThread().getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.i(TAG, "onBind, Thread: " + Thread.currentThread().getName());
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i(TAG, "onUnbind, from:" + intent.getStringExtra("from"));
        return false;
    }

    @Override
    public void onDestroy() {
        Logger.i(TAG, "onDestroy, Thread: " + Thread.currentThread().getName());
        super.onDestroy();
    }
}
