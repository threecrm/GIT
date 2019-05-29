package com.crm.dao;

import java.util.List;

import com.crm.entity.Sign_in;

public interface Sign_inMapper {
	/**
	 * 正常签到
	 * @param sign_in
	 * @return
	 */
	Integer addSign(Sign_in sign_in);
	/**
	 * 迟到
	 * @param sign_in
	 * @return
	 */
	Integer addChidaoSign(Sign_in sign_in);
	/**
	 * 查询当天是否已经签到
	 * @param sign_in
	 * @return
	 */
	Sign_in selectSignByTime(Sign_in sign_in);
	/**
	 * 签退
	 * @param sign_in
	 * @return
	 */
	Integer updateSign(Sign_in sign_in);
	/**
	 * 根据id查询签到数据
	 * @param uid
	 * @return
	 */
	Sign_in selectSignByUid(Sign_in sign_in);
	/**
	 * 查询签到信息
	 * @param sign_in
	 * @return
	 */
	Sign_in selectSign(Sign_in sign_in);
	/**
	 * 查询我的考勤
	 * @param sign_in
	 * @return
	 */
	List<Sign_in> selectMySign(Integer uid);
	
	
}
