package com.crm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Highcharts;
import com.crm.service.HighchartsService;

@Controller
public class HighchartsController {
	@Autowired
	private HighchartsService highchartsService;
	@RequestMapping(value = "/getHigcharts", method = RequestMethod.POST)
	@ResponseBody
	public Highcharts getHigcharts(){
		Highcharts selectHighcharts = highchartsService.selectHighcharts();
		return selectHighcharts;
	}
}
