package com.vxfc.shenxin.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.domian.Token;
import com.vxfc.shenxin.domian.User;
import com.vxfc.shenxin.domian.WeixinInfo;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.presenter.ChoosePresenter;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.ui.BaseActivity;
import com.vxfc.shenxin.ui.MainActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;

import java.util.UUID;

/**
 * Created by Administrator on 2015/3/10 0010.
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private SharedService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        service=new SharedService(this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    /****
     * ErrCode	ERR_OK = 0(用户同意)
     ERR_AUTH_DENIED = -4（用户拒绝授权）
     ERR_USER_CANCEL = -2（用户取消）
     * @param intent
     */
    private void handleIntent(Intent intent) {

        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
            //用户同意
            request(RequestUtil.requestWeixinAuth(resp.code),new HttpModelHandler<String>() {
                @Override
                protected void onSuccess(String data, Response res) {
                    Token token= JSON.parseObject(data,Token.class);
                    if(CommonUtil.notNull(token)){
                        // get  userinfo
                        request(RequestUtil.requestWeixinUserinfo(token.getAccess_token()),new HttpModelHandler<String>() {
                            @Override
                            protected void onSuccess(String data, Response res) {
                                WeixinInfo info=JSON.parseObject(data,WeixinInfo.class);
                                if(CommonUtil.notNull(info)&&!CommonUtil.isEmpty(info.appid)){
                                    //ok
                                    MemberParam param=new MemberParam();
                                    param.setAppId(info.appid);
                                    param.setPhoto(info.niceName);
                                    param.setType(String.valueOf(ChoosePresenter.WEIXIN));
                                    param.setDeviceId(UUID.randomUUID().toString());
                                    param.setNickName(info.niceName);
                                    request(RequestUtil.requestRegister3rd(param), new HttpModelHandler<String>() {
                                        @Override
                                        protected void onSuccess(String data, Response res) {
                                            User user= JSON.parseObject(data, User.class);
                                            if (CommonUtil.notNull(user)){
                                                service.putString(Dict.MEMBER_ID,user.getUserId());
                                                Util.openActivity(MainActivity.class, null,  WXEntryActivity.this, ActivityModel.ACTIVITY_MODEL_1, true);
                                            }
                                            showMsg("授权成功");
                                        }

                                        @Override
                                        protected void onFailure(HttpException e, Response res) {
                                            Log.i(e) ;
                                            showMsg("授权失败");
                                        }
                                    });

                                }
                            }

                            @Override
                            protected void onFailure(HttpException e, Response res) {
                                showMsg("授权失败");
                            }
                        });
                    }
                }

                @Override
                protected void onFailure(HttpException e, Response res) {
                    showMsg("授权失败");
                }
            });

        }else {
            Toast.makeText(this,"授权失败",Toast.LENGTH_LONG).show();

        }
        finish();
    }
}
