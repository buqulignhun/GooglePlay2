package com.example.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStripExtend;
import com.example.googleplay.R;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.fragment.FragmentFactory;
import com.example.googleplay.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.app.ActionBar actionBar;
    private String[] strData;
    private MyAdapter myAdapter;
    private FragmentFactory fragmentFactory;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private PagerSlidingTabStripExtend tabs;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initData();
        initView();
        initEvent();
        super.onCreate(savedInstanceState);
    }

    private void initData() {
        strData = getApplicationContext().getResources().getStringArray((R.array.main_titles));
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout)findViewById(R.id.dl_main_drawer);
        initActionBar();
        initActionBarToggle();
        initSlidingTab();
    }


    private void initSlidingTab() {
        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
       /* myAdapter = new MyAdapter();
        pager.setAdapter(myAdapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStripExtend tabs = (PagerSlidingTabStripExtend) findViewById(R.id.tabs);
        tabs.setViewPager(pager);*/
      //测试FragmentPagerAdapter
      /*  myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myFragmentPagerAdapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStripExtend tabs = (PagerSlidingTabStripExtend) findViewById(R.id.tabs);
        tabs.setViewPager(pager);*/

        MainFragmentStatePagerAdapter mfspa = new MainFragmentStatePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mfspa);

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStripExtend) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }


    /**
     * 初始化AcrionBar
     */
    private void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("googlePlay");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);
    }

    private void initActionBarToggle() {
        mToggle = new ActionBarDrawerToggle(this,
                drawer,
                R.drawable.ic_drawer_am,
                R.string.open,
                R.string.close);
        mToggle.syncState();
        drawer.setDrawerListener(mToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      //  mToggle.onOptionsItemSelected()
        switch(item.getItemId()){
            case android.R.id.home:
                mToggle.onOptionsItemSelected(item);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initEvent() {
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * @param position 位置
             *    当页面选中时，初始化数据
             */
            @Override
            public void onPageSelected(int position) {
                BaseFragment selectedFragment = FragmentFactory.createFragment(position);
                selectedFragment.getPlv().loadingData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /*********************** 测试PagerAdapter  ********************************/
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (strData != null)
                return strData.length;
            return 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(strData[position]);
            tv.setGravity(Gravity.CENTER);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strData[position];
        }
    }
    /*********************** 测试PagerAdapter  ********************************/

    /*********************** 测试FragmentPagerAdapter  ********************************/
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public BaseFragment getItem(int position) {
            BaseFragment bfc = fragmentFactory.createFragment(position);
            LogUtils.sf("初始化了： " + bfc.getClass().getSimpleName());
            return bfc;
        }

        @Override
        public int getCount() {
            if (strData != null)
                return strData.length;
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strData[position];
        }
    }
    /*********************** 测试FragmentPagerAdapter  ********************************/

    /*********************** 测试FragmentStatePagerAdapter  ********************************/
    private class MainFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public MainFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            BaseFragment bfc = FragmentFactory.createFragment(position);
//LogUtils.sf("初始化了 "+ bfc.getClass().getSimpleName());
            return bfc;
        }

        @Override
        public int getCount() {
            if (strData != null)
                return strData.length;
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strData[position];
        }
    }
    /*********************** 测试FragmentStatePagerAdapter  ********************************/
}
