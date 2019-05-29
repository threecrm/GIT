package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.HighchartsMapper;
import com.crm.entity.Highcharts;
@Service
public class HighchartsServiceImp implements HighchartsService{
	@Autowired
	private HighchartsMapper highchartsMapper;
	public Highcharts selectHighcharts(Integer uid) {
		Highcharts highcharts = new Highcharts();
		Integer selectHighchartsZC = highchartsMapper.selectHighchartsZC(uid);
		Integer selectHighchartsCD = highchartsMapper.selectHighchartsCD(uid);
		Integer selectHighchartsZT = highchartsMapper.selectHighchartsZT(uid);
		highcharts.setZc(selectHighchartsZC);
		highcharts.setCd(selectHighchartsCD);
		highcharts.setZt(selectHighchartsZT);
		return highcharts;
	}

}
