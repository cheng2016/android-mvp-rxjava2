package com.chengzj.app.util;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by chengzj on 2018/6/21.
 * 文件日志类
 */
public class L {
    private static FileOutputStream fos;
    private static OutputStreamWriter osWriter;
    private static BufferedWriter writer;

    private static boolean isWriter;

    private static Level currentLevel;

    private static String pkgName;

    private static String logFilePath;

    private static DateFormat FILE_NAME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static DateFormat LOG_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String LOG_FORMAT = "%s  %d-%d/%s  %s/%s：";

    /**
     * 日志级别
     */
    public enum Level {

        VERBOSE(Log.VERBOSE),

        DEBUG(Log.DEBUG),

        INFO(Log.INFO),

        WARN(Log.WARN),

        ERROR(Log.ERROR),

        ASSERT(Log.ASSERT),

        CLOSE(Log.ASSERT + 1);

        int value;

        Level(int value) {
            this.value = value;
        }
    }

    public static final void i(String tag, String msg) {
        if (currentLevel.value > Level.INFO.value)
            return;
        if (isWriter) {
            write(tag, msg, "I");
        }
        Log.i(tag, msg);
    }

    public static final void i(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > Level.INFO.value)
            return;
        if (isWriter) {
            write(tag, msg, "I", throwable);
        }
        Log.i(tag, msg, throwable);
    }

    public static final void v(String tag, String msg) {
        if (currentLevel.value > Level.VERBOSE.value)
            return;
        if (isWriter) {
            write(tag, msg, "V");
        }
        Log.v(tag, msg);
    }

    public static final void v(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > Level.VERBOSE.value)
            return;
        if (isWriter) {
            write(tag, msg, "V", throwable);
        }
        Log.v(tag, msg, throwable);
    }

    public static final void d(String tag, String msg) {
        if (currentLevel.value > Level.DEBUG.value)
            return;
        if (isWriter) {
            write(tag, msg, "D");
        }
        Log.d(tag, msg);
    }

    public static final void d(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > Level.DEBUG.value)
            return;
        if (isWriter) {
            write(tag, msg, "D", throwable);
        }
        Log.d(tag, msg, throwable);
    }

    public static final void e(String tag, String msg) {
        if (currentLevel.value > Level.ERROR.value)
            return;
        if (isWriter) {
            write(tag, msg, "E");
        }
        Log.e(tag, msg);
    }

    public static final void e(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > Level.ERROR.value)
            return;
        if (isWriter) {
            write(tag, msg, "E", throwable);
        }
        Log.e(tag, msg, throwable);
    }

    public static final void w(String tag, String msg) {
        if (currentLevel.value > Level.WARN.value)
            return;
        if (isWriter) {
            write(tag, msg, "W");
        }
        Log.w(tag, msg);
    }

    public static final void w(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > Level.WARN.value)
            return;
        if (isWriter) {
            write(tag, msg, "W", throwable);
        }
        Log.w(tag, msg, throwable);
    }

    public static final void i(Object target, String msg) {
        i(target.getClass().getSimpleName(), msg);
    }

    public static final void i(Object target, String msg, Throwable throwable) {
        i(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void v(Object target, String msg) {
        v(target.getClass().getSimpleName(), msg);
    }

    public static final void v(Object target, String msg, Throwable throwable) {
        v(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void d(Object target, String msg) {
        d(target.getClass().getSimpleName(), msg);
    }

    public static final void d(Object target, String msg, Throwable throwable) {
        d(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void e(Object target, String msg) {
        e(target.getClass().getSimpleName(), msg);
    }

    public static final void e(Object target, String msg, Throwable throwable) {
        e(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void w(Object target, String msg) {
        w(target.getClass().getSimpleName(), msg);
    }

    public static final void w(Object target, String msg, Throwable throwable) {

        w(target.getClass().getSimpleName(), msg, throwable);
    }

    /**
     * 通过handler写入日志
     * @param tag
     * @param msg
     * @param level
     */
    private static final void write(String tag, String msg, String level) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());
        StringBuilder sb = new StringBuilder(String.format(LOG_FORMAT, timeStamp, Process.myPid(), Process.myPid(), pkgName, level, tag));
        sb.append(msg);
        Message message = new Message();
        message.obj = sb.toString();
        mHandler.sendMessage(message);
    }

    /**
     * 写文件操作
     *
     * @param tag   日志标签
     * @param msg   日志内容
     * @param level 日志级别
     */
    private static final void writeLog(String tag, String msg, String level) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());
        try {
            writer.write(String.format(LOG_FORMAT, timeStamp, Process.myPid(), Process.myPid(), pkgName, level, tag));
            writer.write(msg);
            writer.newLine();
            writer.flush();
            osWriter.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文件操作
     *
     * @param tag       日志标签
     * @param msg       日志内容
     * @param level     日志级别
     * @param throwable 异常捕获
     */
    private static final void write(String tag, String msg, String level, Throwable throwable) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());

        try {
            writer.write(String.format(LOG_FORMAT, timeStamp, Process.myPid(), Process.myPid(), pkgName, level, tag));
            writer.write(msg);
            writer.newLine();
            writer.flush();
            osWriter.flush();
            fos.flush();
            if (throwable != null)
                saveCrash(throwable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存异常
     *
     * @param throwable
     * @throws IOException
     */
    private static void saveCrash(Throwable throwable) throws IOException {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        throwable.printStackTrace(pWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(pWriter);
            cause = cause.getCause();
        }
        pWriter.flush();
        pWriter.close();
        sWriter.flush();
        String crashInfo = writer.toString();
        sWriter.close();
        writer.write(crashInfo);
        writer.newLine();
        writer.flush();
        osWriter.flush();
        fos.flush();
    }

    /**
     * 日志组件初始化
     *
     * @param appCtx   application 上下文
     * @param isWriter 是否保存文件
     * @param level    日志级别
     */
    public static final void initialize(Context appCtx, boolean isWriter, Level level) {
        currentLevel = level;
        if (level == Level.CLOSE) {
            L.isWriter = false;
            return;
        }
        L.isWriter = isWriter;
        if (!L.isWriter) {//不保存日志到文件
            return;
        }
        String logFoldPath;
        if (isSDCardOK()) {
            logFoldPath = Environment.getExternalStorageDirectory() + "/wecare/logger/";
        } else {
            logFoldPath = appCtx.getExternalCacheDir().getAbsolutePath() + "/../logger/log";
        }
        pkgName = appCtx.getPackageName();
        File logFold = new File(logFoldPath);
        boolean flag;
        if (!(flag = logFold.exists()))
            flag = logFold.mkdirs();
        if (!flag) {
            L.isWriter = false;
            return;
        }
        logFilePath = logFoldPath + FILE_NAME_FORMAT.format(Calendar.getInstance().getTime()) + ".log";
        try {
            File logFile = new File(logFilePath);
            if (!(flag = logFile.exists())) {
                flag = logFile.createNewFile();
            }
            L.isWriter = isWriter && flag;

            if (L.isWriter) {
                fos = new FileOutputStream(logFile);
                osWriter = new OutputStreamWriter(fos);
                writer = new BufferedWriter(osWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
            L.isWriter = false;
        }
    }

    private static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = (String) msg.obj;

            FileWriter fileWriter = null;
            File logFile = new File(logFilePath);
            try {
                fileWriter = new FileWriter(logFile, true);
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.append(content);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fileWriter != null){
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    //读写sd卡时的判断
    public static boolean isSDCardOK() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());
        String tag = "tag";
        String msg = "this is a message!";
        String str = String.format(LOG_FORMAT, timeStamp, 123, 123, "com.cheng.app", "V", tag);
        System.out.println(str + msg);
    }
}
