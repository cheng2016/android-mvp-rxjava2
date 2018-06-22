package com.chengzj.app.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chengzj.app.R;
import com.chengzj.app.base.BaseActivity;
import com.chengzj.app.module.main.MainActivity;
import com.vondear.rxtools.view.dialog.RxDialogLoading;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.email)
    AutoCompleteTextView emailView;
    @Bind(R.id.password)
    EditText passwordView;

    String email;
    String password;
    RxDialogLoading rxDialogLoading;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.logo_img).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                email = emailView.getText().toString().trim();
                password = passwordView.getText().toString().trim();
                if (TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("用户名和密码不能为空！");
                } else {
                    if (!RegexUtils.isEmail(email)) {
                        ToastUtils.showShort("请输入正确的邮箱！");
                    } else {
                        login();
                    }
                }
                break;
            case R.id.logo_img:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        rxDialogLoading = new RxDialogLoading(LoginActivity.this);
        rxDialogLoading.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rxDialogLoading.cancel();
                startActivity(new Intent().setClass(LoginActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
