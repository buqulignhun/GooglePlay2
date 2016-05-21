package com.example.googleplay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

/**
 * Created by yubin on 2016/5/11.
 * 描述： 定义一个全局的盒子，里面放置对象，方法，属性全局都可调用
 */
public class BaseApplication extends Application {

    private static Thread mainThread;
    private static Context mainContext;
    private static int mainPid;
    private static Looper mainLooper;
    private static Handler mainHadler;//在这里，创建一个主线程中的handler

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getMainPid() {
        return mainPid;
    }

    public static Looper getMyMainLooper() {
        return mainLooper;
    }

    public static Handler getMainHandler(){
        return mainHadler;
    }

    public static Context getMainContext() {
        return mainContext;
    }

    public BaseApplication() {
    }

    @Override
    public void onCreate() {
        mainThread = Thread.currentThread();
        mainContext = getApplicationContext();
        mainPid = Process.myPid();
        mainLooper = getMainLooper();
        mainHadler = new Handler();
        super.onCreate();

    }
}
