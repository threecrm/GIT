package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.GenZongMapper;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
@Service
public class GenZongServiceImpl implements GenZongService{
	@Autowired
	private GenZongMapper genZongMapper;

	public Fenye<Network_trace> selectGenZong(Fenye<Network_trace> fenye) {
		List<Network_trace> selectGenZong = genZongMapper.selectGenZong(fenye);
		Integer count = genZongMapper.count(fenye);
		fenye.setRows(selectGenZong);
		fenye.setTotal(count);
		return fenye;
	}
//Ìí¼ÓÒ»Ìõ¸ú×Ù
	public Integer Addgenzong(Network_trace network_trace) {
		 Integer Addgenzong = null;
		 List<Network_trace> i = genZongMapper.selectWanChengGenzong(network_trace);
		 if(i.size()==0) {
			 Addgenzong = genZongMapper.Addgenzong(network_trace);
			}else {
				return -1;
			}
		return Addgenzong;
	}

}
