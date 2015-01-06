package com.vxfc.shenxin.qq;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.vxfc.common.util.Log;
import com.vxfc.common.view.IMessageView;

/**
 * Created by Hua_ on 2015/1/5.
 */
public abstract class QQIUiListener implements IUiListener {
    private IMessageView messageView;
    public QQIUiListener(IMessageView messageView){
        this.messageView=messageView;
    }

    @Override
    public void onComplete(Object o) {

    }

    @Override
    public void onError(UiError uiError) {
        Log.i(uiError);
    }

    @Override
    public void onCancel() {
        messageView.msgShow("取消授权");
    }


}
