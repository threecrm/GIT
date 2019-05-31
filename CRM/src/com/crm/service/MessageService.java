package com.crm.service;

import com.crm.entity.Users;

public interface MessageService {
	//根据用户名称查询用户
		Users seleUsers(String Name);
}
