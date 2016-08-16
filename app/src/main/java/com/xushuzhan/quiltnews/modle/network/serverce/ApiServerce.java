package com.xushuzhan.quiltnews.modle.network.serverce;

import com.xushuzhan.quiltnews.modle.been.NewDetailBeen;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;



import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("644-1")
    Observable<NewDetailBeen> getNewsDetail(@Field("showapi_appid") String appId,@Field("showapi_sign") String apiSign,@Field("url") String url);

}
