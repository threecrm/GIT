package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.Sign_inMapper;
import com.crm.entity.Sign_in;
@Service
public class Sign_inServiceImp implements Sign_inService {
	   @Autowired
	   private Sign_inMapper sign_inMapper;
/**
 * 签到
 */
	public Integer addSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.addSign(sign_in);
	}
/**
 * 查询当天是否已经签到
 */
	public Sign_in selectSignByTime(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSignByTime(sign_in);
	}
/**
 * 签退	
 */
	public Integer updateSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.updateSign(sign_in);
	}
/**
 * 根据id查询签到数据
 */
	public Sign_in selectSignByUid(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSignByUid(sign_in);
	}
/**
 * 查询该用户当天签到信息
 */
	public Sign_in selectSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSign(sign_in);
	}
	/**
	 * 查询我的考勤
	 */
public List<Sign_in> selectMySign(Integer uid) {
	// TODO Auto-generated method stub
	return sign_inMapper.selectMySign(uid);
}
/**
 * 迟到
 */
	public Integer addChidaoSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.addChidaoSign(sign_in);
	}

}
