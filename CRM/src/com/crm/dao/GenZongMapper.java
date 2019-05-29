package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;


public interface GenZongMapper {
   //分页显示所有
	List<Network_trace> selectGenZong(Fenye<Network_trace> fenye);
	//查询数量
	Integer count(Fenye<Network_trace> fenye);
	
	
	
    //跟踪
    Integer Addgenzong(Network_trace network_trace);
    
}
