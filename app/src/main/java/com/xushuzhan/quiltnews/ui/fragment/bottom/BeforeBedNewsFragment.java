package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.ui.adapter.InfoFixedPageAdapter;
import com.xushuzhan.quiltnews.ui.fragment.News.OtherInfoPageFragment;

import java.util.ArrayList;
import java.util.List;

public class BeforeBedNewsFragment extends Fragment {
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    private InfoFixedPageAdapter infoFixedPageAdapter;

    //储存fragment的数组
    private List<Fragment> mFragments;
    //tab选项卡中的标题
    private String[] titles=new String[]{"标题1","标题1","标题1","标题1","标题1"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_before_bed_news, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoFixedPageAdapter = new InfoFixedPageAdapter(getChildFragmentManager());
        infoFixedPageAdapter.setTitles(titles);//标题
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //传入标题和page的id
            mFragments.add(OtherInfoPageFragment.newInstance("test"));
        }
        //把要显示的fragment集合传给adapter
        infoFixedPageAdapter.setFragments(mFragments);

        //设置tablayout的模式()
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //给viewPager设置适配器
        mViewPager.setAdapter(infoFixedPageAdapter);
        //TabLayout绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);


        Toast.makeText(getActivity(), "睡前精选", Toast.LENGTH_SHORT).show();
    }




}
