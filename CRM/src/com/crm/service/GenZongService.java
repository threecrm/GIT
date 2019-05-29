package com.crm.service;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
public interface GenZongService {
	  //分页显示所有
		public Fenye<GenZong> selectGenZong(Fenye<GenZong> fenye);
	 //跟踪
	    public Integer Addgenzong(GenZong genZong);

}
