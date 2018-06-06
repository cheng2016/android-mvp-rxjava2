package com.chengzj.app.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chengzj.app.base.BaseFragment;
import com.squareup.haha.perflib.Main;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;


    public MainPagerAdapter(FragmentManager fm, Fragment[] mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MainFragment.newInstance();
            case 1:
                return MainFragment.newInstance();
            case 2:
                return MainFragment.newInstance();
            case 3:
                return MainFragment.newInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
