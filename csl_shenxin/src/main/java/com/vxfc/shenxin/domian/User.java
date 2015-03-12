package com.vxfc.shenxin.domian;

/**
 * Created by Hua_ on 2015/3/6.
 */
public class User {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
