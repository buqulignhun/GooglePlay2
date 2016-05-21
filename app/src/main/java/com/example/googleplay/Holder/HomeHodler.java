package com.example.googleplay.Holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStripExtend;
import com.example.googleplay.Config.Constants;
import com.example.googleplay.R;
import com.example.googleplay.javaBean.AppInfo;
import com.example.googleplay.javaBean.HomeJavaBean;
import com.example.googleplay.utils.BitmapHelper;
import com.example.googleplay.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by yubin on 2016/5/16.
 */
public class HomeHodler extends BaseHolder<AppInfo> {

    private ImageView ivIcon;
    private TextView tvAppName;
    private RatingBar brComment;
    private TextView tvSize;
    private TextView tvDesc;

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_list, null);
        ivIcon = (ImageView)view.findViewById(R.id.item_list_lv_icon);
        tvAppName = (TextView) view.findViewById(R.id.item_list_tv_appName);
        brComment = (RatingBar) view.findViewById(R.id.item_list_rb_comments);
        tvSize = (TextView) view.findViewById(R.id.item_list_tv_size);
        tvDesc = (TextView)view.findViewById(R.id.item_list_tv_desc);
        return view;
    }

    @Override
    public void refreshData(AppInfo data) {
        //反复重复创建
      /*  BitmapUtils bitmapUtils = new BitmapUtils(UIUtils.getContext());
        System.out.println("bitmap: "+Constants.URL.BASEHOMEICONURL+data.iconUrl);
        bitmapUtils.display(ivIcon, Constants.URL.BASEHOMEICONURL+data.iconUrl);*/

        BitmapHelper.display(ivIcon, Constants.URL.BASEHOMEICONURL+data.iconUrl);
        tvAppName.setText(data.name);
        brComment.setRating(data.stars);
        tvSize.setText(Formatter.formatFileSize(UIUtils.getContext(),data.size));
        tvDesc.setText(data.des);
    }

}
