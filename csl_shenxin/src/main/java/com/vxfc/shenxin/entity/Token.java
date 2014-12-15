package com.vxfc.shenxin.entity;

/**
 * Created by Hua on 2014/8/2.
 */
public class Token {
    private String access_token;
    private String client_id;
    private String redirect_uri;
    private String sign;
    private Integer state;
    private String refresh_token;
    private long expires_in;//授权码过期时间

    @Override
    public String toString() {
        return "{" +
                "access_token='" + access_token + '\'' +
                ", client_id='" + client_id + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", sign='" + sign + '\'' +
                ", state=" + state +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
