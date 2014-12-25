package com.vxfc.shenxin.view;

import com.vxfc.common.view.IView;

public interface ILoginView extends IView {
    void setUsernameError();

    void setPasswordError();

    void toRegisterActivity();
}