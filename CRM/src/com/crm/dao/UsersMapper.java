package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;

public interface UsersMapper {
	
	/**
	 * �û���ҳ��ѯ
	 * @param fenye
	 * @return
	 */
	List<Users> selectUser(Fenye<Users> fenye);
	/**
	 * ��ѯ�û�������
	 * @param fenye
	 * @return
	 */
	Integer selectCount(Fenye<Users> fenye);
	/**
	 * �����û�����ѯ�û�
	 * @param users
	 * @return
	 */
	List<Users> selectUserName(Users users);
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
	Integer delUser(Integer uid);
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
	 * ��ѯ�û��Ƿ�ӵ�иý�ɫ
	 * @param userRoles
	 * @return
	 */
	List<UserRoles> selectUserRoles(UserRoles userRoles);
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
	/**
	 * ��ѯ���û���
	 * @param uid
	 * @return
	 */
	Users selectUsersName(Integer uid);
	/**
	 * ��ѯ����ɫ��
	 * @param rid
	 * @return
	 */
	Roles selectRolesName(Integer rid);
	
	/**
	 * ��ӵ���ѯʦ
	 * @param ask
	 * @return
	 */
	Integer addAsk(Ask ask);
	
	
	/**
	 * ɾ����ѯʦ
	 * @param askId
	 * @return
	 */
	Integer delAsk(Ask ask);
	
}
