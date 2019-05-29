package com.crm.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Counselor {

	private Integer c_id;
	private String c_name;
	private String c_sex;
	private Integer c_age;
	private String c_phone;
	private String c_address;
	private Date c_entryTime;
	private String c_state;
	private Integer c_studentCount;
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_sex() {
		return c_sex;
	}
	public void setC_sex(String c_sex) {
		this.c_sex = c_sex;
	}
	public Integer getC_age() {
		return c_age;
	}
	public void setC_age(Integer c_age) {
		this.c_age = c_age;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public Date getC_entryTime() {
		return c_entryTime;
	}
	public void setC_entryTime(Date c_entryTime) {
		this.c_entryTime = c_entryTime;
	}
	public String getC_state() {
		return c_state;
	}
	public void setC_state(String c_state) {
		this.c_state = c_state;
	}
	public Integer getC_studentCount() {
		return c_studentCount;
	}
	public void setC_studentCount(Integer c_studentCount) {
		this.c_studentCount = c_studentCount;
	}
	@Override
	public String toString() {
		return "Counselor [c_id=" + c_id + ", c_name=" + c_name + ", c_sex="
				+ c_sex + ", c_age=" + c_age + ", c_phone=" + c_phone
				+ ", c_address=" + c_address + ", c_entryTime=" + c_entryTime
				+ ", c_state=" + c_state + ", c_studentCount=" + c_studentCount
				+ "]";
	}
	
	
}
