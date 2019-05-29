package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class Ask {
	private Integer askId;
	private String askName;
	private String checkState;
	private String checkInTime;
	private String changeState;
	private Integer weights;
	private String roleNames;
	private String bakContent;
	private String Statue;
	public Ask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ask(Integer askId, String askName, String checkState, String checkInTime, String changeState,
			Integer weights, String roleNames, String bakContent, String statue) {
		super();
		this.askId = askId;
		this.askName = askName;
		this.checkState = checkState;
		this.checkInTime = checkInTime;
		this.changeState = changeState;
		this.weights = weights;
		this.roleNames = roleNames;
		this.bakContent = bakContent;
		Statue = statue;
	}
	public Integer getAskId() {
		return askId;
	}
	public void setAskId(Integer askId) {
		this.askId = askId;
	}
	public String getAskName() {
		return askName;
	}
	public void setAskName(String askName) {
		this.askName = askName;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}
	public Integer getWeights() {
		return weights;
	}
	public void setWeights(Integer weights) {
		this.weights = weights;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getBakContent() {
		return bakContent;
	}
	public void setBakContent(String bakContent) {
		this.bakContent = bakContent;
	}
	public String getStatue() {
		return Statue;
	}
	public void setStatue(String statue) {
		Statue = statue;
	}
	@Override
	public String toString() {
		return "Ask [askId=" + askId + ", askName=" + askName + ", checkState=" + checkState + ", checkInTime="
				+ checkInTime + ", changeState=" + changeState + ", weights=" + weights + ", roleNames=" + roleNames
				+ ", bakContent=" + bakContent + ", Statue=" + Statue + "]";
	}
	
	
}
