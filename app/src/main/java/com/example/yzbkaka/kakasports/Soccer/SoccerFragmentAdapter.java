package com.example.yzbkaka.kakasports.Soccer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by yzbkaka on 19-7-18.
 */

public class SoccerFragmentAdapter extends FragmentPagerAdapter {  //切换页适配器
    private int PAGE_COUNT = 2;  //切换页面的数量
    private SoccerVSFragment soccerVSFragment;  //赛程的碎片
    private SoccerGradeFragment soccerGradeFragment;  //积分榜的碎片


    public SoccerFragmentAdapter(FragmentManager fm) {
        super(fm);
        soccerVSFragment = new SoccerVSFragment();
        soccerGradeFragment = new SoccerGradeFragment();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case EnglandActivity.PAGE_ONE:  //如果是页面1
                fragment = soccerVSFragment;
                break;
            case EnglandActivity.PAGE_TWO:  //如果是页面2
                fragment = soccerGradeFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
