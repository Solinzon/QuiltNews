package com.xushuzhan.quiltnews.modle.impl;

import android.util.Log;

import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoBeanTest;
import com.xushuzhan.quiltnews.modle.been.VideoListBean;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideoList;
import com.xushuzhan.quiltnews.ui.adapter.VideoAdapter;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoListModle {
    public static final String TAG = "VideoListModle";
    VideoAdapter adapter;
    public VideoListModle(VideoAdapter adapter){
        this.adapter = adapter;
    }

    public void getVedioList(String category,String count,String page){
        Subscriber<VideoListBean> subscriber = new Subscriber<VideoListBean>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "getVedioList: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(VideoListBean videoListBean) {
                Log.d(TAG, "onNext: "+videoListBean.getVideos().get(0).getLink());
                //请求完成;
                for(int i = 0;i<videoListBean.getVideos().size();i++) {
                    makeUrlUseful(videoListBean.getVideos().get(i).getLink(),
                            videoListBean.getVideos().get(i).getTitle(),videoListBean.getVideos().get(i).getThumbnail()
                            );
                }
            }
        };
        RequestManagerVideoList.getInstance().getVideoList(subscriber,category,count,page);
    }

    public void makeUrlUseful(String youkuURL, final String title, final String thumbPicUrl){
        Subscriber<VideoBean> subscriber = new Subscriber<VideoBean>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "makeUrlUseful: 请求完成了");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(VideoBean videoBean) {
                //请求完成;
                FinalVideoListBean fvl = new FinalVideoListBean();
                fvl.setTitle(title);
                fvl.setThumbnail_pic_s(thumbPicUrl);
                fvl.setUrl(videoBean.getMp4());
                Log.d(TAG, "makeUrlUseful>>onNext: "+videoBean.getMp4());
                adapter.add(fvl);
               // adapter.insert(fvl,0);
            }
        };

        RequestManagerVideo.getInstance().getVideoUrl(subscriber,youkuURL);

    }
}
