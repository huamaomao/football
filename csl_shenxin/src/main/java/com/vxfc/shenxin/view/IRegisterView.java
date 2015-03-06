package com.vxfc.shenxin.view;

public interface IRegisterView extends IRequestView{
    void errorRegiter(String msg);
    void toMainActivity();
    void setTelRemarks(String remarks);
    void setRegisterStatus();
}
