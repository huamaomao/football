package com.vxfc.shenxin.domian;

import java.io.Serializable;
public class PlayerRankingVo implements Serializable{
	private static final long serialVersionUID = -3323602441229343682L;
	private String playerId;
	private String playerName;
	private String totalScoringAtt;
	private String attHdTotal;
	private String goals;
	private String attPenGoal;
	private String ownGoals;
	private String redCard;
	private String yellowCard;
	private String totalClearance;
	private String fouls;
	private String wasFouled;
	private String divingSave;
    private String totalTackle;
    private String totalCross;
    private String goalAssist;
    private String totalPass;

    public String getTotalTackle() {
        return totalTackle;
    }

    public void setTotalTackle(String totalTackle) {
        this.totalTackle = totalTackle;
    }

    public String getTotalCross() {
        return totalCross;
    }

    public void setTotalCross(String totalCross) {
        this.totalCross = totalCross;
    }

    public String getGoalAssist() {
        return goalAssist;
    }

    public void setGoalAssist(String goalAssist) {
        this.goalAssist = goalAssist;
    }

    public String getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(String totalPass) {
        this.totalPass = totalPass;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTotalScoringAtt() {
        return totalScoringAtt;
    }

    public void setTotalScoringAtt(String totalScoringAtt) {
        this.totalScoringAtt = totalScoringAtt;
    }

    public String getAttHdTotal() {
        return attHdTotal;
    }

    public void setAttHdTotal(String attHdTotal) {
        this.attHdTotal = attHdTotal;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAttPenGoal() {
        return attPenGoal;
    }

    public void setAttPenGoal(String attPenGoal) {
        this.attPenGoal = attPenGoal;
    }

    public String getOwnGoals() {
        return ownGoals;
    }

    public void setOwnGoals(String ownGoals) {
        this.ownGoals = ownGoals;
    }

    public String getRedCard() {
        return redCard;
    }

    public void setRedCard(String redCard) {
        this.redCard = redCard;
    }

    public String getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(String yellowCard) {
        this.yellowCard = yellowCard;
    }

    public String getTotalClearance() {
        return totalClearance;
    }

    public void setTotalClearance(String totalClearance) {
        this.totalClearance = totalClearance;
    }

    public String getFouls() {
        return fouls;
    }

    public void setFouls(String fouls) {
        this.fouls = fouls;
    }

    public String getWasFouled() {
        return wasFouled;
    }

    public void setWasFouled(String wasFouled) {
        this.wasFouled = wasFouled;
    }

    public String getDivingSave() {
        return divingSave;
    }

    public void setDivingSave(String divingSave) {
        this.divingSave = divingSave;
    }
}
