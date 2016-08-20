package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.presenter.PersonalCenterPresenter;
import com.xushuzhan.quiltnews.ui.activity.LoginActivity;
import com.xushuzhan.quiltnews.ui.activity.MyDiscussActivity;
import com.xushuzhan.quiltnews.ui.iview.IPersonalCenterView;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener, IPersonalCenterView {
    View view;
    ImageView userLogin;
    RelativeLayout nightMode;
    RelativeLayout fontMode;
    RelativeLayout myDiscuss;
    RelativeLayout collect;
    RelativeLayout download;
    RelativeLayout idea;
    RelativeLayout update;
    RelativeLayout signOut;
    TextView loginAccount;
    PersonalCenterPresenter personalCenterPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal_certen, container, false);
        initView();
        personalCenterPresenter = new PersonalCenterPresenter(this);
        personalCenterPresenter.setHeadPicture();
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
        loginAccount = (TextView) view.findViewById(R.id.personal_certen_login_now);
        signOut = (RelativeLayout) view.findViewById(R.id.rl_pc_sign_out);
        signOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_center_night_mode:
                Toast.makeText(getContext(), "夜间模式—暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_personal_center_font_mode:
                Toast.makeText(getContext(), "文字模式-暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_personal_center_my_discuss:
                personalCenterPresenter.intentToMyDiscuss();
                break;
            case R.id.iv_user_center_login:
            case R.id.personal_certen_login_now:
                personalCenterPresenter.intentToLoginActivity();
                break;
            case R.id.rl_pc_my_collect:
                Toast.makeText(getContext(), "收藏-暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_my_down:
                Toast.makeText(getContext(), "下载-暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_idea:
                Toast.makeText(getContext(), "意见-暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_check_update:
                Toast.makeText(getContext(), "更新", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_pc_sign_out:
                personalCenterPresenter.hintHeadPicture();
                personalCenterPresenter.signOut();
                break;
            default:
                break;
        }
    }

    @Override
    public void intentToLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void showToast(String content) {

    }

    @Override
    public void setHeadPicture() {
        if (UserInfo.userName != null) {
            userLogin.setImageResource(R.drawable.touxiang);
            userLogin.setClickable(false);
            loginAccount.setClickable(false);
            loginAccount.setText(UserInfo.userName);
        }

    }

    @Override
    public void hintHeadPicture() {
        userLogin.setImageResource(R.drawable.personl_certen_user);
        userLogin.setClickable(true);
        loginAccount.setClickable(true);
        loginAccount.setText("请先登录");

    }

    @Override
    public void intentToMyDiscuss() {
        startActivity(new Intent(getContext(), MyDiscussActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        personalCenterPresenter = null;
    }
}
