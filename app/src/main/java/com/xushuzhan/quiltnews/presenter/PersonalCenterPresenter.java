package com.xushuzhan.quiltnews.presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.avos.avoscloud.AVObject;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.activity.MainActivity;
import com.xushuzhan.quiltnews.ui.iview.IPersonalCenterView;
import com.xushuzhan.quiltnews.utils.DialogPopup;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public class PersonalCenterPresenter {
    public static final int NO_UPDATE = 0;
    ProgressDialog progressDialog;
    DialogPopup dialogPopup;
    IPersonalCenterView iPersonalCenterView;

    public PersonalCenterPresenter(IPersonalCenterView iPersonalCenterView) {
        this.iPersonalCenterView = iPersonalCenterView;
    }

    public void intentToLoginActivity() {
        iPersonalCenterView.intentToLogin();
    }

    public void setHeadPicture() {
        iPersonalCenterView.setHeadPicture();
    }

    public void intentToMyDiscuss() {
        iPersonalCenterView.intentToMyDiscuss();
    }

    public void hintHeadPicture() {
        iPersonalCenterView.hintHeadPicture();
    }

    public void signOut() {
        if (UserInfo.isNormalLogin == true) {
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT, null);
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.PASSWORD, null);
            SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.NICKNAME, null);
        }else if(UserInfo.isQQLogin==true){
            SharedPreferenceUtils.putString(APP.getAppContext(),"open_id",null);
        }else {
            iPersonalCenterView.showToast("请登录后再试");
        }
    }

    public void editNickName() {
        dialogPopup = new DialogPopup(iPersonalCenterView.getMyActivity(), "请输入昵称", "确定");
        dialogPopup.showPopupWindow();

        dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
            @Override
            public void onSendClick(View view, String content) {
                dialogPopup.dismiss();
                iPersonalCenterView.hintEditNickButton();
                UserInfo.nickName = content;
                iPersonalCenterView.setNickName(content);
                SharedPreferenceUtils.putString(APP.getAppContext(), "nick_name", content);
                AVObject user = AVObject.createWithoutData("_User", SharedPreferenceUtils.getString(APP.getAppContext(), "object_id"));
                // 修改 nick_name
                user.put("nick_name", content);
                // 保存到云端
                user.saveInBackground();

            }
        });
    }

    public void showIdead(){
        dialogPopup = new DialogPopup(iPersonalCenterView.getMyActivity(), "快把宝贵的意见告诉我们吧！", "发送");
        dialogPopup.showPopupWindow();

        dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
            @Override
            public void onSendClick(View view, String content) {
                iPersonalCenterView.showToast("感谢你的意见，我们一定会不断改进！");
                dialogPopup.dismiss();


            }
        });
    }

    public void checkUpdate(){
        progressDialog = new ProgressDialog
                (iPersonalCenterView.getMyActivity());
        //progressDialog.setTitle("提示：");
        progressDialog.setMessage("正在检查更新...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        handler.sendEmptyMessageDelayed(NO_UPDATE, 2000);

    }

    public void intentToNewsDetail(){

    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NO_UPDATE:
                    progressDialog.dismiss();
                    iPersonalCenterView.showToast("已经是最新版了");
                    break;
            }
        }
    };

}
