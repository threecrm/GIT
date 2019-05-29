package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.service.GenZongService;

@Controller
public class GenZongController {
	@Autowired
	private GenZongService genZongService;
	@Autowired
	private Fenye<Network_trace> fenye;
	
	@RequestMapping(value="/SeleteAllNetwork_trace",method=RequestMethod.POST)
	@ResponseBody
	public Fenye<Network_trace> selectGenZong(Network_trace network_trace ,Integer page,Integer rows) {
		fenye.setPage((page-1)*rows);
		fenye.setPageSize(rows);
		fenye.setNetwork_trace(network_trace);
		fenye=genZongService.selectGenZong(fenye);
		
		return fenye;
	}
	@RequestMapping(value="/addgenzong",method=RequestMethod.POST)
	@ResponseBody
	public Integer Addgenzong(Network_trace network_trace) {
		Integer i = genZongService.Addgenzong(network_trace);
		return i;
	}

}
