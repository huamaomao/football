package com.vxfc.shenxin.entity;

import java.io.Serializable;
import java.util.Date;

public class FixtureVo implements Serializable {
	private static final long serialVersionUID = -7208785650432818243L;
	private String id;
	private String round;
	private Date date;
	private String teamAId;
	private String teamAName;
	private String teamBId;
	private String teamBName;
	private String score;
	private String status;
	private String result;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}