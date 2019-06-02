package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.LoginMapper;
import com.crm.entity.Roles;
import com.crm.entity.Users;
@Service
public class LoginServiceImp implements LoginService{
	@Autowired
	private LoginMapper loginMapper;
	public List<Users> selectUsers() {
		List<Users> selectUsers = loginMapper.selectUsers();
		return selectUsers;
	}
	public Integer updateUsers(Users users) {
		Integer i = loginMapper.updateUsers(users);
		return i;
	}
	public Users selectUsersPass(Users users) {
		// TODO Auto-generated method stub
		return loginMapper.selectUsersPass(users);
	}
	
	/**
	 * 修改个人信息
	 */
	public Integer updatePim(Users users) {
		return loginMapper.updatePim(users);
	}
	@Override
	public List<Roles> selectRolesName(Users users) {
		List<Roles> selectRolesName = loginMapper.selectRolesName(users);
		return selectRolesName;
	}

}
