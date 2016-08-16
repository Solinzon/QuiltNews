package com.xushuzhan.quiltnews.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.been.NewsListBeen;
import com.xushuzhan.quiltnews.modle.network.net.RequestManagerNewsList;
import com.xushuzhan.quiltnews.presenter.FirstTabFragmentPresenter;
import com.xushuzhan.quiltnews.ui.activity.NewsDtailActivity;
import com.xushuzhan.quiltnews.ui.adapter.NewsAdapter;
import com.xushuzhan.quiltnews.ui.adapter.ViewPagerAdapter;
import com.xushuzhan.quiltnews.ui.iview.IFirstTabView;
import com.xushuzhan.quiltnews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/8/16.
 */
public class FirstTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,IFirstTabView{
    View mView;
    private EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    private FirstTabFragmentPresenter firstTabFragmentPresenter;
    private Handler handler = new Handler();
    public static final String TAG = "FirstTabFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_first_viewpager_fragment,container,false);
        firstTabFragmentPresenter = new FirstTabFragmentPresenter(this);
        setRecyclerView();
        return mView;
    }

    private void setRecyclerView() {
        recyclerView= (EasyRecyclerView) mView.findViewById(R.id.recycler_view_view_pager_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter = new NewsAdapter(getContext()));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(getContext());
                header.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.GRAY));
                header.setHintPadding(0, 0, 0, (int) Utils.convertDpToPixel(8, (getContext())));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Utils.convertDpToPixel(190,(getContext()))));
                header.setAdapter(new ViewPagerAdapter((getContext())));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        if(firstTabFragmentPresenter!=null) {
            firstTabFragmentPresenter.showNewsList();
        }

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                firstTabFragmentPresenter.showNewsDetail(position);
            }
        });
        recyclerView.setRefreshListener(this);

    }
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "刷新中", Toast.LENGTH_SHORT).show();
                recyclerView.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void addDataToRecyclerView(NewsListBeen newsListBeen) {
        adapter.addAll(newsListBeen.getResult().getData());
    }

    @Override
    public void intentToNewsDetailActivity(String url) {
        Intent intent = new Intent(getContext(),NewsDtailActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firstTabFragmentPresenter = null;
    }
}
