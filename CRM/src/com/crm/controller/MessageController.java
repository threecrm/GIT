package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Ask;
import com.crm.entity.Message;
import com.crm.entity.Users;
import com.crm.service.CounselorService;
import com.crm.service.MessageService;
@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private CounselorService counselorService;
	
	@RequestMapping(value="/selectUsersNameMessage",method=RequestMethod.POST)
	@ResponseBody
	public Users selectUsersNameMessage(String Name){
		Users seleUsers = messageService.seleUsers(Name);
		return seleUsers;
	}
	
	@RequestMapping(value="/SelectMessage",method=RequestMethod.POST)
	@ResponseBody
	public List<Message> SelectMessage(Integer AskId){
		//查询用户名信息   得到name
	          Users user=counselorService.selectUsersInfo(AskId);
		//查询咨询师id
			  Ask ask =counselorService.selectAskInfo(user.getLonginName());
			  if(ask==null){
				  return null;
			  }
		//根据咨询师Id   查询是否有新增消息记录
			  List<Message> selectMessage=messageService.SelectMessage(ask.getAskId());
		return selectMessage;
	}
	
	@RequestMapping(value="/DeleteMessage",method=RequestMethod.POST)
	@ResponseBody
	public Integer DeleteMessage(Integer AskId){
		//查询用户名信息   得到name
        Users user=counselorService.selectUsersInfo(AskId);
	    //查询咨询师id
		Ask ask =counselorService.selectAskInfo(user.getLonginName());
		  
        Integer del=messageService.DeleteMessage(ask.getAskId());
		return del;
	}
}
