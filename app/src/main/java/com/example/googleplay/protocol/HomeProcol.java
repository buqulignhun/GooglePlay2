package com.example.googleplay.protocol;

import com.example.googleplay.javaBean.HomeJavaBean;
import com.google.gson.Gson;

/**
 * Created by yubin on 2016/5/20.
 */
public class HomeProcol extends BaseProtocol<HomeJavaBean> {
    @Override
    public String getInterfaceKey() {
        return "home";
    }

    @Override
    public HomeJavaBean parseJsonData(String resultData) {
        Gson gson = new Gson();
        HomeJavaBean homeBean = gson.fromJson(resultData, HomeJavaBean.class);
        return homeBean;
    }

}
