package com.vxfc.shenxin.domian;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO(描述类的职责)
 * @author ningyu
 * @date 2014-8-16 下午2:10:04
 * @since
 * @version 1.0
 */
public class Fixture implements Serializable {

	private static final long serialVersionUID = -1034536038119469093L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 赛季
	 */
	private Integer season;
	/**
	 * 轮次
	 */
	private Integer round;
	/**
	 * 比赛日期
	 */
	private Date date;
	/**
	 * 星期
	 */
	private String week;
	/**
	 * 开赛时间
	 */
	private String time;
	/**
	 * 主队ID
	 */
	private String teamAId;
	/**
	 * 主队队名
	 */
	private String teamAName;
	/**
	 * 客队ID
	 */
	private String teamBId;
	/**
	 * 客队名称
	 */
	private String teamBName;
	/**
	 * 比分
	 */
	private String score;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 球场
	 */
	private String site;
	/**
	 * 开赛状态，N（未开赛）|F（完赛）|I（正在进行中）
	 */
	private String status;
	/**
	 * 比赛结果，A（主队胜）| B（客队胜）| C（平）
	 */
	private String result;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
