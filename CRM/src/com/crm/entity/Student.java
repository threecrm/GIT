package com.crm.entity;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private Integer sid;
	private String Sname;
	private Integer Age;
	private String Sex;
	private String Phone;
	private String StuStatus;
	private String PerStatus;
	private String MsgSource;
	private String SourceUrl;
	private String SourceKeyWord;
	private String Address;
	private Integer NetPusherId;
	private Integer AskerId;
	private String QQ;
	private String WeiXin;
	private String Content;
	private String CreatTimes;
	private String LearnForword;
	private String IsValid;
	private String Reoord;
	private String IsReturnVisit;
	private String FirstVisitTime;
	private String isHome;
	private String homeTime;
	private String LostValid;
	private String IsPay;
	private String PayTime;
	private Double Money;
	private String isReturnMoney;
	private String isInClass;
	private String inClassTime;
	private String inClassContent;
	private String AskerContent;
	private String isDel;
	private String FromPart;
	private String stuConcern;
	private String isBaoBei;
	private String ZiXunName;
	private String CreatUser;
	private String ReturnMoneyReason;
	private Double PreMoney;
	private String PreMoneyTime;
	
	private String AskName;
	private String RoleNames;
	private String StartcreatTimes;
	private String EndcreatTimes;
	private String StarthomeTime;
	private String EndhomeTime;
	private String StartfirstVisitTime;
	private String EndfirstVisitTime;
	private String StartpayTime;
	private String EndpayTime;
	private String StartinClassTime;
	private String EndinClassTime;
	private Integer u_id;
	
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getStuStatus() {
		return StuStatus;
	}
	public void setStuStatus(String stuStatus) {
		StuStatus = stuStatus;
	}
	public String getPerStatus() {
		return PerStatus;
	}
	public void setPerStatus(String perStatus) {
		PerStatus = perStatus;
	}
	public String getMsgSource() {
		return MsgSource;
	}
	public void setMsgSource(String msgSource) {
		MsgSource = msgSource;
	}
	public String getSourceUrl() {
		return SourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		SourceUrl = sourceUrl;
	}
	public String getSourceKeyWord() {
		return SourceKeyWord;
	}
	public void setSourceKeyWord(String sourceKeyWord) {
		SourceKeyWord = sourceKeyWord;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Integer getNetPusherId() {
		return NetPusherId;
	}
	public void setNetPusherId(Integer netPusherId) {
		NetPusherId = netPusherId;
	}
	public Integer getAskerId() {
		return AskerId;
	}
	public void setAskerId(Integer askerId) {
		AskerId = askerId;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWeiXin() {
		return WeiXin;
	}
	public void setWeiXin(String weiXin) {
		WeiXin = weiXin;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getCreatTimes() {
		return CreatTimes;
	}
	public void setCreatTimes(String creatTimes) {
		CreatTimes = creatTimes;
	}
	public String getLearnForword() {
		return LearnForword;
	}
	public void setLearnForword(String learnForword) {
		LearnForword = learnForword;
	}
	public String getIsValid() {
		return IsValid;
	}
	public void setIsValid(String isValid) {
		IsValid = isValid;
	}
	public String getReoord() {
		return Reoord;
	}
	public void setReoord(String reoord) {
		Reoord = reoord;
	}
	public String getIsReturnVisit() {
		return IsReturnVisit;
	}
	public void setIsReturnVisit(String isReturnVisit) {
		IsReturnVisit = isReturnVisit;
	}
	public String getFirstVisitTime() {
		return FirstVisitTime;
	}
	public void setFirstVisitTime(String firstVisitTime) {
		FirstVisitTime = firstVisitTime;
	}
	public String getIsHome() {
		return isHome;
	}
	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}
	public String getHomeTime() {
		return homeTime;
	}
	public void setHomeTime(String homeTime) {
		this.homeTime = homeTime;
	}
	public String getLostValid() {
		return LostValid;
	}
	public void setLostValid(String lostValid) {
		LostValid = lostValid;
	}
	public String getIsPay() {
		return IsPay;
	}
	public void setIsPay(String isPay) {
		IsPay = isPay;
	}
	public String getPayTime() {
		return PayTime;
	}
	public void setPayTime(String payTime) {
		PayTime = payTime;
	}
	public Double getMoney() {
		return Money;
	}
	public void setMoney(Double money) {
		Money = money;
	}
	public String getIsReturnMoney() {
		return isReturnMoney;
	}
	public void setIsReturnMoney(String isReturnMoney) {
		this.isReturnMoney = isReturnMoney;
	}
	public String getIsInClass() {
		return isInClass;
	}
	public void setIsInClass(String isInClass) {
		this.isInClass = isInClass;
	}
	public String getInClassTime() {
		return inClassTime;
	}
	public void setInClassTime(String inClassTime) {
		this.inClassTime = inClassTime;
	}
	public String getInClassContent() {
		return inClassContent;
	}
	public void setInClassContent(String inClassContent) {
		this.inClassContent = inClassContent;
	}
	public String getAskerContent() {
		return AskerContent;
	}
	public void setAskerContent(String askerContent) {
		AskerContent = askerContent;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getFromPart() {
		return FromPart;
	}
	public void setFromPart(String fromPart) {
		FromPart = fromPart;
	}
	public String getStuConcern() {
		return stuConcern;
	}
	public void setStuConcern(String stuConcern) {
		this.stuConcern = stuConcern;
	}
	public String getIsBaoBei() {
		return isBaoBei;
	}
	public void setIsBaoBei(String isBaoBei) {
		this.isBaoBei = isBaoBei;
	}
	public String getZiXunName() {
		return ZiXunName;
	}
	public void setZiXunName(String ziXunName) {
		ZiXunName = ziXunName;
	}
	public String getCreatUser() {
		return CreatUser;
	}
	public void setCreatUser(String creatUser) {
		CreatUser = creatUser;
	}
	public String getReturnMoneyReason() {
		return ReturnMoneyReason;
	}
	public void setReturnMoneyReason(String returnMoneyReason) {
		ReturnMoneyReason = returnMoneyReason;
	}
	public Double getPreMoney() {
		return PreMoney;
	}
	public void setPreMoney(Double preMoney) {
		PreMoney = preMoney;
	}
	public String getPreMoneyTime() {
		return PreMoneyTime;
	}
	public void setPreMoneyTime(String preMoneyTime) {
		PreMoneyTime = preMoneyTime;
	}
	public String getAskName() {
		return AskName;
	}
	public void setAskName(String askName) {
		AskName = askName;
	}
	public String getRoleNames() {
		return RoleNames;
	}
	public void setRoleNames(String roleNames) {
		RoleNames = roleNames;
	}
	public String getStartcreatTimes() {
		return StartcreatTimes;
	}
	public void setStartcreatTimes(String startcreatTimes) {
		StartcreatTimes = startcreatTimes;
	}
	public String getEndcreatTimes() {
		return EndcreatTimes;
	}
	public void setEndcreatTimes(String endcreatTimes) {
		EndcreatTimes = endcreatTimes;
	}
	public String getStarthomeTime() {
		return StarthomeTime;
	}
	public void setStarthomeTime(String starthomeTime) {
		StarthomeTime = starthomeTime;
	}
	public String getEndhomeTime() {
		return EndhomeTime;
	}
	public void setEndhomeTime(String endhomeTime) {
		EndhomeTime = endhomeTime;
	}
	public String getStartfirstVisitTime() {
		return StartfirstVisitTime;
	}
	public void setStartfirstVisitTime(String startfirstVisitTime) {
		StartfirstVisitTime = startfirstVisitTime;
	}
	public String getEndfirstVisitTime() {
		return EndfirstVisitTime;
	}
	public void setEndfirstVisitTime(String endfirstVisitTime) {
		EndfirstVisitTime = endfirstVisitTime;
	}
	public String getStartpayTime() {
		return StartpayTime;
	}
	public void setStartpayTime(String startpayTime) {
		StartpayTime = startpayTime;
	}
	public String getEndpayTime() {
		return EndpayTime;
	}
	public void setEndpayTime(String endpayTime) {
		EndpayTime = endpayTime;
	}
	public String getStartinClassTime() {
		return StartinClassTime;
	}
	public void setStartinClassTime(String startinClassTime) {
		StartinClassTime = startinClassTime;
	}
	public String getEndinClassTime() {
		return EndinClassTime;
	}
	public void setEndinClassTime(String endinClassTime) {
		EndinClassTime = endinClassTime;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", Sname=" + Sname + ", Age=" + Age
				+ ", Sex=" + Sex + ", Phone=" + Phone + ", StuStatus="
				+ StuStatus + ", PerStatus=" + PerStatus + ", MsgSource="
				+ MsgSource + ", SourceUrl=" + SourceUrl + ", SourceKeyWord="
				+ SourceKeyWord + ", Address=" + Address + ", NetPusherId="
				+ NetPusherId + ", AskerId=" + AskerId + ", QQ=" + QQ
				+ ", WeiXin=" + WeiXin + ", Content=" + Content
				+ ", CreatTimes=" + CreatTimes + ", LearnForword="
				+ LearnForword + ", IsValid=" + IsValid + ", Reoord=" + Reoord
				+ ", IsReturnVisit=" + IsReturnVisit + ", FirstVisitTime="
				+ FirstVisitTime + ", isHome=" + isHome + ", homeTime="
				+ homeTime + ", LostValid=" + LostValid + ", IsPay=" + IsPay
				+ ", PayTime=" + PayTime + ", Money=" + Money
				+ ", isReturnMoney=" + isReturnMoney + ", isInClass="
				+ isInClass + ", inClassTime=" + inClassTime
				+ ", inClassContent=" + inClassContent + ", AskerContent="
				+ AskerContent + ", isDel=" + isDel + ", FromPart=" + FromPart
				+ ", stuConcern=" + stuConcern + ", isBaoBei=" + isBaoBei
				+ ", ZiXunName=" + ZiXunName + ", CreatUser=" + CreatUser
				+ ", ReturnMoneyReason=" + ReturnMoneyReason + ", PreMoney="
				+ PreMoney + ", PreMoneyTime=" + PreMoneyTime + ", AskName="
				+ AskName + ", RoleNames=" + RoleNames + ", StartcreatTimes="
				+ StartcreatTimes + ", EndcreatTimes=" + EndcreatTimes
				+ ", StarthomeTime=" + StarthomeTime + ", EndhomeTime="
				+ EndhomeTime + ", StartfirstVisitTime=" + StartfirstVisitTime
				+ ", EndfirstVisitTime=" + EndfirstVisitTime
				+ ", StartpayTime=" + StartpayTime + ", EndpayTime="
				+ EndpayTime + ", StartinClassTime=" + StartinClassTime
				+ ", EndinClassTime=" + EndinClassTime + ", u_id=" + u_id + "]";
	}
	
}
