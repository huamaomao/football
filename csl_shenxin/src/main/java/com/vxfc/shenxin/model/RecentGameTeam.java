package com.vxfc.shenxin.model;

import java.io.Serializable;

/**
 * 对战历史
 * Created by Hua on 2014/8/2.
 */
public class RecentGameTeam implements Serializable{
    private static final long serialVersionUID = -6922998295780657609L;
    private String id;
    private String events = "中超";//赛事
    private String season;//赛季
    private String round;//轮次
    private String date;//开赛日期
    private String teamAId;//主队id
    private String teamAName;//主队名称
    private String teamBId;//客队id
    private String teamBName;//客队名称
    private String score;//比分
    private String status;//N（未开赛）|F（完赛）|I（正在进行中）
    private String gameTime;//当status为I是才会有值，比赛进行的有效时间
    private String result;//比赛结果，A（主队胜）| B（客队胜）| C（平）
    private String res;//比赛结果，A（主队胜）| B（客队胜）| C（平）
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(String teamAId) {
        this.teamAId = teamAId;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(String teamBId) {
        this.teamBId = teamBId;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "RecentGameTeam{" +
                "id='" + id + '\'' +
                ", events='" + events + '\'' +
                ", season='" + season + '\'' +
                ", round='" + round + '\'' +
                ", date='" + date + '\'' +
                ", teamAId='" + teamAId + '\'' +
                ", teamAName='" + teamAName + '\'' +
                ", teamBId='" + teamBId + '\'' +
                ", teamBName='" + teamBName + '\'' +
                ", score='" + score + '\'' +
                ", status='" + status + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", result='" + result + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
