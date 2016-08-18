package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener{
    View view;
    ImageView userLogin;
    RelativeLayout nightMode;
    RelativeLayout fontMode;
    RelativeLayout myDiscuss;
    RelativeLayout collect;
    RelativeLayout download;
    RelativeLayout idea;
    RelativeLayout update;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal_certen,container,false);
        initView();
        return view;
    }

    private void initView() {
        nightMode = (RelativeLayout) view.findViewById(R.id.rl_personal_center_night_mode);
        nightMode.setOnClickListener(this);
        fontMode = (RelativeLayout) view.findViewById(R.id.rl_personal_center_font_mode);
        fontMode.setOnClickListener(this);
        myDiscuss = (RelativeLayout) view.findViewById(R.id.rl_personal_center_my_discuss);
        myDiscuss.setOnClickListener(this);
        userLogin = (ImageView) view.findViewById(R.id.iv_user_center_login);
        userLogin.setOnClickListener(this);
        collect = (RelativeLayout) view.findViewById(R.id.rl_pc_my_collect);
        collect.setOnClickListener(this);
        download = (RelativeLayout) view.findViewById(R.id.rl_pc_my_down);
        download.setOnClickListener(this);
        idea = (RelativeLayout) view.findViewById(R.id.rl_pc_idea);
        idea.setOnClickListener(this);
        update = (RelativeLayout) view.findViewById(R.id.rl_pc_check_update);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_personal_center_night_mode:
                Toast.makeText(getContext(), "夜间模式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_personal_center_font_mode:
                Toast.makeText(getContext(), "文字模式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_personal_center_my_discuss:
                Toast.makeText(getContext(), "我的评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_user_center_login:
                Toast.makeText(getContext(), "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_my_collect:
                Toast.makeText(getContext(), "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_my_down:
                Toast.makeText(getContext(), "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_idea:
                Toast.makeText(getContext(), "意见", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_check_update:
                Toast.makeText(getContext(), "更新", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
