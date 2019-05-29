package com.crm.entity;

public class Highcharts {
	//正常签到次数
	private Integer zc;
	//早退次数
	private Integer zt;
	//迟到次数
	private Integer cd;
	
	public Integer getZc() {
		return zc;
	}
	public void setZc(Integer zc) {
		this.zc = zc;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Integer getCd() {
		return cd;
	}
	public void setCd(Integer cd) {
		this.cd = cd;
	}
	@Override
	public String toString() {
		return "Highcharts [zc=" + zc + ", zt=" + zt + ", cd=" + cd + "]";
	}
}
