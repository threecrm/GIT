package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Users;
import com.crm.service.MessageService;
@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	@RequestMapping(value="/selectUsersNameMessage",method=RequestMethod.POST)
	@ResponseBody
	public Users selectUsersNameMessage(String Name){
		Users seleUsers = messageService.seleUsers(Name);
		return seleUsers;
	}
}
