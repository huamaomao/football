package com.vxfc.shenxin.presenter;


import android.app.Activity;
import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.tencent.connect.UserInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.domian.AppInfo;
import com.vxfc.shenxin.domian.User;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.ui.MainActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Constants;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IChooseView;
import java.util.UUID;

public class ChoosePresenter {
    public static final int QQ=1;
    public static final int WEIXIN=2;
    public static final int SINA=3;

    private IChooseView chooseView;
    private SharedService service;
    public ChoosePresenter(IChooseView chooseView){
        this.chooseView=chooseView;
        service=new SharedService((Activity)chooseView);
    }


    public void login(int type){
        switch (type){
            case QQ:
                final Tencent tencent=Tencent.createInstance(Constants.APP_KEY_QQ,(Activity)chooseView);
                tencent.login((Activity)chooseView,Constants.SCOPE_QQ,new IUiListener() {
                    @Override
                    public void onComplete(Object o) {

                        final UserInfo info = new UserInfo((Activity)chooseView,tencent.getQQToken());
                        info.getUserInfo(new IUiListener() {
                            @Override
                            public void onComplete(Object o) {
                                chooseView.showLoading();
                                AppInfo appInfo= JSON.parseObject(o.toString(),AppInfo.class);
                                if (CommonUtil.isNull(appInfo)) return;
                                MemberParam param=new MemberParam();
                                param.setType(String.valueOf(ChoosePresenter.QQ));
                                param.setNickName(appInfo.nickname);
                                param.setDeviceId(UUID.randomUUID().toString());
                                param.setPhoto(appInfo.figureurl_qq_1);
                                param.setAppId(tencent.getOpenId());
                                chooseView.request(RequestUtil.requestRegister3rd(param),new HttpModelHandler<String>() {
                                    @Override
                                    protected void onSuccess(String data, Response res) {
                                        if (!CommonUtil.isEmpty(data)){
                                            User user= JSON.parseObject(data, User.class);
                                            if (CommonUtil.notNull(user)){
                                                service.putString(Dict.MEMBER_ID,user.getUserId());
                                                Util.openActivity(MainActivity.class, null, (Activity) chooseView, ActivityModel.ACTIVITY_MODEL_1, true);
                                            }
                                        }
                                        chooseView.hideLoading();
                                        chooseView.msgShow("登录成功");
                                    }

                                    @Override
                                    protected void onFailure(HttpException e, Response res) {
                                        chooseView.hideLoading();
                                        chooseView.msgShow("登录失败");
                                    }
                                });
                            }

                            @Override
                            public void onError(UiError uiError) {
                                Log.i(uiError);
                            }

                            @Override
                            public void onCancel() {

                            }
                        });

                    }

                    @Override
                    public void onError(UiError uiError) {
                        chooseView.msgShow("授权失败");
                        Log.i(uiError);
                    }

                    @Override
                    public void onCancel() {
                        chooseView.msgShow("取消授权");
                    }
                });
                break;
            case WEIXIN:
                IWXAPI iwxapi=WXAPIFactory.createWXAPI((Activity)chooseView,Constants.APP_KEY_WEIXIN,true);
                iwxapi.registerApp(Constants.APP_KEY_WEIXIN);
                if (!iwxapi.isWXAppInstalled()) {
                    chooseView.msgShow("未安装微信");
                    return;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = Constants.WEXIN_SCOPE;
                req.state = UUID.randomUUID().toString();
                iwxapi.sendReq(req);
                break;
            case  SINA:

                break;
            default:
                break;
        }
    }
}
