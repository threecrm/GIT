package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.service.NetGenzongService;

@Controller
public class NetGenzongController {
	@Autowired
	private NetGenzongService netGenzongService;
	@Autowired
	private Fenye<Network_trace> fenye;
	
	//分页显示所有
	@RequestMapping(value="/selectNetGenZong",method=RequestMethod.POST)
	@ResponseBody
	public Fenye<Network_trace> selectNetGenZong(Integer page,Integer rows,Network_trace network_trace){
		fenye.setPage((page-1)*rows);
		fenye.setPageSize(rows);
		fenye.setNetwork_trace(network_trace);
		Fenye<Network_trace> selectNetGenZong = netGenzongService.selectNetGenZong(fenye);
		return fenye;
		
	}
	
	  //添加网络跟踪
	@RequestMapping(value="/addNetGenzong",method=RequestMethod.POST)
	@ResponseBody
    public Integer addNetGenzong(Network_trace network_trace) {
		return netGenzongService.addNetGenzong(network_trace);
    	
    }

}
