package com.crm.entity;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Fenye<T> {
	private Integer page;
	private Integer pageSize;
	private Integer total;
	private List<T> rows;
	private Users users;
	private String beginDates;
	private String endDates;
	
	private String RoleName;
	private Student student;
	private Ask ask;
    private GenZong genZong;
    private Network_trace network_trace;
    
	public Network_trace getNetwork_trace() {
		return network_trace;
	}
	public void setNetwork_trace(Network_trace network_trace) {
		this.network_trace = network_trace;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Ask getAsk() {
		return ask;
	}
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public String getBeginDates() {
		return beginDates;
	}
	public void setBeginDates(String beginDates) {
		this.beginDates = beginDates;
	}
	public String getEndDates() {
		return endDates;
	}
	public void setEndDates(String endDates) {
		this.endDates = endDates;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public GenZong getGenZong() {
		return genZong;
	}
	public void setGenZong(GenZong genZong) {
		this.genZong = genZong;
	}
	@Override
	public String toString() {
		return "Fenye [page=" + page + ", pageSize=" + pageSize + ", total="
				+ total + ", rows=" + rows + ", users=" + users
				+ ", beginDates=" + beginDates + ", endDates=" + endDates
				+ ", RoleName=" + RoleName + ", student=" + student + ", ask="
				+ ask + ", genZong=" + genZong + ", network_trace="
				+ network_trace + "]";
	}
	
	
}
