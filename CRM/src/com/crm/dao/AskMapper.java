package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Sign_in;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface AskMapper {
	//分页显示咨询师所有
	List<Ask> selectAsk(Fenye<Ask> fenye);
	//查询所有咨询师数量
	Integer selectCountAsk(Fenye<Ask> fenye);
	//查询网络咨询师
	List<Ask> selectInternetAsk(Fenye<Ask> fenye);
	//查询网络咨询师数量
	Integer selectNetAskCount(Fenye<Ask> fenye);
	//查询咨询师
	List<Ask> selectAskTeacher(Fenye<Ask> fenye);	
	//查询咨询师数量
	Integer selectAskCount(Fenye<Ask> fenye);
	//查询角色
	List<Roles> selectRoles();
	//修改网络咨询师
	Integer updateAsk(Ask ask);
	//查询网络学生
	List<Student> selectStudent(Integer askId);
	//签到
	Integer updateQianDao(Ask ask);
	//签退
	Integer updateQianTui(Ask ask);
	//权重排序
	List<Ask> paixun(Ask ask);
	//根据用户名称查询用户
	Users selectUsers(String longinName);
	/**
	 * 查询当天签到的员工
	 * @param sign_in
	 * @return
	 */
	/*List<Sign_in> selectSignByTime(Sign_in sign_in);*/
	/**
	 * 一键签退 咨询师
	 * @param ask
	 * @return
	 */
	Integer allQiantui(Ask ask);
	/**
	 * 一键签退 签到表
	 * @param sign_in
	 * @return
	 */
	Integer allQiantuis(Sign_in sign_in);
	
	/**
	 * 查询出当天未签到的咨询师
	 * @param ask
	 * @return
	 */
	List<Ask> selectAskStatue(String date);
	/**
	 * 未签到的咨询师修改未签到
	 * @param ask
	 * @return
	 */
	Integer updateAskStatue(Ask ask);
	/**
	 * 分量设置
	 * @return
	 */
	Integer updateFenLiang(Ask ask);
	/**
	 * 根据名字查询咨询师
	 * @param Name
	 * @return
	 */
	Ask selectAskName(String AskName);
}
