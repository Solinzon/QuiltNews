package com.xushuzhan.quiltnews.presenter;

import com.xushuzhan.quiltnews.ui.iview.IPersonalCenterView;

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

}
