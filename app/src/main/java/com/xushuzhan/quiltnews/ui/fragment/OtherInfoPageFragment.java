package com.xushuzhan.quiltnews.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.ui.adapter.NewsAdapter;
import com.xushuzhan.quiltnews.ui.adapter.ViewPagerAdapter;
import com.xushuzhan.quiltnews.utils.Utils;

/**
 * Created by xushuzhan on 2016/7/24.
 */
public class OtherInfoPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String PAGER_POSITION = "position";
    private View mView;
    private EasyRecyclerView recyclerView;
    private NewsAdapter adapter;
    String[] A = new String[]{"dd","DD","dd","dd","DD","dd","dd"};
    private Handler handler = new Handler();
    public static OtherInfoPageFragment newInstance() {
    /*    //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER_POSITION, pageId);
        */
        //实例化
        OtherInfoPageFragment fragment = new OtherInfoPageFragment();
    //    fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取出bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.getInt(PAGER_POSITION);
        }
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_info_viewpager_page, container, false);
        }

        setRecyclerView();
        return mView;
    }

    private void setRecyclerView() {
        recyclerView= (EasyRecyclerView) mView.findViewById(R.id.recycler_view_view_pager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter = new NewsAdapter(getContext()));
        //adapter.addAll();

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
}
