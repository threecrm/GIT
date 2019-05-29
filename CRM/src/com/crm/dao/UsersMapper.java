package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;

public interface UsersMapper {
	
	/**
	 * 用户分页查询
	 * @param fenye
	 * @return
	 */
	List<Users> selectUser(Fenye<Users> fenye);
	/**
	 * 查询用户总条数
	 * @param fenye
	 * @return
	 */
	Integer selectCount(Fenye<Users> fenye);
	/**
	 * 根据用户名查询用户
	 * @param users
	 * @return
	 */
	List<Users> selectUserName(Users users);
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
	Integer delUser(Integer uid);
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
	 * 查询用户是否拥有该角色
	 * @param userRoles
	 * @return
	 */
	List<UserRoles> selectUserRoles(UserRoles userRoles);
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
	/**
	 * 查询出用户名
	 * @param uid
	 * @return
	 */
	Users selectUsersName(Integer uid);
	/**
	 * 查询出角色名
	 * @param rid
	 * @return
	 */
	Roles selectRolesName(Integer rid);
	
	/**
	 * 添加到咨询师
	 * @param ask
	 * @return
	 */
	Integer addAsk(Ask ask);
	
	
	/**
	 * 删除咨询师
	 * @param askId
	 * @return
	 */
	Integer delAsk(Ask ask);
	
}
