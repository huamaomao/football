package com.vxfc.shenxin.presenter;

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
      // registerView.request(RequestUtil.);
   }
   public void doRegisterCheck(int code){

   }
}
