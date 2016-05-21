package com.example.googleplay.Config;

import com.example.googleplay.utils.LogUtils;

/**
 * Created by yubin on 2016/5/11.
 */
public class Constants {
    public static final int	DEBUGLEVEL	= LogUtils.LEVEL_ALL;//LEVEL_ALL,显示所有的日志,OFF:关闭日志的显示
    public static final int PAGESIZE = 20;

    public static final class URL{
         public static String SERVICE = "http://172.24.52.203:8080/GooglePlayServer/";
         public static String HOMEURL = SERVICE+"home";
         public static String BASEHOMEICONURL = "http://172.24.52.203:8080/GooglePlayServer/image?name=";
    }
}
