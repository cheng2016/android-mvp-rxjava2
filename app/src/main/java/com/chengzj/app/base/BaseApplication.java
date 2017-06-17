package com.chengzj.app.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by chengzj on 2017/6/17.
 */

public class BaseApplication extends Application{
    private final static String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogUtils.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityDestroyed");
            }
        });
    }
}
