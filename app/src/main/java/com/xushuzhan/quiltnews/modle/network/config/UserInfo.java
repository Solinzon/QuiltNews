package com.xushuzhan.quiltnews.modle.network.config;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class UserInfo {
    public static String userName = null;
    public static boolean TAG;
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";

    public static boolean isLogin() {
        String account = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.ACCOUNT);
        String password = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.PASSWORD);

        AVUser.logInInBackground(account, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    TAG = true;
                    userName = avUser.getUsername();
                    Log.d("TestActivityTAG", "done: " + userName + TAG);
                } else {
                    TAG = false;
                }
            }
        });
        Log.d("TestActivityTAG", "isLogin: " + TAG);
        return TAG;

    }

    public static void tryLogin() {
        String account = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.ACCOUNT);
        String password = SharedPreferenceUtils.getString(APP.getAppContext(), UserInfo.PASSWORD);
        try {
            if (account != null && password != null) {
                AVUser.logInInBackground(account, password, new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        if (e == null) {
                            userName = avUser.getUsername();
                        } else {
                            Log.d("TestActivityTAG", "done: 自动登录失败");
                        }
                    }
                });
            }
        } catch (Exception ee) {
            Log.d("TestActivityTAG", "done: 自动登录失败");
        }

    }


}
