package com.crm.dao;

import com.crm.entity.Users;

public interface MessageMapper {
	//根据用户名称查询用户
	Users seleUsers(String Name);
}
