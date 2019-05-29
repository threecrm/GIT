package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class GenZong {
	 private Integer cid;
	 private int StudentId;
	 private String StudentName;
	 private String FollowTime;
	 private String NextFollowTime;
	 private String Contents;
	 private Integer UserId;
	 private String FollowType;
	 private String CreatsTime;
	 private String FollowState;
	 
	 private Users users;
	 private Student student;	
	 private String StartfollowTime;
	 private String endfollowTime;
	 private String StartnextFollowTime;
	 private String endnextFollowTime;
	public GenZong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenZong(Integer cid, int studentId, String studentName, String followTime, String nextFollowTime,
			String contents, Integer userId, String followType, String creatsTime, String followState, Users users,
			Student student, String startfollowTime, String endfollowTime, String startnextFollowTime,
			String endnextFollowTime) {
		super();
		this.cid = cid;
		StudentId = studentId;
		StudentName = studentName;
		FollowTime = followTime;
		NextFollowTime = nextFollowTime;
		Contents = contents;
		UserId = userId;
		FollowType = followType;
		CreatsTime = creatsTime;
		FollowState = followState;
		this.users = users;
		this.student = student;
		StartfollowTime = startfollowTime;
		this.endfollowTime = endfollowTime;
		StartnextFollowTime = startnextFollowTime;
		this.endnextFollowTime = endnextFollowTime;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getFollowTime() {
		return FollowTime;
	}
	public void setFollowTime(String followTime) {
		FollowTime = followTime;
	}
	public String getNextFollowTime() {
		return NextFollowTime;
	}
	public void setNextFollowTime(String nextFollowTime) {
		NextFollowTime = nextFollowTime;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getFollowType() {
		return FollowType;
	}
	public void setFollowType(String followType) {
		FollowType = followType;
	}
	public String getCreatsTime() {
		return CreatsTime;
	}
	public void setCreatsTime(String creatsTime) {
		CreatsTime = creatsTime;
	}
	public String getFollowState() {
		return FollowState;
	}
	public void setFollowState(String followState) {
		FollowState = followState;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getStartfollowTime() {
		return StartfollowTime;
	}
	public void setStartfollowTime(String startfollowTime) {
		StartfollowTime = startfollowTime;
	}
	public String getEndfollowTime() {
		return endfollowTime;
	}
	public void setEndfollowTime(String endfollowTime) {
		this.endfollowTime = endfollowTime;
	}
	public String getStartnextFollowTime() {
		return StartnextFollowTime;
	}
	public void setStartnextFollowTime(String startnextFollowTime) {
		StartnextFollowTime = startnextFollowTime;
	}
	public String getEndnextFollowTime() {
		return endnextFollowTime;
	}
	public void setEndnextFollowTime(String endnextFollowTime) {
		this.endnextFollowTime = endnextFollowTime;
	}
	@Override
	public String toString() {
		return "GenZong [cid=" + cid + ", StudentId=" + StudentId + ", StudentName=" + StudentName + ", FollowTime="
				+ FollowTime + ", NextFollowTime=" + NextFollowTime + ", Contents=" + Contents + ", UserId=" + UserId
				+ ", FollowType=" + FollowType + ", CreatsTime=" + CreatsTime + ", FollowState=" + FollowState
				+ ", users=" + users + ", student=" + student + ", StartfollowTime=" + StartfollowTime
				+ ", endfollowTime=" + endfollowTime + ", StartnextFollowTime=" + StartnextFollowTime
				+ ", endnextFollowTime=" + endnextFollowTime + "]";
	}
	
 

}
