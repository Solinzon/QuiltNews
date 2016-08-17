package com.xushuzhan.quiltnews.modle.network.serverce;

import com.avos.avoscloud.S3Uploader;
import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.been.VideoBean;
import com.xushuzhan.quiltnews.modle.been.VideoBeanTest;
import com.xushuzhan.quiltnews.modle.been.VideoListBean;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public interface ApiServerce {
    /**
     * top(头条，默认) shehui(社会) guonei(国内) guoji(国际) yule(娱乐) tiyu(体育) junshi(军事) keji(科技) caijing(财经) shishang(时尚)
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("index?key=723940fe36afd7c744e1ac16773d99e7")
    Observable<NewsListBeen> getNewsList(@Field("type") String type);

    /**
     * 抽取新闻页面的图片和内容
     * @param appId
     * @param apiSign
     * @param url
     * @return
     */
    @FormUrlEncoded
    @POST("644-1")
    Observable<NewDetailBeen> getNewsDetail(@Field("showapi_appid") String appId,@Field("showapi_sign") String apiSign,@Field("url") String url);

    @FormUrlEncoded
    @POST("v2/videos/by_category.json")
    Observable<VideoListBean> getVideoList(@Field("client_id") String clientId,@Field("category") String category,
                                           @Field("count") String count,@Field("page") String page );


    @FormUrlEncoded
    @POST("dmxy/truevideourl/truevideourl")
    Observable<VideoBean> getVideaoURL(@Header("apikey") String videoApiKey, @Field("url") String url);


    @FormUrlEncoded
    @POST("dmxy/truevideourl/truevideourl")
    Observable<VideoBeanTest> getVideaoURLT(@Field("key")String key, @Field("playurl") String url);


}
