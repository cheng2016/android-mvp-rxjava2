package com.chengzj.app.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blankj.utilcode.util.ToastUtils;
import com.chengzj.app.R;
import com.chengzj.app.base.BaseActivity;
import com.chengzj.app.module.login.LoginActivity;

public class SplashActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
//        RxBarTool.hideStatusBar(this);//隐藏状态栏 并 全屏
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        RxToast.showToast(this, "正在检查版本更新...", 500);
        ToastUtils.showShort("正在检查版本更新");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent().setClass(SplashActivity.this, LoginActivity.class));
                finish();
            }
        },2000);
    }
}
