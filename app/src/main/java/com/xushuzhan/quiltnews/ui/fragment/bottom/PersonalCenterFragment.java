package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.NewsInfo;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.presenter.PersonalCenterPresenter;
import com.xushuzhan.quiltnews.ui.activity.LoginActivity;
import com.xushuzhan.quiltnews.ui.activity.MyCollectionActivity;
import com.xushuzhan.quiltnews.ui.activity.MyDiscussActivity;
import com.xushuzhan.quiltnews.ui.iview.IPersonalCenterView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import java.io.FileNotFoundException;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener, IPersonalCenterView {
    public static final String TAG = "PersonalCenterTAG";
    View view;
    ImageView userLogin;
    ImageView ViewModeIV;
    RelativeLayout nightMode;
    RelativeLayout fontMode;
    RelativeLayout myDiscuss;
    RelativeLayout collect;
    RelativeLayout download;
    RelativeLayout idea;
    RelativeLayout update;
    RelativeLayout signOut;
    ImageView editNickName;
    TextView nickName;
    TextView ViewModeTV;

    ImageView ReadModeIV;
    TextView ReadModeTV;
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
        ViewModeIV = (ImageView) view.findViewById(R.id.iv_font_mode);
        ViewModeTV = (TextView) view.findViewById(R.id.tv_font_mode);
        ReadModeIV = (ImageView) view.findViewById(R.id.iv_read_mode);
        ReadModeTV = (TextView) view.findViewById(R.id.tv_read_mode);
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
        editNickName = (ImageView) view.findViewById(R.id.iv_edit_nick_name);
//        download = (RelativeLayout) view.findViewById(R.id.rl_pc_my_down);
//        download.setOnClickListener(this);
        idea = (RelativeLayout) view.findViewById(R.id.rl_pc_idea);
        idea.setOnClickListener(this);
        update = (RelativeLayout) view.findViewById(R.id.rl_pc_check_update);
        update.setOnClickListener(this);
        nickName = (TextView) view.findViewById(R.id.personal_certen_login_now);
        signOut = (RelativeLayout) view.findViewById(R.id.rl_pc_sign_out);
        signOut.setOnClickListener(this);
        try {
            checkInfo();
        } catch (Exception e) {
            Log.d(TAG, "initView: "+e.getMessage());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_center_night_mode:
                changeNightModel();
                break;
            case R.id.rl_personal_center_font_mode:
                if (!NewsInfo.isChecked) {
                    NewsInfo.isShowPic = false;
                    NewsInfo.isChecked = true;
                    ViewModeTV.setText("图片模式");
                    ViewModeIV.setImageResource(R.drawable.picture_mode);

                } else {
                    NewsInfo.isShowPic = true;
                    NewsInfo.isChecked = false;
                    ViewModeTV.setText("文字模式");
                    ViewModeIV.setImageResource(R.drawable.font_mode);
                }
                break;
            case R.id.rl_personal_center_my_discuss:
                personalCenterPresenter.intentToMyDiscuss();
                break;
            case R.id.iv_user_center_login:
            case R.id.personal_certen_login_now:
                personalCenterPresenter.intentToLoginActivity();
                break;
            case R.id.rl_pc_my_collect:
                startActivity(new Intent(getContext(), MyCollectionActivity.class));
                break;
//            case R.id.rl_pc_my_down:
//                Toast.makeText(getContext(), "抱歉-这个功能正在开发", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.rl_pc_idea:
                personalCenterPresenter.showIdead();
                break;
            case R.id.rl_pc_check_update:
                personalCenterPresenter.checkUpdate();
                break;
            case R.id.rl_pc_sign_out:
                personalCenterPresenter.hintHeadPicture();
                personalCenterPresenter.signOut();
                break;

            case R.id.iv_edit_nick_name:
                personalCenterPresenter.editNickName();
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
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHeadPicture() {
        if (UserInfo.isQQLogin || UserInfo.isNormalLogin) {
            userLogin.setImageResource(R.drawable.touxiang);
            userLogin.setClickable(false);
            nickName.setClickable(false);
            nickName.setText(UserInfo.nickName);

        }

    }

    @Override
    public void hintHeadPicture() {
        userLogin.setImageResource(R.drawable.personl_certen_user);
        userLogin.setClickable(true);
        nickName.setClickable(true);
        nickName.setText("请先登录");

    }

    @Override
    public void intentToMyDiscuss() {
        startActivity(new Intent(getContext(), MyDiscussActivity.class));
    }

    @Override
    public Activity getMyActivity() {
        return PersonalCenterFragment.this.getActivity();
    }


    @Override
    public void hintEditNickButton() {
        editNickName.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setNickName(String content) {
        nickName.setText(content);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        personalCenterPresenter = null;
    }

    private void checkInfo() {
        if ((UserInfo.isQQLogin || UserInfo.isNormalLogin)) {
            if (!UserInfo.nickName.equals("匿名用户")) {
                userLogin.setClickable(false);
                editNickName.setVisibility(View.INVISIBLE);
            } else {
                editNickName.setOnClickListener(this);
            }

        } else if (!UserInfo.isQQLogin || !UserInfo.isNormalLogin) {
            editNickName.setVisibility(View.INVISIBLE);
            userLogin.setClickable(false);
        } else {
            editNickName.setOnClickListener(this);
        }

        if (!NewsInfo.isChecked) {
            ViewModeTV.setText("文字模式");
            ViewModeIV.setImageResource(R.drawable.font_mode);
        } else {
            ViewModeTV.setText("图片模式");
            ViewModeIV.setImageResource(R.drawable.picture_mode);
        }

        if (!NewsInfo.isNightMode) {
            ReadModeTV.setText("夜间模式");
            ReadModeIV.setImageResource(R.drawable.night_mode);
        } else {
            ReadModeTV.setText("日间模式");
            ReadModeIV.setImageResource(R.drawable.daytime_mode);

        }
    }

    private void changeNightModel() {
        if (NewsInfo.isNightMode) {
            ReadModeIV.setImageResource(R.drawable.daytime_mode);
            ReadModeTV.setText("夜间模式");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            NewsInfo.isNightMode = false;
        } else {
            ReadModeIV.setImageResource(R.drawable.night_mode);
            ReadModeTV.setText("日间模式");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            NewsInfo.isNightMode = true;

        }
        getActivity().recreate();

    }


}
