package com.crm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;
import com.crm.service.UsersService;

@Controller
public class UserController {
	@Autowired
	private Fenye<Users> fenye;
	@Autowired
	private UsersService usersService;
	/**
	 * ��ҳ��ѯ�û�
	 * @param page
	 * @param rows
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectUser",method=RequestMethod.POST)
	public Fenye<Users> selectUser(Integer page,Integer rows,Users users, String beginDates,String endDates){
		fenye.setPage((page-1)*rows);
		fenye.setPageSize(rows);
		fenye.setUsers(users);
		fenye.setBeginDates(beginDates);
		fenye.setEndDates(endDates);
		List<Users> selectUser = usersService.selectUser(fenye);
		fenye.setRows(selectUser);
		return fenye;
	}
	/**
	 * ����û�
	 * @param users
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public Integer addUser(Users users){
		return usersService.insertUser(users);
	}
	/**
	 * ɾ���û�
	 * @param sid
	 * @return
	 */
	  @RequestMapping(value="/delUser",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer delUser(Integer uid,String longinName){
		  return usersService.delUser(uid,longinName);
	  }
	  /**
	   * �޸��û�
	   * @param users
	   * @return
	   */
	  @RequestMapping(value="/updateUser",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer updateUsers(Users users){
		  return usersService.updateUser(users);
	  }
	  /**
	   * �����û�
	   * @param LonginName
	   * @return
	   */
	  @RequestMapping(value="/lockUser",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer lockUser(String LonginName){
		  return usersService.lockUser(LonginName);
	  }
	  /**
	   * �����û�
	   */
	  @RequestMapping(value="/unlockUser",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer unlockUser(String LonginName){
		  return usersService.unlockUser(LonginName);
	  }
	  /**
	   * ��������
	   * @param LonginName
	   * @return
	   */
	  @RequestMapping(value="/chongzhiPwd",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer chongzhiPwd(String LonginName){
		  return usersService.chongzhiPwd(LonginName);
	  }
	  /**
	   * ��ѯ��ǰ�û��Ľ�ɫ
	   * @param LonginName
	   * @return
	   */
	  @RequestMapping(value="/selectRoles",method=RequestMethod.POST)
	  @ResponseBody
	  public List<Roles> selectRoles(String LonginName){
		  return usersService.selectRoles(LonginName);
	  }
	  /**
	   * �����û���ɫ
	   * @param userRoles
	   * @return
	   */
	  @RequestMapping(value="/addUserRoles",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer addUserRoles(UserRoles userRoles){
		  return usersService.addUserRoles(userRoles);
	  }
	  /**
	   * �Ƴ��û���ɫ
	   * @param userid
	   * @param roleid
	   * @return
	   */
	  @RequestMapping(value="/delUserRoles",method=RequestMethod.POST)
	  @ResponseBody
	  public Integer delUserRoles (UserRoles userRoles) 
	   {
		 return usersService.delUserRoles(userRoles);
	}
}
