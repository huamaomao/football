package com.vxfc.shenxin.entity;

import java.io.Serializable;
public class PlayerAttackDataVo implements Serializable{
	private static final long serialVersionUID = 8534332547885094791L;
	private String id;
	private String totalScoringAtt;
	private String round;
	private String totalOffside;
	private String goals;
	private String goalAssist;
	public String getId() {
		return id;
	}

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalScoringAtt() {
        return totalScoringAtt;
    }

    public void setTotalScoringAtt(String totalScoringAtt) {
        this.totalScoringAtt = totalScoringAtt;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getTotalOffside() {
        return totalOffside;
    }

    public void setTotalOffside(String totalOffside) {
        this.totalOffside = totalOffside;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoalAssist() {
        return goalAssist;
    }

    public void setGoalAssist(String goalAssist) {
        this.goalAssist = goalAssist;
    }
}
