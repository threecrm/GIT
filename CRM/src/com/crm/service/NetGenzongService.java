package com.crm.service;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;

public interface NetGenzongService {
	//分页显示所有
	public Fenye<Network_trace> selectNetGenZong(Fenye<Network_trace> fenye);
	  //添加网络跟踪
    public Integer addNetGenzong(Network_trace network_trace);
    //查看跟踪日志
    List<Network_trace> selectgenzongrizhi(String name);

}
