package com.xushuzhan.quiltnews.modle.impl;

import android.app.Activity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.myinterface.QQBaseUiListener;

/**
 * Created by xushuzhan on 2016/8/21.
 */
public class UserModle {
    public static final String TAG = "UserModleTAG";
    Tencent mTencent;
    IUiListener baseUiListener;
    //QQ登录
    public void loginByQQ(Activity activity){
        baseUiListener = new QQBaseUiListener();
        mTencent = Tencent.createInstance("1105625820", APP.getAppContext());
        mTencent.login(activity, "all",baseUiListener);
    }

    public IUiListener getIUiListener(){
        return baseUiListener;
    }
}
