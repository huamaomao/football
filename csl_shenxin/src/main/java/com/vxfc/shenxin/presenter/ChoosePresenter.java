package com.vxfc.shenxin.presenter;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.vxfc.shenxin.util.Constants;
import com.vxfc.shenxin.view.IChooseView;

import java.util.Map;

public class ChoosePresenter {
    private IChooseView chooseView;

    /*******整个平台的Controller, 负责管理整个SDK的配置、操作等处理*****/
    private UMSocialService mController = UMServiceFactory.getUMSocialService(Constants.DESCRIPTOR);

    public ChoosePresenter(IChooseView chooseView){
        this.chooseView=chooseView;
    }

    /**
     * 授权。如果授权成功，则获取用户信息</br>
     */
    public void login(final SHARE_MEDIA platform) {
        mController.doOauthVerify((Activity)chooseView, platform, new SocializeListeners.UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                String uid = value.getString("uid");
                if (!TextUtils.isEmpty(uid)) {
                    getUserInfo(platform);
                } else {
                    chooseView.msgLongShow("授权失败");
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                chooseView.msgLongShow("取消授权");
            }
        });
    }

    /**
     * 注销本次登录</br>
     */
    public void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth((Activity)chooseView, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除"+platform.toString()+"平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除"+platform.toString()+"平台授权失败[" + status + "]";
                }
                chooseView.msgShow(showText);
            }
        });
    }

    /**
     * 获取授权平台的用户信息</br>
     */
    private void getUserInfo(SHARE_MEDIA platform) {
        mController.getPlatformInfo((Activity)chooseView, platform, new SocializeListeners.UMDataListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
//                String showText = "";
//                if (status == StatusCode.ST_CODE_SUCCESSED) {
//                    showText = "用户名：" + info.get("screen_name").toString();
//                    Log.d("#########", "##########" + info.toString());
//                } else {
//                    showText = "获取用户信息失败";
//                }
                if ( info != null ) {
                    chooseView.msgShow(info.toString());
                }
            }
        });
    }


}
