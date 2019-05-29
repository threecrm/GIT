package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class UserRoles {
	private Integer urid;
	private Integer userid;
	private Integer roleid;
	public Integer getUrid() {
		return urid;
	}
	public void setUrid(Integer urid) {
		this.urid = urid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	@Override
	public String toString() {
		return "UserRoles [urid=" + urid + ", userid=" + userid + ", roleid="
				+ roleid + "]";
	}
	
}
