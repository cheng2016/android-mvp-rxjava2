package com.chengzj.app.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * Created by chengzj on 2017/6/17.
 */

public class BaseApplication extends Application{
    private final static String TAG = "BaseApplication %s";

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.d(TAG,"activity: "+activity.getClass().getSimpleName()+" onActivityDestroyed");
            }
        });
    }
}
