package com.vxfc.shenxin.presenter;


import com.vxfc.shenxin.view.ILoginView;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView){
        this.loginView=loginView;
    }

    /***
     * 登陆
     * @param tel
     * @param pwd
     */
    public void login(String tel,String pwd){

    }

}
