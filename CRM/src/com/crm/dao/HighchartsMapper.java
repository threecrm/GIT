package com.crm.dao;

import java.util.List;

public interface HighchartsMapper {
	// 查詢当天签到的人数
	Integer selectHighchartsZC(String date);

	// 查询咨询师总数
	Integer selectHighchartsZS();

	// 查询有几个迟到
	Integer selectHighchartsCD(String date);
	
}
