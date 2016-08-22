package com.xushuzhan.quiltnews.modle.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.FinalVideoListBean;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class ViedoListViewHolder extends BaseViewHolder<FinalVideoListBean> {
    TextView playCount;
    Button discuss;
    TextView discussCount;
    Button share;
    JCVideoPlayerStandard jcVideoPlayerStandard;
    public ViedoListViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
//        playCount = $(R.id.tv_video_play_count);
//        discuss = $(R.id.bt_discuss_eyeshot_video);
//        discussCount = $(R.id.tv_video_discuss_count);
//        share = $(R.id.bt_video_share);
        jcVideoPlayerStandard = $(R.id.custom_videoplayer_standard);
    }

    @Override
    public void setData(FinalVideoListBean data) {
        super.setData(data);

     //   playCount.setText(data.getView_count());
//        discussCount.setText(data.getComment_count());
//        jcVideoPlayerStandard.thumbImageView.set

        jcVideoPlayerStandard.setUp(data.getUrl()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, data.getTitle());

        Glide.with(getContext())
                .load(data.getThumbnail_pic_s())
                .error(R.drawable.touxiang)
                .into(jcVideoPlayerStandard.thumbImageView);


    }



}
