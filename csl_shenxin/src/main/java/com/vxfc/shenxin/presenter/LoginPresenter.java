package com.vxfc.shenxin.presenter;


import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.domian.ResponseModel;
import com.vxfc.shenxin.domian.User;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.ui.MainActivity;
import com.vxfc.shenxin.util.ActivityModel;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.ILoginView;

import java.util.UUID;

public class LoginPresenter {
    private ILoginView loginView;
    private SharedService service;

    public LoginPresenter(ILoginView loginView){
        this.loginView=loginView;
        service=new SharedService((Activity)loginView);
    }

    /***
     * 登陆
     * @param tel
     * @param pwd
     */
    public void login(String tel,String pwd){
        loginView.showLoading();
        MemberParam param=new MemberParam();
        if(!CommonUtil.isMobileNO(tel)){
            loginView.msgShow("手机号格式错误");
            return;
        }
        if(CommonUtil.isEmpty(pwd)){
            loginView.msgShow("密码不能为空");
            return;
        }
        if(pwd.length()<6||pwd.length()>16){
            loginView.msgShow("密码必须6至16位");
            return;
        }
        param.setDeviceId(UUID.randomUUID().toString());
        param.setTelphone(tel);
        param.setPassword(pwd);
        loginView.request(RequestUtil.requestLogin(param),new HttpModelHandler<String>() {
            @Override
            protected void onSuccess(String data, Response res) {
                if (!CommonUtil.isEmpty(data)){
                    User user= JSON.parseObject(data, User.class);
                    if (CommonUtil.notNull(user)){
                        service.putString(Dict.MEMBER_ID,user.getUserId());
                        Util.openActivity(MainActivity.class,null,(Activity)loginView, ActivityModel.ACTIVITY_MODEL_1,true);
                    }
                }
                loginView.hideLoading();
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                ResponseModel model= Util.handleServerException(e, loginView);
                loginView.hideLoading();
                if (CommonUtil.notNull(model)){
                    if("40002".equals(model.statusCode)){
                        loginView.msgShow(model.msg);
                        return;
                    }
                }

                loginView.msgShow("登录异常...");
            }
        });
    }

}
