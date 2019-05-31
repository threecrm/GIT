package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class Message {

	private Integer Mid;
	
	private Integer Sid;
	private Integer AskId;
	private String Sname;
	private String Mtime;
	public Integer getMid() {
		return Mid;
	}
	public void setMid(Integer mid) {
		Mid = mid;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public Integer getAskId() {
		return AskId;
	}
	public void setAskId(Integer askId) {
		AskId = askId;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getMtime() {
		return Mtime;
	}
	public void setMtime(String mtime) {
		Mtime = mtime;
	}
	@Override
	public String toString() {
		return "Message [Mid=" + Mid + ", Sid=" + Sid + ", AskId=" + AskId
				+ ", Sname=" + Sname + ", Mtime=" + Mtime + "]";
	}
	
	
}
