package com.crm.dao;

public interface HighchartsMapper {
	//查詢正常签到次数
	Integer selectHighchartsZC(Integer uid);
	//早退次数
	Integer selectHighchartsZT(Integer uid);
	//迟到次数
	Integer selectHighchartsCD(Integer uid);
}
