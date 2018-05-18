package com.chengzj.app.module.main;

import com.chengzj.app.data.entity.NewsList;
import com.chengzj.app.module.BasePresenter;
import com.chengzj.app.module.BaseView;

/**
 * Created by chengzj on 2017/6/17.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void showNewList(NewsList newsList);
    }

    interface Presenter extends BasePresenter{

    }
}
