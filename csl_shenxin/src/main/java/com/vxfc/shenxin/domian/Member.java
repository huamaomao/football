package com.vxfc.shenxin.domian;

/**
 * Created by Hua on 2014/8/2.
 */
public class Member {
    private String id;
    private String state;
    private String telphone;
    private String password;
    private String nike;//昵称
    private String level;//级别
    private String honor;//荣誉
    private String favoriteTeam;//喜欢的球队
    private String goldCoins;//金币
    private String tel;//手机号
    private String mail;//邮箱
    private String photo;
    private String qqId;
    private String weixinId;
    private String sinaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public String getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(String goldCoins) {
        this.goldCoins = goldCoins;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getSinaId() {
        return sinaId;
    }

    public void setSinaId(String sinaId) {
        this.sinaId = sinaId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", telphone='" + telphone + '\'' +
                ", password='" + password + '\'' +
                ", nike='" + nike + '\'' +
                ", level='" + level + '\'' +
                ", honor='" + honor + '\'' +
                ", favoriteTeam='" + favoriteTeam + '\'' +
                ", goldCoins='" + goldCoins + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", photo='" + photo + '\'' +
                ", qqId='" + qqId + '\'' +
                ", weixinId='" + weixinId + '\'' +
                ", sinaId='" + sinaId + '\'' +
                '}';
    }
}
