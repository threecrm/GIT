package com.crm.service;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;

public interface NetGenzongService {
	//��ҳ��ʾ����
	public Fenye<Network_trace> selectNetGenZong(Fenye<Network_trace> fenye);
	  //����������
    public Integer addNetGenzong(Network_trace network_trace);

}
