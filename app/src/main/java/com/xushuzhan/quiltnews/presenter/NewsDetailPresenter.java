package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.NewsDetailModle;
import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsDtail;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;

import rx.Subscriber;

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


}
