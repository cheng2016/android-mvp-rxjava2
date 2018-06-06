package com.chengzj.app.module.main;

import com.chengzj.app.data.entity.NewsList;
import com.chengzj.app.data.source.remote.HttpApi;
import com.chengzj.app.data.source.remote.HttpFactory;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chengzj on 2017/6/17.
 */

public class MainPresenter implements MainContract.Presenter{
    private MainContract.View view;
    private HttpApi mHttpApi;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        view.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
        mHttpApi =  HttpFactory.createRetrofit2(HttpApi.class);
    }

    @Override
    public void subscribe() {
        getNewList();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    public void getNewList() {
        mHttpApi.getNewsList("L295","10")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsList newsList) {
                        view.showNewList(newsList);
                        Logger.d("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
