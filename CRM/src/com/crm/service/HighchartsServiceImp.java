package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crm.dao.HighchartsMapper;
import com.crm.entity.Highcharts;
@Service
public class HighchartsServiceImp implements HighchartsService{
	@Autowired
	private HighchartsMapper highchartsMapper;
	public Highcharts selectHighcharts() {
		Highcharts highcharts = new Highcharts();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Integer qiandao = highchartsMapper.selectHighchartsZC(sdf.format(date));
		Integer chidao = highchartsMapper.selectHighchartsCD(sdf.format(date));
		Integer queqin = highchartsMapper.selectHighchartsZS();
		highcharts.setZc(qiandao);
		highcharts.setCd(chidao);
		highcharts.setZt(queqin-(qiandao+chidao));
		return highcharts;
	}
}
