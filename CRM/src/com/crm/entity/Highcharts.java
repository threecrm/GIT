package com.crm.entity;

public class Highcharts {
	//����ǩ������
	private Integer zc;
	//���˴���
	private Integer zt;
	//�ٵ�����
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
