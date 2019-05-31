package com.crm.service;

import java.util.List;

import com.crm.entity.Message;
import com.crm.entity.Users;

public interface MessageService {
	//根据用户名称查询用户
		Users seleUsers(String Name);
		
		//添加提示消息
		Integer insertMessage(Message message);
		//查询提示消息
		List<Message> SelectMessage(Integer AskId);
		//删除提示消息
		Integer DeleteMessage(Integer AskId);
}
