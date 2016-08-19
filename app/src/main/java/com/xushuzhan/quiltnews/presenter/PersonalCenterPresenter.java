package com.xushuzhan.quiltnews.presenter;

import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.ui.iview.IPersonalCenterView;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

/**
 * Created by xushuzhan on 2016/8/18.
 */
public class PersonalCenterPresenter {
    IPersonalCenterView iPersonalCenterView;
    public PersonalCenterPresenter(IPersonalCenterView iPersonalCenterView){
        this.iPersonalCenterView = iPersonalCenterView;
    }

    public void intentToLoginActivity(){
        iPersonalCenterView.intentToLogin();
    }

    public void setHeadPicture(){
        iPersonalCenterView.setHeadPicture();
    }

    public void intentToMyDiscuss(){
        iPersonalCenterView.intentToMyDiscuss();
    }

    public void hintHeadPicture(){
        iPersonalCenterView.hintHeadPicture();
    }

    public void signOut(){
        SharedPreferenceUtils.putString(APP.getAppContext(), UserInfo.ACCOUNT,null);
        SharedPreferenceUtils.putString(APP.getAppContext(),UserInfo.PASSWORD,null);
    }
}
