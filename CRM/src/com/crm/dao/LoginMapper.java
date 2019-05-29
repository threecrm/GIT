package com.crm.dao;

import java.util.List;

import com.crm.entity.Users;

public interface LoginMapper {
	List<Users> selectUsers();
	
	Users selectUsersPass(Users users);
	
	Integer updateUsers(Users users);
	
}
