package com.example.googleplay.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yubin on 2016/5/13.
 */
public abstract class BaseFragmentComment extends Fragment {


    //只执行一次，完成初始化
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    public abstract View initView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData();
        initEvent();
        super.onActivityCreated(savedInstanceState);
    }

    private void initEvent() {
    }

    private void initData() {
    }
}
