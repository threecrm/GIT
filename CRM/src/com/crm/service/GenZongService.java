package com.crm.service;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
public interface GenZongService {
	  //��ҳ��ʾ����
		public Fenye<GenZong> selectGenZong(Fenye<GenZong> fenye);
	 //����
	    public Integer Addgenzong(GenZong genZong);

}
