package com.crm.service;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;

public interface UsersService {
	/**
	 * 用户分页查询
	 * @param fenye
	 * @return
	 */
	List<Users> selectUser(Fenye<Users> fenye);
	/**
	 * 添加用户
	 * @param users
	 * @return
	 */
	Integer insertUser(Users users);
	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	Integer delUser(Integer uid,String askName);
	/**
	 * 修改用户
	 * @param users
	 * @return
	 */
	Integer updateUser(Users users);
	
	/**
	 * 锁定用户
	 * @param LonginName
	 * @return
	 */
	Integer lockUser(String LonginName);
	/**
	 * 解锁用户
	 * @param LonginName
	 * @return
	 */
	Integer unlockUser(String LonginName);
	
	/**
	 * 重置密码
	 * @param LonginName
	 * @return
	 */
	Integer chongzhiPwd(String LonginName);
	
	/**
	 * 根据用户名查询该用户的角色
	 * @param LonginName
	 * @return
	 */
	List<Roles> selectRoles(String LonginName);
	
	/**
	 * 为用户设置角色
	 * @param users
	 * @return
	 */
	Integer addUserRoles(UserRoles userRoles);
	
	/**
	 * 移除用户角色
	 * @param urid
	 * @return
	 */
	Integer delUserRoles(UserRoles userRoles);

}
