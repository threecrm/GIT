package com.crm.service;


import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface CounselorService {

	//查询学生
		Fenye<Student> SelectStudent(Fenye<Student> studentfenye);
		
		//添加追踪数据
		Integer Insert(Network_trace network_trace);
		
		//查询追踪表数据
		Fenye<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye);
	
		//查询网络追踪,单个学生信息的 数据
	    Fenye<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye);
		/////
		
        //删除追踪记录
	   Integer Delete(Integer n_id);
	   
	   //修改登录信息
		Integer UpdateUserInfo(Users users);
		
		//修改用户密码
		Integer UpdateUserPassword(Users users);
		
		//查询用户信息
		Users selectUsersInfo(Integer uid);
		
		//修改学生信息
		 Integer UpdateStudentInfo(Student student);
		 
		// 查询咨询师信息
		 Ask selectAskInfo(String LonginName);
		 
		// 查询用户密码
		Users selectUsersPassword(Users u);
}
