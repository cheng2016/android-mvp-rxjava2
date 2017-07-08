package com.chengzj.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chengzj.app.App;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Created by chengzj on 2017/6/17.
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view,savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    protected abstract int getLayoutId();

    protected abstract void init(View view, Bundle savedInstanceState);
}
