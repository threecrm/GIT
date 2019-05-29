package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.CounselorMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;
@Service
public class CounselorServiceImp implements CounselorService {
   @Autowired
	private CounselorMapper counselorMapper;
	
	public Fenye<Student> SelectStudent(Fenye<Student>  studentfenye) {
		Integer count =counselorMapper.SelectCount(studentfenye);
		List<Student> selectStudent = counselorMapper.SelectStudent(studentfenye);
		
		studentfenye.setRows(selectStudent);
		studentfenye.setTotal(count);
		return studentfenye;
	}

	public Integer Insert(Network_trace network_trace) {
		
		return counselorMapper.InsertInfo(network_trace);
	}

	public Fenye<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye) {
		Integer count=counselorMapper.SelectNetworkTraceCount(fenye);
		List<Network_trace> selectNetwork_trace=counselorMapper.SelectNetworkTrace(fenye);
		fenye.setRows(selectNetwork_trace);
		fenye.setTotal(count);
		return fenye;
	}


	public Integer Delete(Integer n_id) {
		
		return counselorMapper.Delete(n_id);
		
	}

   public Integer UpdateUserInfo(Users users) {
		
		return counselorMapper.UpdateUserInfo(users);
	}

	public Integer UpdateUserPassword(Users users) {
		
		return counselorMapper.UpdateUserPassword(users);
	}

	public Users selectUsersInfo(Integer uid) {
		
		return counselorMapper.selectUsersInfo(uid);
	}

	public Integer UpdateStudentInfo(Student student) {
		
		return counselorMapper.UpdateStudentInfo(student);
	}

	public Ask selectAskInfo(String LonginName) {

		return counselorMapper.selectAskInfo(LonginName);
	}

	public Fenye<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye) {
		
		Integer count=counselorMapper.SeleteNetwork_traceLogCount(fenye);
		List<Network_trace> selectNetwork_trace=counselorMapper.SeleteNetwork_traceLog(fenye);
		
		fenye.setRows(selectNetwork_trace);
		fenye.setTotal(count);
		return fenye;
	}

	public Users selectUsersPassword(Users u) {
		return counselorMapper.selectUsersPassword(u);
	}
}
