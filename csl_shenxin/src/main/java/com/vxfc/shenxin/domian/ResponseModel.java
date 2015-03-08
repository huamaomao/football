package com.vxfc.shenxin.domian;

/**
 * Created by Hua_ on 2015/2/27.
 */
public class ResponseModel {
    public String code;
    public String statusCode;
    public String msg;

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code='" + code + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
