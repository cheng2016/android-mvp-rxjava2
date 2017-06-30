package com.chengzj.app;

import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.chengzj.app.base.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
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
        initAppTool();
        initLogSystem();
    }

    void initAppTool(){
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        mRefWatcher = LeakCanary.install(this);
        //初始化工具类
        Utils.init(this);
    }

    void initLogSystem(){
        Logger.addLogAdapter(new AndroidLogAdapter());
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) 是否显示线程信息。默认值true
                .methodCount(0)         // (Optional) 要显示的方法行数。默认值2
                .methodOffset(7)        // (Optional) 隐藏内部方法调用到偏移量。默认5
//                .logStrategy(customLog) // (Optional) 更改要打印的日志策略。默认LogCat
                .tag("APP_Logger_Print")   // (Optional) 每个日志的全局标签，默认 PRETTY_LOGGER。
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        //是否打印日志
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        //将日志保存在磁盘中
        FormatStrategy diskFormatStrategy = CsvFormatStrategy.newBuilder()
                .tag("APP_Logger_Print")
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(diskFormatStrategy));
    }
}
