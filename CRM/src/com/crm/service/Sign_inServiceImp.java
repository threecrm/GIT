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
 * ǩ��
 */
	public Integer addSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.addSign(sign_in);
	}
/**
 * ��ѯ�����Ƿ��Ѿ�ǩ��
 */
	public Sign_in selectSignByTime(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSignByTime(sign_in);
	}
/**
 * ǩ��	
 */
	public Integer updateSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.updateSign(sign_in);
	}
/**
 * ����id��ѯǩ������
 */
	public Sign_in selectSignByUid(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSignByUid(sign_in);
	}
/**
 * ��ѯ���û�����ǩ����Ϣ
 */
	public Sign_in selectSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.selectSign(sign_in);
	}
	/**
	 * ��ѯ�ҵĿ���
	 */
public List<Sign_in> selectMySign(Integer uid) {
	// TODO Auto-generated method stub
	return sign_inMapper.selectMySign(uid);
}
/**
 * �ٵ�
 */
	public Integer addChidaoSign(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return sign_inMapper.addChidaoSign(sign_in);
	}

}
