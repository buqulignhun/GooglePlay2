package com.example.googleplay.protocol;

import com.example.googleplay.javaBean.AppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by yubin on 2016/5/21.
 */
public class ApplicationProtocal extends BaseProtocol<List<AppInfo>> {

    private Gson gson;

    @Override
    public String getInterfaceKey() {
        return "app";
    }

    @Override
    public List<AppInfo> parseJsonData(String resultData) {
        gson = new Gson();
        //泛型解析
        List<AppInfo> appInfos = gson.fromJson(resultData, new TypeToken<List<AppInfo>>() {
        }.getType());
        return appInfos;
    }
}
