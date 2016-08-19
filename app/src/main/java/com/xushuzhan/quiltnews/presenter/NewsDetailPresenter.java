package com.xushuzhan.quiltnews.presenter;


import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.modle.been.NewsDiscussBeen;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;

import java.util.ArrayList;
import java.util.List;


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

    public void showDiscussCount(){
            AVQuery<AVObject> query = new AVQuery<>("comment");
            query.whereEqualTo("news_uniquekey",iNewsDetailView.getNewsUniqueKey());   //查询某条新闻的所有评论
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    try {

                        for (int i = 0; i < list.size(); i++) {
                            iNewsDetailView.setDiscussCount(list.size()+"");
                        }


                    }catch (Exception ee){

                    }


                }
            });

        }



}
