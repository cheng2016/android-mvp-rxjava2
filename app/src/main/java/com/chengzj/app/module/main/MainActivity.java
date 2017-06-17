package com.chengzj.app.module.main;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.chengzj.app.R;
import com.chengzj.app.base.BaseActivity;

public class MainActivity extends BaseActivity
{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MainFragment mainFragment = MainFragment.newInstance();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content,mainFragment);
    }
}
