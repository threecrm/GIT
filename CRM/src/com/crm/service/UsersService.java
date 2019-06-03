package com.crm.service;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;

public interface UsersService {
	/**
	 * �û���ҳ��ѯ
	 * @param fenye
	 * @return
	 */
	List<Users> selectUser(Fenye<Users> fenye);
	/**
	 * ����û�
	 * @param users
	 * @return
	 */
	Integer insertUser(Users users);
	/**
	 * ɾ���û�
	 * @param uid
	 * @return
	 */
	Integer delUser(Integer uid,String askName);
	/**
	 * �޸��û�
	 * @param users
	 * @return
	 */
	Integer updateUser(Users users);
	
	/**
	 * �����û�
	 * @param LonginName
	 * @return
	 */
	Integer lockUser(String LonginName);
	/**
	 * �����û�
	 * @param LonginName
	 * @return
	 */
	Integer unlockUser(String LonginName);
	
	/**
	 * ��������
	 * @param LonginName
	 * @return
	 */
	Integer chongzhiPwd(String LonginName);
	
	/**
	 * �����û�����ѯ���û��Ľ�ɫ
	 * @param LonginName
	 * @return
	 */
	List<Roles> selectRoles(String LonginName);
	
	/**
	 * Ϊ�û����ý�ɫ
	 * @param users
	 * @return
	 */
	Integer addUserRoles(UserRoles userRoles);
	
	/**
	 * �Ƴ��û���ɫ
	 * @param urid
	 * @return
	 */
	Integer delUserRoles(UserRoles userRoles);

}
