package com.crm.dao;

import java.util.List;

import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;


public interface RoleModulesMapper {
	//根据登录名查询模块
	List<Modules> selectModules(String loginName);
	//根据父模块id查询子模块
	List<Modules> selectModulesSubmodule(Integer id,String LonginNmae);
	//根据用户名查询用户
	Users selectUser(String LoginName);
}
