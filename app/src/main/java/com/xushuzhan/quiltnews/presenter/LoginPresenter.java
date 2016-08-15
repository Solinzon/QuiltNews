package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.xushuzhan.quiltnews.ui.iview.IloginView;

/**
 * Created by xushuzhan on 2016/8/12.
 */
public class LoginPresenter {
    IloginView iloginView;
    public LoginPresenter(IloginView iloginView){
        this.iloginView = iloginView;
    }

    public void login(){
        String account= iloginView.getAccount();
        String password=  iloginView.getPassword();
        if (account == null || password.length()==0) {
            iloginView.setError(iloginView.getEditAccount(),"账号不能为空");
        } else if (password.length() < 7) {
            iloginView.setError(iloginView.getEditPassword(),"密码格式错误");
        } else {
            AVUser.logInInBackground(account, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                        iloginView.showToast("登陆成功");
                        iloginView.toMainActivity();
                    } else {
                        iloginView.showToast(e.toString());
                    }
                }
            });
        }
    }

    public void signUp(){
        iloginView.toSignUpActivity();
    }
}
