package com.crm.service;
import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;
import com.crm.entity.Users;
public interface AskService {
	//显示所有咨询师
	public Fenye<Ask> selectAskAll(Fenye<Ask> fenye);	
	//查询网络咨询师
	public Fenye<Ask> selectInternetAsk(Fenye<Ask> fenye);
	//查询咨询师
	public Fenye<Ask> selectAskTeacher(Fenye<Ask> fenye);
	//查询角色
	public List<Roles> selectRoles();
     //修改
	 public Integer updateAsk(Ask ask);
	 //查询网络学生
	public List<Student> selectStudent(Integer askId);
	//签到
	public Integer updateQianDao(Ask ask);
	//签退
	 public	Integer updateQianTui(Ask ask);
	//权重排序
	public	List<Ask> paixun(Ask ask);
	/**
	 * 一键签退
	 * @param ask
	 * @return
	 */
	Integer allQiantui(Ask ask);
	/**
	 * 把未签到的咨询师修改成未签到状态
	 * @return
	 */
	Integer updateASkStatue();
}
