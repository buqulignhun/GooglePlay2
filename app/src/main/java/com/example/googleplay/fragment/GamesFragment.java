package com.example.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googleplay.Holder.BaseHolder;
import com.example.googleplay.Holder.HomeHodler;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseFragmentComment;
import com.example.googleplay.base.PageLoadingView;
import com.example.googleplay.base.SuperAdapter;
import com.example.googleplay.javaBean.AppInfo;
import com.example.googleplay.listView.ListViewFactory;
import com.example.googleplay.protocol.GameProtocol;
import com.example.googleplay.utils.UIUtils;
import com.lidroid.xutils.exception.HttpException;

import java.io.IOException;
import java.util.List;

/**
 * Created by yubin on 2016/5/13.
 */
public class GamesFragment extends BaseFragment {

    private List<AppInfo> gamesApps;
    private GameProtocol gamesProtocol;

    @Override
    public PageLoadingView.ReturnResult loadingData() {
        gamesProtocol = new GameProtocol();
        try {
            gamesApps = gamesProtocol.getData(0);
        } catch (HttpException e) {
            e.printStackTrace();
            return PageLoadingView.ReturnResult.ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return PageLoadingView.ReturnResult.ERROR;
        }

        if(gamesApps == null || gamesApps.size() == 0){
            return PageLoadingView.ReturnResult.EMPTY;
        }

        return PageLoadingView.ReturnResult.SUCCESS;
    }

    @Override
    protected View initSuccessPage() {
        ListView gameList = ListViewFactory.getListView();
        gameList.setAdapter(new GamesAdapter(gameList,gamesApps));
        return gameList;
    }

    private class GamesAdapter extends SuperAdapter {

        public GamesAdapter(AbsListView absListView, List dataList) {
            super(absListView, dataList);
        }

        @Override
        protected List<AppInfo> preformLoading(int index) throws Exception {
            return gamesProtocol.getData(index);
        }

        @Override
        protected BaseHolder getSpecialHodler() {
            return new HomeHodler();
        }
    }
}
