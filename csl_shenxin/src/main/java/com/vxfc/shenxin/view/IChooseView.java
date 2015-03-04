package com.vxfc.shenxin.view;

import com.vxfc.common.view.IMessageView;

public interface IChooseView extends IMessageView,IRequestView{
    void toLoginActivity();
    void toRegisterActivity();
}
