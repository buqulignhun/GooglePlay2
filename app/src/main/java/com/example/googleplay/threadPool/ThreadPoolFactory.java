package com.example.googleplay.threadPool;

/**
 * Created by yubin on 2016/5/16.
 */
public class ThreadPoolFactory {
    public static ThreadPoolProxy normalThreadPool;
    public static ThreadPoolProxy downloadThreadPool;

    public static ThreadPoolProxy getNormalThreadPool(){
        if (normalThreadPool == null) {
            synchronized (ThreadPoolProxy.class) {
                if (normalThreadPool == null)
                    normalThreadPool = new ThreadPoolProxy(5, 5, 3000);
            }
        }
        return normalThreadPool;

    }

    public static ThreadPoolProxy getDownThreadPool(){
        if (downloadThreadPool == null) {
            synchronized (ThreadPoolProxy.class) {
                if (downloadThreadPool == null)
                    downloadThreadPool = new ThreadPoolProxy(3, 3, 3000);
            }
        }
        return downloadThreadPool;

    }

}
