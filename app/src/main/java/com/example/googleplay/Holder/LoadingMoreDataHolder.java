package com.example.googleplay.Holder;

import android.view.View;
import android.widget.LinearLayout;

import com.example.googleplay.R;
import com.example.googleplay.utils.UIUtils;

/**
 * Created by yubin on 2016/5/19.
 */
public class LoadingMoreDataHolder extends BaseHolder<Integer> {

    public static final int STATE_LOADING = 0;
    public static final int STATE_NOMOREDATA = 1;
    public static final int STATE_NONE = 2;
    private LinearLayout llLoadMore;
    private LinearLayout llNoMoreData;

   /* public LoadingMoreDataHolder(){
        refreshData(STATE_LOADING);
    }*/


    /**
     *避免尾部参与缓存
     */
    @Override
    protected void initView() {
        mHolderView = initHolderView();
    }


    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_loading_more_holder, null);
        llLoadMore = (LinearLayout) view.findViewById(R.id.item_home_holder_ll_loading);
        llNoMoreData = (LinearLayout) view.findViewById(R.id.item_home_holder_ll_noData);
        //初始化最初状态
        refreshData(STATE_LOADING);
        return view;
    }

    @Override
    public void refreshData(Integer data ) {
        llLoadMore.setVisibility(View.GONE);
        llNoMoreData.setVisibility(View.GONE);
        if (data == STATE_LOADING) {
            llLoadMore.setVisibility(View.VISIBLE);
            llNoMoreData.setVisibility(View.GONE);
        }else if(data == STATE_NOMOREDATA){
            llNoMoreData.setVisibility(View.GONE);
            llNoMoreData.setVisibility(View.VISIBLE);
        }
    }
}
