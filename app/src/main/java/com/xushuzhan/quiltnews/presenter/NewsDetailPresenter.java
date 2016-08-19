package com.xushuzhan.quiltnews.presenter;


import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;



/**
 * Created by xushuzhan on 2016/8/17.
 */
public class NewsDetailPresenter {
    public static final String TAG = "NewsDetailPresenter";
    INewsDetailView iNewsDetailView;
    public NewsDetailPresenter(INewsDetailView iNewsDetailView){
        this.iNewsDetailView = iNewsDetailView;
    }

    public void showNewsDetail(String url){
        iNewsDetailView.showNewsDetail(url);
    }

    public void showDiscussWindow(){
        iNewsDetailView.showPopupWindow();
    }

    public void sendDiscuss(){
//        String url = iNewsDetailView.getNewsUrl();
//        String picUrl = iNewsDetailView.getNewsPicUrl();
//        String title = iNewsDetailView.getNewsTitle();
//        String uniqueKey = iNewsDetailView.getNewsUniqueKey();
        iNewsDetailView.sendDiscussContent();
    }

    public void intentToAllDiscuss(){
       iNewsDetailView.intentToAllDiscuss();

    }


}
