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
        Subscriber<NewDetailBeen> sb = new Subscriber<NewDetailBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewDetailBeen newDetailBeen) {
                Log.d(TAG, "onNext: "+newDetailBeen.getShowapi_res_code());

                iNewsDetailView.setTime(newDetailBeen.getShowapi_res_body().getShijian());
                iNewsDetailView.setTitle(newDetailBeen.getShowapi_res_body().getTitle());
                iNewsDetailView.showNewsDetail(newDetailBeen.getShowapi_res_body().getNeirong());

            }
        };

        RequestManagerNewsDtail.getInstance().getNewsDetail(sb,url.replace("mobile","a"));
    }
}
