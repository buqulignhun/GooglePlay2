package com.example.googleplay.protocol;

import com.example.googleplay.Config.Constants;
import com.example.googleplay.javaBean.HomeJavaBean;
import com.example.googleplay.utils.UIUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yubin on 2016/5/20.
 */
public abstract class BaseProtocol<T> {
    private static final long TIMEDURATION = 6 * 60 * 1000;//有效时长6分钟

    HttpUtils mHttpUtils;

    public T getData(int index) throws HttpException, IOException {
        File file = UIUtils.getContext().getFilesDir();
        File cacheFile = new File(file, getInterfaceKey() + "." + index);
        if (cacheFile.exists() && cacheFile.length() != 0) {
            //文件存在，在文件中读取
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                //获取时间
                String timeMillis = reader.readLine();
//System.out.println("文件中： "+timeMillis);
                //判断是否过期
                if ((System.currentTimeMillis() - Long.parseLong(timeMillis)) < TIMEDURATION) {
                    System.out.println("从文件中获取数据");
                    String cacheJson = reader.readLine();
                    return parseJsonData(cacheJson);
                }else {
//System.out.println("文件过期");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                        reader = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        //从网络上获取
        String jsonString = getDataFromNet(index);
        return parseJsonData(jsonString);

    }

    //// TODO: 2016/5/20 考虑是否多次创建
    private String getDataFromNet(int index) throws HttpException,IOException {

        if (mHttpUtils == null) {
            mHttpUtils = new HttpUtils();
        }
        String data = null;

        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter("index", index + "");
        ResponseStream responseStream = mHttpUtils.sendSync(HttpRequest.HttpMethod.GET, Constants.URL.SERVICE + getInterfaceKey(), requestParams);

        int retCode = responseStream.getStatusCode();
        if (retCode == 200) {
            data = responseStream.readString();

            //在文件夹中缓存数据
            File file = UIUtils.getContext().getFilesDir();
            File cacheFile = new File(file, getInterfaceKey() + "." + index);

            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(cacheFile));
                //写当前时间
                writer.write(System.currentTimeMillis() + "");
                //换行
                writer.newLine();
                //写json数据
                writer.write(data);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null){
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            throw new HttpException();
            //网络错误情况处理
        }
        return data;
    }

    /**
     * @return 访问地址的后缀名
     * 必须实现，但又不知道怎么实现的方法
     */
    public abstract String getInterfaceKey();


    public abstract T parseJsonData(String resultData);
}
