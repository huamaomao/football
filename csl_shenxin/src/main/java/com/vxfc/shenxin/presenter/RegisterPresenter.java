package com.vxfc.shenxin.presenter;

import com.alibaba.fastjson.JSON;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.util.CommonUtil;
import com.vxfc.common.util.Log;
import com.vxfc.shenxin.domian.Result;
import com.vxfc.shenxin.domian.param.MemberParam;
import com.vxfc.shenxin.util.Dict;
import com.vxfc.shenxin.util.RequestUtil;
import com.vxfc.shenxin.view.IRegisterView;

/**
 * Created by Hua_ on 2014/12/26.
 */
public class RegisterPresenter {
    private IRegisterView registerView;
    public RegisterPresenter(IRegisterView registerView){
        this.registerView=registerView;
    }
   public void  doRegisterFirst(String tel){
       MemberParam param=new MemberParam();
       param.setTelphone(tel);
      registerView.request(RequestUtil.requestSms(param),new HttpModelHandler<String>(){
          @Override
          protected void onSuccess(String data, Response res) {
              Log.i(data);
              Result result= JSON.parseObject(data,Result.class);
              if (CommonUtil.notNull(result)&& Dict.RESULT.equals(result.result)){
                  registerView.msgShow("短信验证码已发送...");
              }else {
                  registerView.msgShow("短信验证码发送失败...");
              }

          }

          @Override
          protected void onFailure(HttpException e, Response res) {
              registerView.msgShow("短信验证码发送失败...");
              registerView.responseFailure(e,res);
          }
      });
   }
   public void doRegisterCheck(int code){

   }
}
