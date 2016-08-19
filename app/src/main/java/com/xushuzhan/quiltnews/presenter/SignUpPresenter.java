package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.iview.ISignUpView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public class SignUpPresenter {
    ISignUpView iSignUpView;
    public SignUpPresenter(ISignUpView iSignUpView){
        this.iSignUpView = iSignUpView;
    }

    public void signUp(){
        final String account = iSignUpView.getAccount();
        final String password = iSignUpView.getPassword();
        if (account == null ||account.length()==0) {
            iSignUpView.showError(iSignUpView.getEditTextAccount(),"用户名不能为空！");
        } else if (password == null || password.length() < 7) {
            iSignUpView.showError(iSignUpView.getEditTextPassword(),"密码不能小于了7位！");
        } else {
            AVUser user = new AVUser();
            user.setUsername(account);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        iSignUpView.showToast("注册成功");
                        SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT,account);
                        SharedPreferenceUtils.putString(APP.getAppContext(),UserInfo.PASSWORD,password);
                        UserInfo.userName = account;
                        iSignUpView.moveToMainActivity();

                    } else {
                        iSignUpView.showToast("注册失败，请检查你的网络连接！");

                    }
                }
            });
        }
    }
}
