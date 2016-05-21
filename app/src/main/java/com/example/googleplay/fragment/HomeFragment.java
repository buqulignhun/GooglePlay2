package com.example.googleplay.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.googleplay.Holder.BaseHolder;
import com.example.googleplay.Holder.HomeHodler;
import com.example.googleplay.Holder.HomeViewPagerHolder;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.PageLoadingView;
import com.example.googleplay.base.SuperAdapter;
import com.example.googleplay.javaBean.AppInfo;
import com.example.googleplay.javaBean.HomeJavaBean;
import com.example.googleplay.listView.ListViewFactory;
import com.example.googleplay.listView.TestListView;
import com.example.googleplay.protocol.HomeProcol;
import com.example.googleplay.utils.UIUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;

import java.io.IOException;
import java.util.List;

/**
 * Created by yubin on 2016/5/13.
 */
public class HomeFragment extends BaseFragment {

    private HomeAdapter homeAdapter;
    private List<String> sampeleList;
    private List<AppInfo> mAppInfos;
    private HttpUtils httpUtils;
    private HomeProcol homeProcol;
    private List<String> picUrl;

    /**
     * 在子线程中，加载数据
     *
     * @return
     */
    @Override
    public PageLoadingView.ReturnResult loadingData() {

        try {

            homeProcol = new HomeProcol();
            HomeJavaBean homeBean = homeProcol.getData(0);
            //处理数据
            processData(homeBean);

            if (checkDataEmpty(homeBean)) {
                return PageLoadingView.ReturnResult.EMPTY;
            }

            if (checkDataEmpty(homeBean.list)) {
                return PageLoadingView.ReturnResult.EMPTY;
            }


            return PageLoadingView.ReturnResult.SUCCESS;


        } catch (HttpException e) {
            e.printStackTrace();
            return PageLoadingView.ReturnResult.ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return PageLoadingView.ReturnResult.ERROR;
        }
    }

    //分发数据
    private void processData(HomeJavaBean data) {
        mAppInfos = data.list;
        picUrl = data.picture;
    }

    @Override
    protected View initSuccessPage() {
     //   ListView listView = ListViewFactory.getListView();
        //todo   item 点击事件没有响应
        TestListView listView = new TestListView(UIUtils.getContext());
        //添加轮播图放首部
        BaseHolder homeViewPagerHolder = new HomeViewPagerHolder();
        //初始化轮播图数据
        homeViewPagerHolder.refreshDataView(picUrl);

        homeAdapter = new HomeAdapter(listView,mAppInfos);
        listView.setAdapter(homeAdapter);
        listView.addHeaderView(homeViewPagerHolder.mHolderView);

        return listView;
    }

    private class HomeAdapter extends SuperAdapter {

        public HomeAdapter(AbsListView absListView,List dataList) {
            super(absListView,dataList);
        }

        @Override
        protected BaseHolder getSpecialHodler() {
            return new HomeHodler();
        }

        @Override
        protected List preformLoading(int index) throws Exception {

            HomeJavaBean beanTemp = homeProcol.getData(index);
            List<AppInfo> tempData = beanTemp.list;
            if (beanTemp == null) {
                return null;
            }

            if (tempData == null || tempData.size() == 0 ) {
               return null;
            }
            return tempData;
        }

        @Override
        public void onNormalItemClick(AdapterView parent, View view, int position, long id) {
            Toast.makeText(UIUtils.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
        }
    }
}



