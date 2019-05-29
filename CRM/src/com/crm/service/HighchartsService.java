package com.crm.service;

import java.util.List;

import com.crm.entity.Highcharts;

public interface HighchartsService {

	// 统计当天员工签到信息
	Highcharts selectHighcharts();
}
