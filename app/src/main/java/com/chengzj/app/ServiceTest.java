package com.chengzj.app;

import com.chengzj.app.data.source.NewsList;
import com.chengzj.app.data.source.remote.HttpFactory;
import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by chengzj on 2017/6/18.
 */

public class ServiceTest {

    public static void main(String[] args) throws Exception {
        IMainService iMainService = HttpFactory.createRetrofit2(IMainService.class);
        Disposable disposable = iMainService.getNewsList("L295","10")
                .subscribe(new Consumer<NewsList>() {
                    @Override
                    public void accept(@NonNull NewsList newsList) throws Exception {
                        System.out.println(new Gson().toJson(newsList));
                    }
                });
        CompositeDisposable mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }
}
