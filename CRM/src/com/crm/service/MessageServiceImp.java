package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.MessageMapper;
import com.crm.entity.Message;
import com.crm.entity.Users;
@Service
public class MessageServiceImp implements MessageService {
	@Autowired
	private MessageMapper messageMapper;
	@Override
	public Users seleUsers(String Name) {
		return messageMapper.seleUsers(Name);
	}
	@Override
	public Integer insertMessage(Message message) {
		// TODO Auto-generated method stub
		return messageMapper.insertMessage(message);
	}
	@Override
	public List<Message> SelectMessage(Integer AskId) {
		// TODO Auto-generated method stub
		return messageMapper.SelectMessage(AskId);
	}
	@Override
	public Integer DeleteMessage(Integer AskId) {
		// TODO Auto-generated method stub
		return messageMapper.DeleteMessage(AskId);
	}

}
