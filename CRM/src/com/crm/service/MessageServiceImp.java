package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.MessageMapper;
import com.crm.entity.Users;
@Service
public class MessageServiceImp implements MessageService {
	@Autowired
	private MessageMapper messageMapper;
	@Override
	public Users seleUsers(String Name) {
		return messageMapper.seleUsers(Name);
	}

}
