package com.example.googleplay.fragment;



import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseFragmentComment;

/**
 * Created by yubin on 2016/5/13.
 */
public class FragmentFactory {
    private static BaseFragment mFragment = null;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_APPLICATION = 1;
    private static final int FRAGMENT_GAMES = 2;
    private static final int FRAGMENT_SUBJECT = 3;
    private static final int FRAGMENT_RECOMMEND = 4;
    private static final int FRAGMENT_CLASSFICATION = 5;
    private static final int FRAGMENT_RANK = 6;
    private static SparseArray<BaseFragment> cacheFragment = new SparseArray<BaseFragment>();

    /**
     * @param fragmentType 类型
     * @return
     *   FragmentStatePagerAdapter 没有缓存
     */
    public static BaseFragment createFragment(int fragmentType){

        BaseFragment tempFragemnt = cacheFragment.get(fragmentType);
        if(tempFragemnt != null){
            return tempFragemnt;
        }

        switch(fragmentType){
            case FRAGMENT_HOME:
                mFragment = new HomeFragment();
                break;
            case FRAGMENT_APPLICATION:
                mFragment = new ApplicationFragment();
                break;
            case FRAGMENT_GAMES:
                mFragment = new GamesFragment();
                break;
            case FRAGMENT_SUBJECT:
                mFragment = new SubjectFragment();
                break;
            case FRAGMENT_RECOMMEND:
                mFragment = new RecommendFragment();
                break;
            case FRAGMENT_CLASSFICATION:
                mFragment = new ClassficationFragment();
                break;
            case FRAGMENT_RANK:
                mFragment = new RankFragment();
                break;
           default:
                break;
        }
        cacheFragment.put(fragmentType,mFragment);
        return mFragment;
    }
}
