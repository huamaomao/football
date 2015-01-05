package com.vxfc.shenxin.sina;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.vxfc.shenxin.ui.BaseActivity;
import com.vxfc.shenxin.util.Util;

/**
     * 微博认证授权回调类。
     * 1. SSO 授权
     *    该回调才会被执行。
     */
public class AuthListener implements WeiboAuthListener {
        private Oauth2AccessToken mAccessToken;
        private BaseActivity activity;
        public AuthListener(BaseActivity activity){
            this.activity=activity;
        }
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(activity, mAccessToken);
                activity.showMsg("授权成功");
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code");
                String message = "授权失败";
                if (!Util.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                activity.showMsg(message);
            }
        }

    @Override
    public void onWeiboException(WeiboException e) {
        e.printStackTrace();
    }

    @Override
    public void onCancel() {
        activity.showMsg("取消授权");
    }
}