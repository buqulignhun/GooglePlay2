package com.example.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay.utils.UIUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yubin on 2016/5/13.
 */
public abstract class BaseFragment extends Fragment {

    private PageLoadingView plv;

    public PageLoadingView getPlv() {
        return plv;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //todo 3防止plv的重复创建
        if (plv == null) {
            plv = new PageLoadingView(UIUtils.getContext()) {
                @Override
                protected ReturnResult loadingDataTask() {
                    return BaseFragment.this.loadingData();
                }


                @Override
                public View initSuccessPage() {
                    return BaseFragment.this.initSuccessPage();
                }
            };
        }/*else{
            ((ViewGroup)plv.getParent()).removeView(plv);
        }*/
        return plv;
    }


    /**
     * 子类继承子类实现自己数据的加载，在子线程中
     *
     * @return 加载数据后的状态
     */
    public abstract PageLoadingView.ReturnResult loadingData();

    /**
     * 子类继承此类实现加载数据后成功页面的初始化
     *
     * @return 成功加载的页面
     */
    protected abstract View initSuccessPage();

    public boolean checkDataEmpty(Object data){
        if(data == null)
              return true;

        if(data instanceof List){
            if(((List) data).size()==0)
                 return true;
        }

        if(data instanceof Map){
            if(((Map) data).size() == 0)
                return true;
        }

        return false;
    }
}
