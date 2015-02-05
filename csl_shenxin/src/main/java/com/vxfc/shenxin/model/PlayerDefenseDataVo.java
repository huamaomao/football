package com.vxfc.shenxin.model;

import java.io.Serializable;
public class PlayerDefenseDataVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String round;
	private String shieldBallOop;
	private String totalTackle;
    private String yellowCard;
	private String redCard;
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

    public String getShieldBallOop() {
        return shieldBallOop;
    }

    public void setShieldBallOop(String shieldBallOop) {
        this.shieldBallOop = shieldBallOop;
    }

    public String getTotalTackle() {
        return totalTackle;
    }

    public void setTotalTackle(String totalTackle) {
        this.totalTackle = totalTackle;
    }

    public String getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(String yellowCard) {
        this.yellowCard = yellowCard;
    }

    public String getRedCard() {
        return redCard;
    }

    public void setRedCard(String redCard) {
        this.redCard = redCard;
    }
}
