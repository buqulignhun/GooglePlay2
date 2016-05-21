package com.example.googleplay.protocol;

import com.example.googleplay.javaBean.AppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by yubin on 2016/5/21.
 */
public class GameProtocol extends BaseProtocol<List<AppInfo>>{

    @Override
    public String getInterfaceKey() {
        return "game";
    }

    @Override
    public List<AppInfo> parseJsonData(String resultData) {
        Gson gson = new Gson();
        return gson.fromJson(resultData,new TypeToken<List<AppInfo>>(){}.getType());
    }
}
