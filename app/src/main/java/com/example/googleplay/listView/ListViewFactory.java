package com.example.googleplay.listView;

import android.graphics.Color;
import android.widget.ListView;

import com.example.googleplay.utils.UIUtils;

/**
 * Created by yubin on 2016/5/21.
 */
public class ListViewFactory {
    public static ListView getListView(){
        ListView listView = new ListView(UIUtils.getContext());
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setFastScrollEnabled(true);
        return listView;
    }


}
