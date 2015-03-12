package com.vxfc.shenxin.domian;

/**
 * Created by Administrator on 2015/3/10 0010.
 */
public class WeixinInfo {
    public String appid;
    public String nickname;
    public String headimgurl;
    public String openid;

    @Override
    public String toString() {
        return "WeixinInfo{" +
                "appid='" + appid + '\'' +
                ", niceName='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
