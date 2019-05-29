package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class RoleModules {
	private Integer rmid;
	private Integer roleid;
	private Integer moduleid;
	public Integer getRmid() {
		return rmid;
	}
	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getModuleid() {
		return moduleid;
	}
	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}
	@Override
	public String toString() {
		return "RoleModules [rmid=" + rmid + ", roleid=" + roleid
				+ ", moduleid=" + moduleid + "]";
	}
	
	
	
}
