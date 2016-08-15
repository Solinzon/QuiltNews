package com.xushuzhan.quiltnews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;


/**
 * Created by xushuzhan on 2016/7/26.
 */
public class NewsAdapter extends RecyclerArrayAdapter {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent, R.layout.item_news_list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
