package com.vxfc.shenxin.view;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpModelHandler;
import com.vxfc.common.view.IMessageView;
import com.vxfc.common.view.IView;

/**
 * Created by Hua_ on 2015/3/4.
 */
public interface IRequestView extends IMessageView,IView{
    void request(final Request res,final HttpModelHandler<String> UIHandler);
    void responseFailure(HttpException e, Response res);
}
