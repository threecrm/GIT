package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.GenZongMapper;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Student;
@Service
public class GenZongServiceImpl implements GenZongService{
	@Autowired
	private GenZongMapper genZongMapper;

	public Fenye<GenZong> selectGenZong(Fenye<GenZong> fenye) {
		List<GenZong> selectGenZong = genZongMapper.selectGenZong(fenye);
		Integer count = genZongMapper.count(fenye);
		fenye.setRows(selectGenZong);
		fenye.setTotal(count);
		return fenye;
	}

	public Integer Addgenzong(GenZong genZong) {
		Integer i=null;
		List<GenZong> selectStudentName = genZongMapper.selectGenZongName(genZong);
		if(selectStudentName.size()==0) {
			i = genZongMapper.Addgenzong(genZong);
		}else {
			return -1;
		}
		return i;
	}

}
