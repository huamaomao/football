package com.vxfc.shenxin.presenter;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.util.Constants;
import com.vxfc.shenxin.view.IChooseView;

import java.util.Map;

public class ChoosePresenter {
    public static final int QQ=1;
    public static final int WEIXIN=2;
    public static final int SINA=3;

    private IChooseView chooseView;

    public ChoosePresenter(IChooseView chooseView){
        this.chooseView=chooseView;
    }


    public void login(int type){
        switch (type){
            case QQ:
                final Tencent tencent=Tencent.createInstance(Constants.APP_KEY_QQ,(Activity)chooseView);
                tencent.login((Activity)chooseView,Constants.SCOPE_QQ,new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Log.i(o);
                        chooseView.msgShow("授权成功");

                        UserInfo info = new UserInfo((Activity)chooseView,tencent.getQQToken());
                        info.getUserInfo(new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                                Log.i(o);
                            }

                            @Override
                            public void onError(UiError uiError) {
                                Log.i(uiError);
                            }

                            @Override
                            public void onCancel() {
                                Log.i("onCancel");
                            }
                        });
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.i(uiError);
                    }

                    @Override
                    public void onCancel() {
                        chooseView.msgShow("取消授权");
                    }
                });
                break;
            case WEIXIN:

                break;
            case  SINA:

                break;
            default:
                break;
        }
    }
}
