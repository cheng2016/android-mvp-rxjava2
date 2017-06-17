package com.chengzj.app;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.chengzj.app.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by chengzj on 2017/6/17.
 */

public class App extends BaseApplication{
    private static App mInstance;

    public static Context getInstance(){
        return mInstance;
    }

    private RefWatcher mRefWatcher;

    public static RefWatcher getRefWatcher(){
        return mInstance.mRefWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        mRefWatcher = LeakCanary.install(this);
        Utils.init(this);
    }
}
