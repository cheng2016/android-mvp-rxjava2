package com.chengzj.app.module.main;

import com.chengzj.app.module.BasePresenter;
import com.chengzj.app.module.BaseView;
import com.chengzj.app.data.source.NewsList;

/**
 * Created by chengzj on 2017/6/17.
 */

public class MainContract {
    interface View extends BaseView<Presenter>{
        void showNewList(NewsList newsList);
    }

    interface Presenter extends BasePresenter{

    }
}
