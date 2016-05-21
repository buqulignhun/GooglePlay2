package com.example.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseFragmentComment;
import com.example.googleplay.base.PageLoadingView;
import com.example.googleplay.utils.UIUtils;

/**
 * Created by yubin on 2016/5/13.
 */
public class RankFragment extends BaseFragment {
    @Override
    public PageLoadingView.ReturnResult loadingData() {

                SystemClock.sleep(2000);
     ;
        return PageLoadingView.ReturnResult.SUCCESS;
    }

    @Override
    protected View initSuccessPage() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("排行...");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);

        return tv;
    }
   /* @Override
    public View initView() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("排行...");
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        return tv;
    }*/
}
