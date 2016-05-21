package com.example.googleplay.base;

import android.content.Context;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.googleplay.R;
import com.example.googleplay.threadPool.ThreadPoolFactory;
import com.example.googleplay.utils.UIUtils;

/**
 * Created by yubin on 2016/5/13.
 */
public abstract class PageLoadingView extends FrameLayout {
    private View emptyPage;
    private View errorPage;
    private View loadingPage;

    public static final int STATE_NONE = -1;//请求网络加载数据
    public static final int STATE_LOADING = 0;//请求网络加载数据
    public static final int STATE_EMPTY = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_SUCCESS = 3;
    private int mState = STATE_NONE;
    private View successPage;
    private Button reTryBtn;

    public PageLoadingView(Context context) {
        super(context);
        initView();
        refreshView();
        //子线程中，初始化数据
    }


    private void initView() {
        emptyPage = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        errorPage = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        loadingPage = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);

        //重新加载按钮
        reTryBtn = (Button) errorPage.findViewById(R.id.error_btn_retry);
        reTryBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingData();
            }
        });

        this.addView(emptyPage);
        this.addView(errorPage);
        this.addView(loadingPage);
    }


    /**
     * 加载数据，基类中没有调用
     *  1. 每次加载数据前，初始化状态
     *  2. 如果是加载成功，就不需要再加载数据
     *  3. 如果数据正在加载，就不需要重新加载
     */
    public  void loadingData(){
        if (mState != STATE_SUCCESS && mState != STATE_LOADING) {
            mState = STATE_LOADING;
            refreshView();

           // new ThreadTask().start();
           // 采用线程池
            ThreadPoolFactory.getNormalThreadPool().execute(new ThreadTask());
        }
    }

    private class ThreadTask extends Thread{

        @Override
        public void run() {
            //加载数据
            ReturnResult result = loadingDataTask();
            mState = result.getState();
            //System.out.println("mState: "+mState);
            //刷新页面,投递消息到主线程中
            UIUtils.postTask(this,new Runnable() {
                @Override
                public void run() {
                    refreshView();
                }
            });
        }

    }

    protected abstract ReturnResult loadingDataTask();

    public enum ReturnResult{
        SUCCESS(STATE_SUCCESS),EMPTY(STATE_EMPTY),ERROR(STATE_ERROR);
        int state;

        private ReturnResult(int state){
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }


    /**
     * 刷新页面，控制页面的显示
     */
    private void refreshView() {
        emptyPage.setVisibility((mState == STATE_EMPTY) ? View.VISIBLE : View.GONE);
        errorPage.setVisibility((mState == STATE_ERROR) ? View.VISIBLE : View.GONE);
        loadingPage.setVisibility((mState == STATE_LOADING) || (mState == STATE_LOADING)? View.VISIBLE : View.GONE);

        //防止多次创建，以及添加多次
        if(mState == STATE_SUCCESS && successPage == null){
        //数据加载完成后，初始化页面
            successPage = initSuccessPage();
            this.addView(successPage);
        }

        if (successPage != null) {
            successPage.setVisibility((mState == STATE_SUCCESS) ? View.VISIBLE : View.GONE);
        }
    }

    public abstract View initSuccessPage();


}
