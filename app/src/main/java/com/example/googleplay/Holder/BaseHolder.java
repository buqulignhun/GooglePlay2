package com.example.googleplay.Holder;

import android.view.View;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.utils.UIUtils;
import com.example.googleplay.javaBean.HomeJavaBean;

/**
 * Created by yuibn on 2016/5/17.
 */
public abstract class BaseHolder<DATATYPE> {
    public View mHolderView;
    public DATATYPE mData;

    public BaseHolder(){
        initView();
    }

    protected void initView() {
        mHolderView = initHolderView();
        mHolderView.setTag(this);
    }

    /**
     * @return 返回视图
     * 这样更利于代码的抽取
     */
   public abstract View initHolderView();


    public void refreshDataView(DATATYPE data) {
        mData = data;
        refreshData(data);
    }

    /**
     * @param data 数据
     *        设置数据
     */
    public abstract void refreshData(DATATYPE data) ;
}
