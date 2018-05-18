package com.chengzj.app.module.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chengzj.app.R;
import com.chengzj.app.base.BaseFragment;
import com.chengzj.app.data.entity.NewsList;
import com.google.gson.Gson;

import butterknife.Bind;

/**
 * Created by chengzj on 2017/6/17.
 */

public class MainFragment extends BaseFragment implements MainContract.View {
    MainContract.Presenter mMainPresenter;
    @Bind(R.id.message)
    TextView mTextView;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        new MainPresenter(mainFragment);
        return mainFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMainPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mMainPresenter = presenter;
    }

    @Override
    public void showNewList(NewsList newsList) {
        mTextView.setText(new Gson().toJson(newsList));
    }
}
