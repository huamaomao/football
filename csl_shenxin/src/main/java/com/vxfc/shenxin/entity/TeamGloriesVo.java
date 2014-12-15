package com.vxfc.shenxin.entity;

import java.io.Serializable;

public class TeamGloriesVo implements Serializable {
	private static final long serialVersionUID = 5873944316579735676L;
	private String teamId;
	private String glories;
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getGlories() {
		return glories;
	}
	public void setGlories(String glories) {
		this.glories = glories;
	}
	
}	
