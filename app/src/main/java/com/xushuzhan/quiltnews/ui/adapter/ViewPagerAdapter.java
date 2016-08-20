package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.been.ViewPagerBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerBedNewsList;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.ui.activity.NewsDtailActivity;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/7/25.
 */
public class ViewPagerAdapter extends StaticPagerAdapter {
        public static ViewPagerBeen viewPagerContent;
    public static ArrayList<ViewPagerBeen.DataBean.ArticleBean> mviewPagerContent;
    public static final String TAG = "ViewPagerAdapter";
    private Context ctx;
    public ViewPagerAdapter(Context ctx) {
        this.ctx=ctx;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        final ImageView view = new ImageView(container.getContext());
        Subscriber<ViewPagerBeen> subscriber = new Subscriber<ViewPagerBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(ViewPagerBeen viewPagerBeen) {
                viewPagerContent = viewPagerBeen;
                Glide.with(ctx)
                        .load(viewPagerBeen.getData().getArticle().get(position).getImg())
                        .error(R.drawable.no_picture)
                        .into(view);
            }
        };

        RequestManagerBedNewsList.getInstance().getViewPager(subscriber);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return view;
    }

    @Override
    public int getCount() {
        return 5;
    }


}
