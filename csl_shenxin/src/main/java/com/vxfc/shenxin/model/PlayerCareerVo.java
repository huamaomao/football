package com.vxfc.shenxin.model;

import java.io.Serializable;
public class PlayerCareerVo implements Serializable{
	private static final long serialVersionUID = 3146980026004076476L;
	private String id;
	private String playerId;
	private String date;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
