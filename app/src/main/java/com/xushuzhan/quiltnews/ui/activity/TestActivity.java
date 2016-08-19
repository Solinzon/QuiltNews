package com.xushuzhan.quiltnews.ui.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.VideoListModle;
import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoBeanTest;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsDtail;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerVideo;
import com.xushuzhan.quiltnews.utils.DialogPopup;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.util.List;

import rx.Subscriber;

public class TestActivity extends AppCompatActivity {
    public static final String TAG = "TestActivityTAG";
    Button button;
    Button bt;
    Button bt4;
    Button creatObject;
    Button queryUser;
    Button b;
    
    Button  bt7;
    Button BT8;
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

        creatObject = (Button) findViewById(R.id.button);
        creatObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creat();
            }
        });
        queryUser = (Button) findViewById(R.id.button5);
        queryUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qurey();
            }
        });
        b= (Button) findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogPopup(TestActivity.this).showPopupWindow();
            }
        });
        
        bt7 = (Button) findViewById(R.id.button7);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveUser();
            }
        });
        
        BT8 = (Button) findViewById(R.id.button8);
        BT8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
            }
        });
    }

    private void getUser() {
//        Log.d(TAG, "getUser: NAME>>"+SharedPreferenceUtils.getString(TestActivity.this,"user_name"));
//        Log.d(TAG, "getUser: PASSWORD>>"+SharedPreferenceUtils.getString(TestActivity.this,"password"));
      //  Log.d(TAG, "getUser: NAME>>"+SharedPreferenceUtils.getString(APP.getAppContext(),"user_name"));
        Log.d(TAG, "getUser: "+ UserInfo.isLogin());
        Log.d(TAG, "getUser:>> "+UserInfo.userName);
    }

    private void saveUser() {
//        SharedPreferenceUtils.putString(TestActivity.this,"user_name","Xushuzhan");
        Log.d(TAG, "saveUser: "+UserInfo.userName);
//        SharedPreferenceUtils.putString(APP.getAppContext(),"user_name","test");
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

    public void creat(){
//        AVObject todoFolder = new AVObject("aaa");// 构建对象
//        todoFolder.put("name", "打游戏");// 设置名称
//        todoFolder.put("user","小明");// 设置优先级
//        todoFolder.put("url", "www.google.com");// 设置优先级
//        todoFolder.saveInBackground();// 保存到服务端

//        AVObject news = new AVObject("news");// 构建对象
//        news.put("uniquekey","160818135424737");
//        news.put("title","一夜间臭名昭著的明星，永远除不去身上的恶名");
//        news.put("url","http://mini.eastday.com/mobile/160818135424737.html?qid=juheshuju");
//        news.put("pic_url","http://06.imgmini.eastday.com/mobile/20160818/20160818135424_88018a331b409231ba5135b786623dcc_1_mwpm_03200403.jpeg");
//        news.saveInBackground();// 保存到服务端

        AVObject news = new AVObject("comment");// 构建对象
        news.put("user_name","SuperBigHead");
        news.put("news_uniquekey","160818135424737");
        news.put("discuss_content","其实我不太喜欢这种放荡不羁的少年");
        news.put("title","一夜间臭名昭著的明星，永远除不去身上的恶名");
        news.put("url","http://mini.eastday.com/mobile/160818135424737.html?qid=juheshuju");
        news.put("pic_url","http://06.imgmini.eastday.com/mobile/20160818/20160818135424_88018a331b409231ba5135b786623dcc_1_mwpm_03200403.jpeg");
        news.saveInBackground();// 保存到服务端
    }

    public void qurey(){
        Log.d(TAG, "qurey: 查询模块启动了");
        //查询用户评论的所有新闻
//        AVQuery<AVObject> query = new AVQuery<>("comment");
//        query.whereEqualTo("user_name", "小明");   //查询小明在某条新闻的评论
//        query.findInBackground(new FindCallback<AVObject>() {
//            @Override
//            public void done(List<AVObject> list, AVException e) {
//                for(int i = 0;i<list.size();i++){
//                    //Log.d(TAG, "done: >>>>>>>>>>>>>>>>>>>"+list.get(i));
//                    Log.d(TAG, "done: ====================news_title:"+list.get(i).get("news_title"));
//                    Log.d(TAG, "done: >>>>>>>>>>>>>>>>>>>>url"+list.get(i).get("url"));
//                    Log.d(TAG, "done: --------------------pic_url"+list.get(i).get("pic_url"));
//                    Log.d(TAG, "done: ====================news_uniquekey"+list.get(i).get("news_uniquekey"));
//                    Log.d(TAG, "done: ++++++++++++++++++++discuss_content"+list.get(i).get("discuss_content"));
//                    if(e!=null)
//                    Log.d(TAG, "done: error"+e.getMessage());
//                }
//            }
//        });

        //查询新闻的所有评论
        AVQuery<AVObject> query = new AVQuery<>("comment");
        query.whereEqualTo("news_uniquekey", "10000");   //查询小明在某条新闻的评论
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for(int i = 0;i<list.size();i++){
                    //Log.d(TAG, "done: >>>>>>>>>>>>>>>>>>>"+list.get(i));
                    Log.d(TAG, "done: ======user+name"+list.get(i).get("user_name"));
//                    Log.d(TAG, "done: ====================news_title:"+list.get(i).get("news_title"));
//                    Log.d(TAG, "done: >>>>>>>>>>>>>>>>>>>>url"+list.get(i).get("url"));
//                    Log.d(TAG, "done: --------------------pic_url"+list.get(i).get("pic_url"));
//                    Log.d(TAG, "done: ====================news_uniquekey"+list.get(i).get("news_uniquekey"));
                    Log.d(TAG, "done: ++++++++++++++++++++discuss_content"+ list.get(i).get("discuss_content").toString());

                    Log.d(TAG, "done: ********************discuss——time："+ list.get(i).get("createdAt").toString());
                    if(e!=null)
                        Log.d(TAG, "done: error"+e.getMessage());
                }
            }
        });
    }
}
