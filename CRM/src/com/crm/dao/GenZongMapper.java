package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;


public interface GenZongMapper {
   //��ҳ��ʾ����
	List<Network_trace> selectGenZong(Fenye<Network_trace> fenye);
	//��ѯ����
	Integer count(Fenye<Network_trace> fenye);
	
	
	
    //����
    Integer Addgenzong(Network_trace network_trace);
    
}
