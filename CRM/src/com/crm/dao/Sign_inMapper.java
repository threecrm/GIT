package com.crm.dao;

import java.util.List;

import com.crm.entity.Sign_in;

public interface Sign_inMapper {
	/**
	 * ����ǩ��
	 * @param sign_in
	 * @return
	 */
	Integer addSign(Sign_in sign_in);
	/**
	 * �ٵ�
	 * @param sign_in
	 * @return
	 */
	Integer addChidaoSign(Sign_in sign_in);
	/**
	 * ��ѯ�����Ƿ��Ѿ�ǩ��
	 * @param sign_in
	 * @return
	 */
	Sign_in selectSignByTime(Sign_in sign_in);
	/**
	 * ǩ��
	 * @param sign_in
	 * @return
	 */
	Integer updateSign(Sign_in sign_in);
	/**
	 * ����id��ѯǩ������
	 * @param uid
	 * @return
	 */
	Sign_in selectSignByUid(Sign_in sign_in);
	/**
	 * ��ѯǩ����Ϣ
	 * @param sign_in
	 * @return
	 */
	Sign_in selectSign(Sign_in sign_in);
	/**
	 * ��ѯ�ҵĿ���
	 * @param sign_in
	 * @return
	 */
	List<Sign_in> selectMySign(Integer uid);
	
	
}
