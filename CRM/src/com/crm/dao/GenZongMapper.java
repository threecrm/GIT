package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.GenZong;


public interface GenZongMapper {
   //��ҳ��ʾ����
	List<GenZong> selectGenZong(Fenye<GenZong> fenye);
	//��ѯ����
	Integer count(Fenye<GenZong> fenye);
    //����
    Integer Addgenzong(GenZong genZong);
    //��ѯ����������(�ж��Ƿ�����)
    List<GenZong> selectGenZongName(GenZong genZong);
}
