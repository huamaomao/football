package com.vxfc.shenxin.entity;

import java.io.Serializable;
/***
 * 
* @ClassName: TeamDataVo 
* @Description: 
* @author Hua
* @date 2014-9-1 下午5:05:03 
*
 */
public class TeamDataVo implements Serializable{
	private static final long serialVersionUID = 741989508067869716L;
	private String teamDataA;
	private String teamDataB;
	private String  dataName;
	public String getTeamDataA() {
		return teamDataA;
	}
	public void setTeamDataA(Object teamDataA) {
		this.teamDataA = String.valueOf(teamDataA);
	}
	public String getTeamDataB() {
		return teamDataB;
	}
	public void setTeamDataB(Object teamDataB) {
		this.teamDataB = String.valueOf(teamDataB);
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
}
