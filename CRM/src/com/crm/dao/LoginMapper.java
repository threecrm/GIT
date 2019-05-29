package com.crm.dao;

import java.util.List;

import com.crm.entity.Users;

public interface LoginMapper {
	List<Users> selectUsers();
	
	Users selectUsersPass(Users users);
	
	Integer updateUsers(Users users);
	
	/**
	 * 修改个人信息
	 * @param uid
	 * @return
	 */
	Integer updatePim(Users users);
	
}
