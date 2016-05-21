package com.example.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import com.example.googleplay.base.BaseApplication;

/**
 * Created by yubin on 2016/5/11.
 */
public class UIUtils {

    public static Context getContext(){
        return BaseApplication.getMainContext();
    }

    public static Resources getResource(){
        return  getContext().getResources();
    }

    public static String getString(int strId){
        return getResource().getString(strId);
    }

    public static String[] getStringArr(int strArrId){
       return getResource().getStringArray(strArrId);
    }

    public static int getColor(int corId){
        return getResource().getColor(corId);
    }

    public static String getPackageName(){
        return getContext().getPackageName();
    }

    public static int getMainThreadId(){
        return Process.myTid();
    }

    public static Thread getMainThread(){
       return BaseApplication.getMainThread();
    }

    public static Handler getMainHandler(){
        return BaseApplication.getMainHandler();
    }

    public static void postTask(Thread thread,Runnable task){
        long threadId = thread.getId();

        if(threadId == getMainThreadId()){
            task.run();
        }else{
            //放在主线程执行
            getMainHandler().post(task);
        }
     /*   getMainHandler().post(task);*/
    }
}
