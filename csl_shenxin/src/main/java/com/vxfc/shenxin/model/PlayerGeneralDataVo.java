package com.vxfc.shenxin.model;

import java.io.Serializable;
public class PlayerGeneralDataVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String isFirst;
	private String round;
	private String totalContest;
	private String totalCross;
	private String minsPlayed;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getTotalContest() {
		return totalContest;
	}
	public void setTotalContest(String totalContest) {
		this.totalContest = totalContest;
	}
	public String getTotalCross() {
		return totalCross;
	}
	public void setTotalCross(String totalCross) {
		this.totalCross = totalCross;
	}
	public String getMinsPlayed() {
		return minsPlayed;
	}
	public void setMinsPlayed(String minsPlayed) {
		this.minsPlayed = minsPlayed;
	}
	
}
