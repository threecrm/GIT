package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface CounselorMapper {
	/**
	 * 查询学生总条数
	 * @param fenye
	 * @return
	 */
	Integer SelectCount(Fenye<Student> fenye);

	/**
	 *  查询学生数据
	 * @param fenye
	 * @return
	 */
	List<Student> SelectStudent(Fenye<Student> fenye);

	/**
	 * 添加拜访的数据
	 * @param fenye
	 * @return
	 */
	Integer InsertInfo(Network_trace network_trace);

	// 
	/**
	 * 查询网络追踪学生的 总条数
	 * @param fenye
	 * @return
	 */
	Integer SelectNetworkTraceCount(Fenye<Network_trace> fenye);

	/**
	 * 查询网络追踪学生的数据
	 * @param fenye
	 * @return
	 */
	List<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye);

	/**
	 * 查询网络追踪,单个学生信息的 总条数
	 * @param fenye
	 * @return
	 */
	Integer SeleteNetwork_traceLogCount(Fenye<Network_trace> fenye);

	/**
	 * 查询网络追踪,单个学生信息的 数据
	 * @param fenye
	 * @return
	 */
	List<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye);

	/**
	 * 删除追踪记录
	 * @param fenye
	 * @return
	 */
	Integer Delete(Integer n_id);

	/**
	 * 修改登录信息
	 * @param fenye
	 * @return
	 */
	Integer UpdateUserInfo(Users users);

	/**
	 * 修改用户密码
	 * @param fenye
	 * @return
	 */
	Integer UpdateUserPassword(Users users);

	/**
	 * 根据ID 查询用户信息
	 * @param fenye
	 * @return
	 */
	Users selectUsersInfo(Integer uid);

	/**
	 * 修改学生信息
	 * @param fenye
	 * @return
	 */
	Integer UpdateStudentInfo(Student student);

	/**
	 * 查询咨询师信息
	 * @param fenye
	 * @return
	 */
	Ask selectAskInfo(String LonginName);

	/**
	 * 查询咨询师密码
	 * @param fenye
	 * @return
	 */
	Users selectUsersPassword(Users u);

}
