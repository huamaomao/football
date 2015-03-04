package com.vxfc.shenxin.view;

import com.litesuits.http.request.Request;
import com.litesuits.http.response.handler.HttpModelHandler;

/**
 * Created by Hua_ on 2015/3/4.
 */
public interface IRequestView {
    void request(final Request res,final HttpModelHandler<String> UIHandler);
}
