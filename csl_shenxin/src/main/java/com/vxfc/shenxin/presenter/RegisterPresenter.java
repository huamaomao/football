package com.vxfc.shenxin.presenter;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.exception.HttpServerException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.domian.ResponseModel;
import com.vxfc.shenxin.domian.Result;
import com.vxfc.shenxin.domian.User;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.service.SharedService;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.util.Util;
import com.vxfc.shenxin.view.IRegisterView;

import java.util.UUID;

/**
 * Created by Hua_ on 2014/12/26.
 */
public class RegisterPresenter {
    private IRegisterView registerView;
    private SharedService service;
    public RegisterPresenter(IRegisterView registerView){
        this.registerView=registerView;
        service=new SharedService((Activity)registerView);
    }
   public void  doRegisterFirst(final String tel){
       registerView.showLoading();
       MemberParam param=new MemberParam();
       param.setTelphone(tel);
      registerView.request(RequestUtil.requestSms(param),new HttpModelHandler<String>(){
          @Override
          protected void onSuccess(String data, Response res) {
              Log.i(data);
              Result result= JSON.parseObject(data,Result.class);
              registerView.hideLoading();
              if (CommonUtil.notNull(result)&& Dict.RESULT.equals(result.result)){
                   StringBuilder builder=new StringBuilder("我们已给你的手机号码");
                   builder.append(tel);
                   builder.append("发送一条验证码(5分钟有效)");
                   registerView.setRegisterStatus();
                  registerView.setTelRemarks(builder.toString());
              }else {
                  registerView.msgShow("短信验证码发送失败...");
              }

          }

          @Override
          protected void onFailure(HttpException e, Response res) {
              ResponseModel model=Util.handleServerException(e,registerView);
              registerView.hideLoading();
              if (CommonUtil.notNull(model)){
                  if ("40003".equals(model.statusCode)||"10040".equals(model.statusCode)){
                      registerView.msgShow(model.msg);
                      return;
                  }
              }else {
                  registerView.msgShow("短信验证码发送失败...");
              }


              //registerView.responseFailure(e,res);
          }
      });
   }

  public void repeatSms(String tel){
      registerView.showLoading();
      MemberParam param=new MemberParam();
      param.setTelphone(tel);
      registerView.request(RequestUtil.requestSms(param),new HttpModelHandler<String>(){
          @Override
          protected void onSuccess(String data, Response res) {
              registerView.hideLoading();
              Result result= JSON.parseObject(data,Result.class);
              if (CommonUtil.notNull(result)&& Dict.RESULT.equals(result.result)){
                  registerView.msgShow("短信验证码已发送...");
              }else {
                  registerView.msgShow("短信验证码发送失败...");
              }
          }

          @Override
          protected void onFailure(HttpException e, Response res) {
              registerView.hideLoading();
              ResponseModel model=Util.handleServerException(e,registerView);
                  if (CommonUtil.notNull(model)){
                      if ("40003".equals(model.statusCode)||"10040".equals(model.statusCode)){
                          registerView.msgShow(model.msg);
                          return;
                      }
              }
              registerView.msgShow("短信验证码发送失败...");
              //registerView.responseFailure(e,res);
          }
      });
  }

   public void doRegister(String tel,String nickname,String code,String  pwd){
       if (!CommonUtil.isMobileNO(tel)){
            registerView.msgShow("手机格式错误");
           return;
       }
       if (CommonUtil.isEmpty(nickname)){
           registerView.msgShow("昵称不能为空");
           return;
       }
       if (!CommonUtil.checkName(nickname)){
           registerView.msgShow("昵称格式错误");
           return;
       }
       if (CommonUtil.isEmpty(pwd)){
           registerView.msgShow("密码不能为空");
           return;
       }
       if (pwd.length()<6||pwd.length()>16){
           registerView.msgShow("密码长度需符合6位至16位");
           return;
       }
       registerView.showLoading();
       MemberParam param=new MemberParam();
       param.setSmsCode(code);
       param.setDeviceId(UUID.randomUUID().toString());
       param.setTelphone(tel);
       param.setPassword(pwd);
       param.setNickName(nickname);
       registerView.request(RequestUtil.requestRegister(param),new HttpModelHandler<String>() {
           @Override
           protected void onSuccess(String data, Response res) {
                if (!CommonUtil.isEmpty(data)){
                    User user=JSON.parseObject(data,User.class);
                    if (CommonUtil.notNull(user)){
                        service.putString(Dict.MEMBER_ID,user.getUserId());
                        registerView.msgShow("注册成功");
                        registerView.toMainActivity();
                    }else {
                        registerView.msgShow("注册失败");
                    }
                }
               registerView.hideLoading();
           }

           @Override
           protected void onFailure(HttpException e, Response res) {
               ResponseModel model=Util.handleServerException(e,registerView);
               registerView.hideLoading();
               if (CommonUtil.notNull(model)){
                   if ("40003".equals(model.statusCode)||"10040".equals(model.statusCode)){
                       registerView.msgShow(model.msg);
                       return;
                   }
               }
               registerView.msgShow("注册失败");
               //registerView.responseFailure(e,res);
           }
       });
   }
}
