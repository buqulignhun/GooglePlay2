package com.example.googleplay.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.googleplay.Config.Constants;
import com.example.googleplay.Holder.BaseHolder;
import com.example.googleplay.Holder.LoadingMoreDataHolder;
import com.example.googleplay.threadPool.ThreadPoolFactory;
import com.example.googleplay.utils.UIUtils;

import java.util.List;

/**
 * Created by yubin on 2016/5/16.
 */


public abstract class SuperAdapter<BEANTYPE> extends BaseAdapter {
    List<BEANTYPE> dataList;
    public static final int LOADING_MORE = 0;
    public static final int LOADING_NORMAL = 1;
    private View view;
    private LoadingMoreDataHolder loadingDataHodler;
    private LoadingTask mTask;
    private AbsListView absListView;

    public SuperAdapter(AbsListView absListView, List<BEANTYPE> dataList) {
        this.dataList = dataList;
        this.absListView = absListView;

        initEvent();
    }

    // TODO: 2016/5/21 点击事件响应不了 
    /**
     * 点击事件监听
     */
    private void initEvent() {
       /* absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
System.out.println("点击事件");
                if (getItemViewType(position) == LOADING_MORE) {
                    onLoadingMoreData();
                } else {
                    onNormalItemClick(parent, view, position, id);
                }
            }
        });*/

        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("+++++++++++++++++++++++++++点击事件+++++++++++++++++++++++++");
            }
        });

}

    /**
     * 覆盖此方法，普通item，被点击后响应点击事件
     */
    public void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * 此方法，在加载失败后，点击重试加载
     */
    private void onLoadingMoreData() {
        loadingMoreData();
    }


    @Override
    public int getCount() {
        if (dataList != null) {
            //加载更多，与没有数据  视图,只加一次
            return dataList.size() + 1;
        }
        return 0;
    }

    BaseHolder baseHodler = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /********************  视图部分 begin   **************************/

        //todo 尾部不参与缓存
        if (convertView != null) {
            //缓存问题
            if (getItemViewType(position) == LOADING_MORE) {
                baseHodler = getLoadingMoreHolder();
            } else
                baseHodler = (BaseHolder) convertView.getTag();
        } else {
            if (getItemViewType(position) == LOADING_MORE) {
                baseHodler = getLoadingMoreHolder();
            } else {
                baseHodler = getSpecialHodler();
            }
        }

        convertView = baseHodler.mHolderView;
        /********************  视图部分 end  **************************/

        /********************  数据部分 begin  **************************/
        if (getItemViewType(position) == LOADING_MORE) {
            //更新加载视图，并且真正的加载数据
            baseHodler.refreshData(LOADING_MORE);
            if (hasMoreData())
                loadingMoreData();
        } else {
            BEANTYPE data = dataList.get(position);
            baseHodler.refreshData(data);
        }
        /********************  数据部分 end  **************************/
        return convertView;
    }

    /**
     * 是否有更多数据
     *
     * @return 默认返回true，子类可以覆盖此方法来返回是否有更多数据
     */
    private boolean hasMoreData() {
        return true;
    }


    /**
     * 加载更多数据
     * 防止反复加载数据
     */
    private void loadingMoreData() {
        if (mTask == null) {
            mTask = new LoadingTask();
            ThreadPoolFactory.getNormalThreadPool().execute(mTask);
        }
    }


    private class LoadingTask extends Thread {

        @Override
        public void run() {
            //加载更多数据
            List<BEANTYPE> listTemp = null;
            int state = LoadingMoreDataHolder.STATE_LOADING;
            try {
                listTemp = preformLoading(dataList.size());
                if (listTemp == null || listTemp.size() < Constants.PAGESIZE) {
                    state = LoadingMoreDataHolder.STATE_NONE;
                } else {
                    state = LoadingMoreDataHolder.STATE_LOADING;
                }
            } catch (Exception e) {
                state = LoadingMoreDataHolder.STATE_NOMOREDATA;
                e.printStackTrace();
            }
            //刷新界面
            //中转变量
            final int tempState = state;
            final List<BEANTYPE> listDataTemp = listTemp;

            UIUtils.postTask(this, new Runnable() {
                @Override
                public void run() {
                    //列表更新
                    if (listDataTemp != null) {
                        dataList.addAll(listDataTemp);
                        notifyDataSetChanged();
                    }
                    //加载更多视图更新
                    loadingDataHodler.refreshData(tempState);
                }
            });

            mTask = null;
        }
    }

    protected abstract List<BEANTYPE> preformLoading(int index) throws Exception;


    protected abstract BaseHolder getSpecialHodler();


    /**
     * @return 只初始化一次，防止多次初始化
     */
    private BaseHolder<Integer> getLoadingMoreHolder() {
        if (loadingDataHodler == null)
            loadingDataHodler = new LoadingMoreDataHolder();
        return loadingDataHodler;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return LOADING_MORE;
        }
        return LOADING_NORMAL;
    }
}

