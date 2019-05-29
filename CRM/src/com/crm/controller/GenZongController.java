package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.service.GenZongService;

@Controller
public class GenZongController {
	@Autowired
	private GenZongService genZongService;
	@Autowired
	private Fenye<GenZong> fenye;
	
	@RequestMapping(value="/selectGenZong",method=RequestMethod.POST)
	@ResponseBody
	public Fenye<GenZong> selectGenZong(Integer page,Integer rows,GenZong genZong) {
		fenye.setPage((page-1)*rows);
		fenye.setPageSize(rows);
		fenye.setGenZong(genZong);
		genZongService.selectGenZong(fenye);
		return fenye;
	}
	@RequestMapping(value="/addgenzong",method=RequestMethod.POST)
	@ResponseBody
	public Integer Addgenzong(GenZong genZong) {
		Integer i = genZongService.Addgenzong(genZong);
		return i;
	}

}
