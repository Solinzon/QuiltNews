package com.xushuzhan.quiltnews.ui.iview;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public interface IPersonalCenterView {
    //跳转到登录界面
    void intentToLogin();

    //显示Toast
    void showToast(String content);

    //设置头像
    void setHeadPicture();
}
