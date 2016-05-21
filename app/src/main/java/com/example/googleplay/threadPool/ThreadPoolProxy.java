package com.example.googleplay.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yubin on 2016/5/16.
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;

    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;

    /**
     * @param maximumPoolSize 线程池最多
     * @param corePoolSize 核心池大小
     * @param keepAliveTime 保持时间
     *  单例创建
     */
    public ThreadPoolProxy(int maximumPoolSize, int corePoolSize, long keepAliveTime) {
        this.maximumPoolSize = maximumPoolSize;
        this.corePoolSize = corePoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private ThreadPoolExecutor initThreadPoolExecutor() {

        if (mExecutor == null) {
            //加锁
            synchronized (ThreadPoolExecutor.class) {
                //双重检查机制
                if (mExecutor == null) {
                    TimeUnit unit = TimeUnit.MICROSECONDS;//时间单位
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();//无界队列
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();//默认工厂
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//策略

                    mExecutor = new ThreadPoolExecutor(corePoolSize,
                            maximumPoolSize,
                            keepAliveTime,
                            unit,
                            workQueue,
                            threadFactory,
                            handler);
                }
            }
        }
        return mExecutor;
    }


    public void execute(Runnable command) {
        initThreadPoolExecutor();
        mExecutor.execute(command);
    }


    public Future<?> submit(Runnable command) {
        initThreadPoolExecutor();
        return mExecutor.submit(command);
    }

    public void remove(Runnable command) {
        initThreadPoolExecutor();
        mExecutor.remove(command);
    }
}
