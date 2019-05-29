package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class Users {
	private Integer uid;
	private String LonginName;
	private String PassWord;
	private String IsLockout;
	private String LastLoginTime;
	private String CreateTime;
	private Integer PsdWrongTime;
	private String LockTime;
	private String ProtectEMail;
	private String ProtectMTel;
	private Roles roles;
	private UserRoles userRoles;
	private Sign_in sign_in;
	
	public Sign_in getSign_in() {
		return sign_in;
	}
	public void setSign_in(Sign_in sign_in) {
		this.sign_in = sign_in;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public UserRoles getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getLonginName() {
		return LonginName;
	}
	public void setLonginName(String longinName) {
		LonginName = longinName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getIsLockout() {
		return IsLockout;
	}
	public void setIsLockout(String isLockout) {
		IsLockout = isLockout;
	}
	public String getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public Integer getPsdWrongTime() {
		return PsdWrongTime;
	}
	public void setPsdWrongTime(Integer psdWrongTime) {
		PsdWrongTime = psdWrongTime;
	}
	public String getLockTime() {
		return LockTime;
	}
	public void setLockTime(String lockTime) {
		LockTime = lockTime;
	}
	public String getProtectEMail() {
		return ProtectEMail;
	}
	public void setProtectEMail(String protectEMail) {
		ProtectEMail = protectEMail;
	}
	public String getProtectMTel() {
		return ProtectMTel;
	}
	public void setProtectMTel(String protectMTel) {
		ProtectMTel = protectMTel;
	}
	
	
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", LonginName=" + LonginName
				+ ", PassWord=" + PassWord + ", IsLockout=" + IsLockout
				+ ", LastLoginTime=" + LastLoginTime + ", CreateTime="
				+ CreateTime + ", PsdWrongTime=" + PsdWrongTime + ", LockTime="
				+ LockTime + ", ProtectEMail=" + ProtectEMail
				+ ", ProtectMTel=" + ProtectMTel + ", roles=" + roles
				+ ", userRoles=" + userRoles + ", sign_in=" + sign_in + "]";
	}
	
}
