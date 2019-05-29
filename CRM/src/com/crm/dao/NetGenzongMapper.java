package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;

public interface NetGenzongMapper {
	
	//查询网络跟踪信息
    List<Network_trace> selectNetGenzong(Fenye<Network_trace> fenye);
    //查询网络跟踪数量
    Integer countNetzong(Fenye<Network_trace> fenye);
    //添加网络跟踪
    Integer addNetGenzong(Network_trace network_trace);
  //判断跟踪姓名是否重名 
    List<Network_trace> selectNetName(Network_trace network_trace);

}
