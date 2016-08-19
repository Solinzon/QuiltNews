package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.BedNewsListPresenter;
import com.xushuzhan.quiltnews.ui.activity.BedNewsDetailActivity;
import com.xushuzhan.quiltnews.ui.adapter.BedNewsListAdapter;
import com.xushuzhan.quiltnews.ui.adapter.InfoFixedPageAdapter;
import com.xushuzhan.quiltnews.ui.fragment.News.OtherInfoPageFragment;
import com.xushuzhan.quiltnews.ui.iview.IBedNewsListView;
import com.xushuzhan.quiltnews.utils.DownTimer;

import java.util.ArrayList;
import java.util.List;

public class BeforeBedNewsFragment extends Fragment implements IBedNewsListView,SwipeRefreshLayout.OnRefreshListener  {
    BedNewsListPresenter bedNewsListPresenter;
    EasyRecyclerView easyRecyclerView;
    BedNewsListAdapter adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_before_bed_news, container, false);
        initView();
        initTimer();
        bedNewsListPresenter = new BedNewsListPresenter(this,adapter);
        bedNewsListPresenter.guideUser();
        //bedNewsListPresenter.showBedNewsList();
        return view;
    }

    private void initTimer() {
        DownTimer timer = new DownTimer();//实例化
        timer.setTotalTime(60*1000);//设置毫秒数
        timer.setIntervalTime(5*1000);//设置间隔数
        timer.setTimerLiener(new DownTimer.TimeListener() {
            @Override
            public void onFinish() {
                Toast.makeText(APP.getAppContext(), "完成倒计时", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onInterval(long remainTime) {
                Toast.makeText(APP.getAppContext(), "剩余时间"+remainTime, Toast.LENGTH_SHORT).show();
            }
        });
        timer.start();
    }

    private void initView(){
        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.recyclerview_bed_news);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        easyRecyclerView.setRefreshListener(this);
        adapter = new BedNewsListAdapter(getContext());
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                bedNewsListPresenter.intentToBedNewsDetail(position);
            }
        });
    }




    @Override
    public void onRefresh() {
        adapter.clear();
        bedNewsListPresenter.showBedNewsList();
        easyRecyclerView.setRefreshing(false);
    }

    @Override
    public void showToast(String content) {

        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void intentToBenNewsDtail(String url) {
        Intent intent = new Intent(getContext(), BedNewsDetailActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bedNewsListPresenter = null;
    }
}
