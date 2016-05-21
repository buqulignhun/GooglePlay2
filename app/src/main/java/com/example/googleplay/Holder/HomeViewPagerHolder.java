package com.example.googleplay.Holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.Contacts;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.googleplay.Config.Constants;
import com.example.googleplay.R;
import com.example.googleplay.utils.BitmapHelper;
import com.example.googleplay.utils.UIUtils;

import java.util.List;

/**
 * Created by 余斌 on 2016/5/21.
 */
public class HomeViewPagerHolder extends BaseHolder<List<String>> {

    private LinearLayout points;
    private ViewPager viewPager;
    private MyViewPagerAdapter myPagerAdapter;

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_header_viewpager, null);
        viewPager = (ViewPager) view.findViewById(R.id.item_list_header_vp_picture);
        points = (LinearLayout) viewPager.findViewById(R.id.item_list_header_ll_point);

        return view;
    }

    @Override
    public void refreshData(List<String> data) {
        myPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);

        initPointsView();
    }

    private void initPointsView() {

    }

    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mData != null) {

                return mData.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            BitmapHelper.display(iv, Constants.URL.BASEHOMEICONURL + mData.get(position));
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
