package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.GenZong;


public interface GenZongMapper {
   //分页显示所有
	List<GenZong> selectGenZong(Fenye<GenZong> fenye);
	//查询数量
	Integer count(Fenye<GenZong> fenye);
    //跟踪
    Integer Addgenzong(GenZong genZong);
    //查询跟踪者姓名(判断是否重名)
    List<GenZong> selectGenZongName(GenZong genZong);
}
