package com.chengzj.app.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;


    public MainPagerAdapter(FragmentManager fm, Fragment[] mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MainFragment.newInstance();
            case 1:
                return MainFragment.newInstance();
            case 2:
                return MainFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
