package com.xushuzhan.quiltnews.modle.myinterface;

import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xushuzhan.quiltnews.APP;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.utils.SharedPreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xushuzhan on 2016/8/21.
 */
public class QQBaseUiListener implements IUiListener {
    public static final String TAG ="789789789";
    @Override
    public void onComplete(Object o) {
        if (o != null) {
            JSONObject jsonObject = (JSONObject) o;
            try {
                String token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
                String expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
                final String openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
                        Log.d(TAG, "onComplete: "+token);
                        Log.d(TAG, "onComplete: "+expires);
                        Log.d(TAG, "onComplete: "+openId);
                UserInfo.isQQLogin = true;

                SharedPreferenceUtils.putString(APP.getAppContext(),"open_id",openId);
                SharedPreferenceUtils.putString(APP.getAppContext(),"token",token);


                final AVUser user = new AVUser();
                user.setUsername(openId);
                user.setPassword(token);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            SharedPreferenceUtils.putString(APP.getAppContext(),"object_id",user.getObjectId());
                            UserInfo.nickName = "匿名用户";
                            UserInfo.userName = openId;
                            UserInfo.isQQLogin = true;
                        } else {

                        }
                    }
                });
            } catch (JSONException e) {

            }
        }
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(APP.getAppContext(), "登录失败，请重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {

    }
}
