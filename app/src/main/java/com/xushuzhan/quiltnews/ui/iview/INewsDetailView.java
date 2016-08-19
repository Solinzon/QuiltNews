package com.xushuzhan.quiltnews.ui.iview;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public interface INewsDetailView {
    //webview设置url
    void showNewsDetail(String url);

    //显示PopupWindow评论窗
    void showPopupWindow();

    //获取评论框的内容
    void sendDiscussContent();

    //跳转到评论详情的Activity

    void intentToAllDiscuss();

    //设置评论条数
    void setDiscussCount(String count);

//    //获取新闻标题
//    String getNewsTitle();
//
//    //获取新闻图片的链接
//    String getNewsPicUrl();
//
    //获取新闻唯一码
    String getNewsUniqueKey();
//
//    String getNewsUrl();


}
