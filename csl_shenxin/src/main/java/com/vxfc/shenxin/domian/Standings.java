package com.vxfc.shenxin.domian;

/**
 * 积分榜
 * Created by Hua on 2014/8/2.
 */
public class Standings {
    private String  no;//名次
    private String  session;//场次
    private String win;//胜场次
    private String draw;//平场次
    private String lose;//输场次
    private String jsq;//净胜球
    private String score;//积分
    private  String teamName;//球队
    private String teamId;

    @Override
    public String toString() {
        return "Standings{" +
                "no='" + no + '\'' +
                ", session='" + session + '\'' +
                ", win='" + win + '\'' +
                ", draw='" + draw + '\'' +
                ", lose='" + lose + '\'' +
                ", jsq='" + jsq + '\'' +
                ", score='" + score + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getJsq() {
        return jsq;
    }

    public void setJsq(String jsq) {
        this.jsq = jsq;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
