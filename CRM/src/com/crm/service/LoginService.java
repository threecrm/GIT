package com.crm.service;

import java.util.List;

import com.crm.entity.Roles;
import com.crm.entity.Users;

public interface LoginService {
	
	/**
	 * 查询所有的用户
	 * 
	 * @return
	 */
	List<Users> selectUsers();

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param users
	 * @return
	 */
	Users selectUsersPass(Users users);

	/**
	 * 修改用户密码错误次数
	 * 
	 * @param users
	 * @return
	 */
	Integer updateUsers(Users users);

	/**
	 * 修改个人信息
	 * 
	 * @param uid
	 * @return
	 */
	Integer updatePim(Users users);

	/**
	 * 查询改用户拥有的所有的角色
	 * 
	 * @param users
	 * @return
	 */
	List<Roles> selectRolesName(Users users);
}
