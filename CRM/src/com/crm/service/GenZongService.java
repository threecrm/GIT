package com.crm.service;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
public interface GenZongService {
	  //��ҳ��ʾ����
		public Fenye<Network_trace> selectGenZong(Fenye<Network_trace> fenye);
	 //����
	    public Integer Addgenzong(Network_trace network_trace);

}
