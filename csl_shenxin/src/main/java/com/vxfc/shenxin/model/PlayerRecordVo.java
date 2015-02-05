package com.vxfc.shenxin.model;

import java.io.Serializable;

public class PlayerRecordVo implements Serializable{
	private static final long serialVersionUID = -6386763331835099568L;
	private String year;
	
	/***场次***/
	private String number;
	/***出场时间***/
	private String minsPlayed;
	/***助攻***/
	private String goalAssist;
	/*** 触球 ***/
	private String touches;
	/*** 点球 ***/
	private String attPenGoal;
	/*** 传中 ***/
	private String totalCross;
	
	/*** 黄牌 ***/
	private String yellowCard;
	/*** 护球 ***/
	private String shieldBallOop;
	/*** 解围 ***/
	private String totalClearance;
	/*** 红牌 ***/
	private String redCard;
	/*** 犯规 ***/
	private String fouls;
	/*** 抢断 ***/
	private String totalTackle;
	
	
	/*** 射门 ***/
	private String totalScoringAtt;
	/*** 进球  ***/
	private String goals;
	/*** 越位 ***/
	private String totalOffside;
	/*** 过人 ***/
	private String totalContest;
	/*** 直塞球 ***/
	private String totalThroughBall;
	/*** 角球 ***/
	private String cornerTaken;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMinsPlayed() {
		return minsPlayed;
	}
	public void setMinsPlayed(String minsPlayed) {
		this.minsPlayed = minsPlayed;
	}
	public String getGoalAssist() {
		return goalAssist;
	}
	public void setGoalAssist(String goalAssist) {
		this.goalAssist = goalAssist;
	}
	public String getTouches() {
		return touches;
	}
	public void setTouches(String touches) {
		this.touches = touches;
	}
	public String getAttPenGoal() {
		return attPenGoal;
	}
	public void setAttPenGoal(String attPenGoal) {
		this.attPenGoal = attPenGoal;
	}
	public String getTotalCross() {
		return totalCross;
	}
	public void setTotalCross(String totalCross) {
		this.totalCross = totalCross;
	}
	public String getYellowCard() {
		return yellowCard;
	}
	public void setYellowCard(String yellowCard) {
		this.yellowCard = yellowCard;
	}
	public String getShieldBallOop() {
		return shieldBallOop;
	}
	public void setShieldBallOop(String shieldBallOop) {
		this.shieldBallOop = shieldBallOop;
	}
	public String getTotalClearance() {
		return totalClearance;
	}
	public void setTotalClearance(String totalClearance) {
		this.totalClearance = totalClearance;
	}
	public String getRedCard() {
		return redCard;
	}
	public void setRedCard(String redCard) {
		this.redCard = redCard;
	}
	public String getFouls() {
		return fouls;
	}
	public void setFouls(String fouls) {
		this.fouls = fouls;
	}
	public String getTotalTackle() {
		return totalTackle;
	}
	public void setTotalTackle(String totalTackle) {
		this.totalTackle = totalTackle;
	}
	public String getTotalScoringAtt() {
		return totalScoringAtt;
	}
	public void setTotalScoringAtt(String totalScoringAtt) {
		this.totalScoringAtt = totalScoringAtt;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getTotalOffside() {
		return totalOffside;
	}
	public void setTotalOffside(String totalOffside) {
		this.totalOffside = totalOffside;
	}
	public String getTotalContest() {
		return totalContest;
	}
	public void setTotalContest(String totalContest) {
		this.totalContest = totalContest;
	}
	public String getTotalThroughBall() {
		return totalThroughBall;
	}
	public void setTotalThroughBall(String totalThroughBall) {
		this.totalThroughBall = totalThroughBall;
	}
	public String getCornerTaken() {
		return cornerTaken;
	}
	public void setCornerTaken(String cornerTaken) {
		this.cornerTaken = cornerTaken;
	}
	
	
	
}
