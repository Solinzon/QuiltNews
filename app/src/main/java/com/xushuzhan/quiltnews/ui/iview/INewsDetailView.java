package com.xushuzhan.quiltnews.ui.iview;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public interface INewsDetailView {
    //webview显示内容
    void showNewsDetail(String content);

    //设置标题
    void setTitle(String title);

    //设置时间
    void setTime(String time);

    //
}
