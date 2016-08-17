package com.xushuzhan.quiltnews.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.VideoListModle;
import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoBeanTest;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsDtail;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideo;

import rx.Subscriber;

public class TestActivity extends AppCompatActivity {
    public static final String TAG = "TestActivity";
    Button button;
    Button bt;
    Button bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovie();
            }
        });
    bt = (Button) findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getDetail();
            }
        });

        bt4= (Button) findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVideo();
            }
        });
    }

    public void getMovie(){
        Subscriber<NewsListBeen> subscriber = new Subscriber<NewsListBeen>() {

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
            public void onNext(NewsListBeen newsListBeen) {
                //请求完成;
                Log.d(TAG, "onNext: "+newsListBeen.getResult().getData().get(0).getTitle());
                Log.d(TAG, "onNext: "+newsListBeen.getResult().getData().get(0).getUrl());
                Log.d(TAG, "onNext: "+newsListBeen.getReason());

                getDetail(newsListBeen.getResult().getData().get(0).getUrl().replace("mobile","a"));
                Log.d(TAG, "onNext: "+newsListBeen.getResult().getData().get(0).getUrl().replace("mobile","a"));
            }
        };

            RequestManagerNewsList.getInstance().getNewsList(subscriber,"top");


    }

    public void getDetail(String url){
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
                Log.d(TAG, "onNext: "+newDetailBeen.getShowapi_res_body().getNeirong());
            }
        };

        RequestManagerNewsDtail.getInstance().getNewsDetail(sb,url);
    }
    public void getVideo(){
        Subscriber<VideoBean> sb = new Subscriber<VideoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(VideoBean videoBean) {
                Log.d(TAG, "onNext: >>>>>>"+videoBean.getMp4());

            }
        };

     //   RequestManagerVideo.getInstance().getVideoUrl(sb,"http://apis.baidu.com/dmxy/truevideourl/truevideourl?url=http://v.youku.com/v_show/id_XMTY4NjMwOTIyNA==.html");
       RequestManagerVideo.getInstance().getVideoUrl(sb,"http://v.youku.com/v_show/id_XMTY4NjMwOTIyNA==.html");

    }
}
