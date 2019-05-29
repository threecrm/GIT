package com.crm.service;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
public interface GenZongService {
	  //分页显示所有
		public Fenye<Network_trace> selectGenZong(Fenye<Network_trace> fenye);
	 //跟踪
	    public Integer Addgenzong(Network_trace network_trace);

}
