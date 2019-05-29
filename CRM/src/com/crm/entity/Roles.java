package com.crm.entity;


import org.springframework.stereotype.Component;
@Component
public class Roles {
	private Integer rid;
	private String RoleName;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	@Override
	public String toString() {
		return "Roles [rid=" + rid + ", RoleName=" + RoleName + "]";
	}
	
	
	
}
