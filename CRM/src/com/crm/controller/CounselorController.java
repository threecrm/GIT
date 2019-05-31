package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;
import com.crm.service.CounselorService;

@Controller
public class CounselorController {
	@Autowired
	private CounselorService counselorService;
	@Autowired
	private Fenye<Network_trace> fenye;
	@Autowired
	private Fenye<Student> studentfenye;

	@RequestMapping(value = "/ShowStudent", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Student> SelectStudent(Student student, Integer page,
			Integer rows) {

		// 查询用户名信息 得到name
		Users user = counselorService.selectUsersInfo(student.getU_id());

		// 查询咨询师id
		Ask ask = counselorService.selectAskInfo(user.getLonginName());
		if(ask==null){
			 return studentfenye;
		}
		studentfenye.setPage((page - 1) * rows);
		studentfenye.setPageSize(rows);
		studentfenye.setStudent(student);
		studentfenye.setAsk(ask);

		studentfenye = counselorService.SelectStudent(studentfenye);

		return studentfenye;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Integer Insert(Network_trace network_trace) {
		// 查询用户名信息 得到name
		Users user = counselorService.selectUsersInfo(network_trace.getUid());

		// 查询咨询师id
		Ask ask = counselorService.selectAskInfo(user.getLonginName());

		network_trace.setUid(ask.getAskId());

		Integer Insert = counselorService.Insert(network_trace);
		return Insert;
	}

	@RequestMapping(value = "/SeleteNetwork_trace", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Network_trace> SelectNetworkTrace(Network_trace network_trace,
			Integer page, Integer rows) {
		Users user = counselorService.selectUsersInfo(network_trace.getUid());
		// 查询咨询师id
		Ask ask = counselorService.selectAskInfo(user.getLonginName());

		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		fenye.setNetwork_trace(network_trace);
		fenye.setAsk(ask);
		fenye = counselorService.SelectNetworkTrace(fenye);
		return fenye;
	}

	@RequestMapping(value = "/SeleteNetwork_traceLog", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Network_trace> SeleteNetwork_traceLog(
			Network_trace network_trace, Integer page, Integer rows) {

		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		fenye.setNetwork_trace(network_trace);
		fenye = counselorService.SeleteNetwork_traceLog(fenye);

		return fenye;
	}

	@RequestMapping(value = "/DeleteNetwork_trace", method = RequestMethod.POST)
	@ResponseBody
	public Integer Delete(Integer n_id) {
		Integer Delete = counselorService.Delete(n_id);
		return Delete;
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Integer UpdatePassword(Users users) {
		Integer Update = counselorService.UpdateUserInfo(users);
		return Update;
	}

	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Integer UpdateUserPassword(Users u, Integer uid, String PassWord,
			String newPassword, String trueNewPassword) {
		Users user = counselorService.selectUsersPassword(u);
		if (user != null) {
			if (newPassword.equals(trueNewPassword)) {
				u.setUid(uid);
				u.setPassWord(newPassword);
				Integer Update = counselorService.UpdateUserPassword(u);
				return Update;
			} else {
				return -1;
			}
		} else {
			return 0;
		}

	}

	@RequestMapping(value = "/selectUsersInfo", method = RequestMethod.POST)
	@ResponseBody
	public Users selectUsersInfo(Integer uid) {
		Users user = counselorService.selectUsersInfo(uid);
		return user;
	}

	@RequestMapping(value = "/UpdateStudentInfo", method = RequestMethod.POST)
	@ResponseBody
	public Integer UpdateStudentInfo(Student student) {

		Integer Update = counselorService.UpdateStudentInfo(student);

		return Update;
	}

}
