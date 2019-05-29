package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class Sign_in {
	private Integer sid;
	private Integer uid;
	private String LoginUserName;
	
	private String creat_time;
	private String end_time;
	private String state;
	private String endState;
	
	public String getLoginUserName() {
		return LoginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		LoginUserName = loginUserName;
	}
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Sign_in [sid=" + sid + ", uid=" + uid + ", LoginUserName="
				+ LoginUserName + ", creat_time=" + creat_time + ", end_time="
				+ end_time + ", state=" + state + ", endState=" + endState
				+ "]";
	}
	
	

}
