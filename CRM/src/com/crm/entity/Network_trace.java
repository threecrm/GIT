package com.crm.entity;


import org.springframework.stereotype.Component;
@Component
public class Network_trace {

	private Integer n_id;
	private String n_sname;
	private String uname;
	private String  n_date;
	private String n_ask;
	private String  n_aftertime;
	private Integer uid;
	
	private String n_qingkuang;
	private String n_fangshi;
	
	private String searchn_sname;
	private String searchn_address;
	private String searchn_qingkuang;
	private String searchn_fangshi;
	
	private String searchstartTime;
	private String searchendTime;
	
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getN_qingkuang() {
		return n_qingkuang;
	}
	public void setN_qingkuang(String n_qingkuang) {
		this.n_qingkuang = n_qingkuang;
	}
	public String getN_fangshi() {
		return n_fangshi;
	}
	public void setN_fangshi(String n_fangshi) {
		this.n_fangshi = n_fangshi;
	}
	public String getSearchn_sname() {
		return searchn_sname;
	}
	public void setSearchn_sname(String searchn_sname) {
		this.searchn_sname = searchn_sname;
	}
	public String getSearchn_address() {
		return searchn_address;
	}
	public void setSearchn_address(String searchn_address) {
		this.searchn_address = searchn_address;
	}
	public String getSearchn_qingkuang() {
		return searchn_qingkuang;
	}
	public void setSearchn_qingkuang(String searchn_qingkuang) {
		this.searchn_qingkuang = searchn_qingkuang;
	}
	public String getSearchn_fangshi() {
		return searchn_fangshi;
	}
	public void setSearchn_fangshi(String searchn_fangshi) {
		this.searchn_fangshi = searchn_fangshi;
	}
	public String getSearchstartTime() {
		return searchstartTime;
	}
	public void setSearchstartTime(String searchstartTime) {
		this.searchstartTime = searchstartTime;
	}
	public String getSearchendTime() {
		return searchendTime;
	}
	public void setSearchendTime(String searchendTime) {
		this.searchendTime = searchendTime;
	}
	public Integer getN_id() {
		return n_id;
	}
	public void setN_id(Integer n_id) {
		this.n_id = n_id;
	}
	public String getN_sname() {
		return n_sname;
	}
	public void setN_sname(String n_sname) {
		this.n_sname = n_sname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getN_date() {
		return n_date;
	}
	public void setN_date(String n_date) {
		this.n_date = n_date;
	}
	public String getN_ask() {
		return n_ask;
	}
	public void setN_ask(String n_ask) {
		this.n_ask = n_ask;
	}
	public String getN_aftertime() {
		return n_aftertime;
	}
	public void setN_aftertime(String n_aftertime) {
		this.n_aftertime = n_aftertime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Network_trace [n_id=" + n_id + ", n_sname=" + n_sname
				+ ", uname=" + uname + ", n_date=" + n_date + ", n_ask="
				+ n_ask + ", n_aftertime=" + n_aftertime + ", uid=" + uid
				+ ", n_qingkuang=" + n_qingkuang + ", n_fangshi=" + n_fangshi
				+ ", searchn_sname=" + searchn_sname + ", searchn_address="
				+ searchn_address + ", searchn_qingkuang=" + searchn_qingkuang
				+ ", searchn_fangshi=" + searchn_fangshi + ", searchstartTime="
				+ searchstartTime + ", searchendTime=" + searchendTime
				+ ", sid=" + sid + "]";
	}
	
	
	
}
