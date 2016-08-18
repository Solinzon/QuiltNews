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

    public void showNewsDetaile(String url){
        iNewsDetailView.showNewsDetail(url);
    }

}
