package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.xushuzhan.quiltnews.R;

/**
 * Created by xushuzhan on 2016/7/25.
 */
public class ViewPagerAdapter extends StaticPagerAdapter {
    private Context ctx;
    public ViewPagerAdapter(Context ctx) {
        this.ctx=ctx;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(ctx);
        view.setImageResource(R.drawable.b);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
