package com.example.googleplay.listView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by yubin on 2016/5/21.
 */
public class TestListView extends ListView {
    public TestListView(Context context) {
        this(context, null);
    }

    public TestListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



  /*  @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //System.out.println("++++ListView  onTouchEvent++++");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE: {

                break;
            }
        }
            return super.onTouchEvent(ev);
    }*/


}
