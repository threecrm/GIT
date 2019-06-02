package com.crm.service;

import java.util.List;

import com.crm.entity.Roles;
import com.crm.entity.Users;

public interface LoginService {
	
	/**
	 * ��ѯ���е��û�
	 * 
	 * @return
	 */
	List<Users> selectUsers();

	/**
	 * �����û����������ѯ�û�
	 * 
	 * @param users
	 * @return
	 */
	Users selectUsersPass(Users users);

	/**
	 * �޸��û�����������
	 * 
	 * @param users
	 * @return
	 */
	Integer updateUsers(Users users);

	/**
	 * �޸ĸ�����Ϣ
	 * 
	 * @param uid
	 * @return
	 */
	Integer updatePim(Users users);

	/**
	 * ��ѯ���û�ӵ�е����еĽ�ɫ
	 * 
	 * @param users
	 * @return
	 */
	List<Roles> selectRolesName(Users users);
}
