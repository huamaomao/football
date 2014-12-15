package com.vxfc.shenxin.entity;

import java.io.Serializable;
public class TeamVo implements Serializable {
	private static final long serialVersionUID = -1547592830542828738L;
	private String id;
	private String cname;
	private String ename;
	private String region;
	private String regTime;
	private String stadium;
	private String galleryful;
	private String owner;
	private String coach;
	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getGalleryful() {
		return galleryful;
	}

	public void setGalleryful(String galleryful) {
		this.galleryful = galleryful;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	
}
