package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;

public interface NetGenzongMapper {
	
	//��ѯ���������Ϣ
    List<Network_trace> selectNetGenzong(Fenye<Network_trace> fenye);
    //��ѯ�����������
    Integer countNetzong(Fenye<Network_trace> fenye);
    //����������
    Integer addNetGenzong(Network_trace network_trace);
  //�жϸ��������Ƿ����� 
    List<Network_trace> selectNetName(Network_trace network_trace);

}
