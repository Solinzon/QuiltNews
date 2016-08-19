package com.xushuzhan.quiltnews.presenter;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.been.BedNewsListBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewsList;
import com.xushuzhan.quiltnews.ui.adapter.BedNewsListAdapter;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsListView;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsListPresenter {
    public static final String TAG = "BedNewsListPresenter";
    IBedNewsListView iBedNewsListView;
    BedNewsListAdapter adapter;
    BedNewsListBeen newsList;
    public BedNewsListPresenter(IBedNewsListView iBedNewsListView, BedNewsListAdapter adapter){
        this.iBedNewsListView = iBedNewsListView;
        this.adapter = adapter;
    }

    public void showBedNewsList(){
        Subscriber<BedNewsListBeen> subscriber = new Subscriber<BedNewsListBeen>() {

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Log.d(TAG, "onCompleted: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(BedNewsListBeen newsListBeen) {
                adapter.addAll(newsListBeen.getRetData());
                Log.d(TAG, "onNext: "+newsListBeen.getRetData().get(0).getTitle());
                Log.d(TAG, "onNext: "+newsListBeen.getRetData().get(0).getImage_url());
                Log.d(TAG, "onNext: "+newsListBeen.getRetData().get(0).getAbstractX());
                Log.d(TAG, "onNext: "+newsListBeen.getRetData().get(0).getUrl());
                newsList = newsListBeen;
            }
        };

        RequestManagerBedNewsList.getInstance().getNewsList(subscriber);
    }

    public void guideUser(){
        iBedNewsListView.showToast("每次下拉都可以更新新闻哟");
    }

    public void intentToBedNewsDetail(int position){
        Log.d(TAG, "intentToBedNewsDetail: +"+newsList.getRetData().get(position).getUrl());
        iBedNewsListView.intentToBenNewsDtail(newsList.getRetData().get(position).getUrl());
    }
}
