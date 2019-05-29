package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.NetGenzongMapper;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Network_trace;
@Service
public class NetGenzongServiceImpl implements NetGenzongService{
  @Autowired
	private NetGenzongMapper netGenzongMapper;

//分页显示所有
public Fenye<Network_trace> selectNetGenZong(Fenye<Network_trace> fenye) {
	List<Network_trace> selectNetGenzong = netGenzongMapper.selectNetGenzong(fenye);
	Integer countNetzong = netGenzongMapper.countNetzong(fenye);
	fenye.setRows(selectNetGenzong);
	fenye.setTotal(countNetzong);
	return fenye;
}

//添加网络跟踪
public Integer addNetGenzong(Network_trace network_trace) {
	Integer addNetGenzong = null;
	List<Network_trace> selectNetName = netGenzongMapper.selectNetName(network_trace);
	if(selectNetName.size()==0) {
		addNetGenzong = netGenzongMapper.addNetGenzong(network_trace);
	}else {
		return -1;
	}
	return addNetGenzong;
}


  


}
