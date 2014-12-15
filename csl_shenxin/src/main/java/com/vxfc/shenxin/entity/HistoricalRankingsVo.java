package com.vxfc.shenxin.entity;

import java.io.Serializable;
public class HistoricalRankingsVo implements Serializable{
	private static final long serialVersionUID = -2753634910019804470L;
	private String playerName;
	private String playerId;
	private String startYear;
	private String goals;
	//场次
	private String number;
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

    @Override
    public String toString() {
        return "HistoricalRankingsVo{" +
                "playerName='" + playerName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", startYear='" + startYear + '\'' +
                ", goals='" + goals + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
