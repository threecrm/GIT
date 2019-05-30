package com.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;

public interface RoleService {
	/**
	 * 分页查询
	 * 
	 * @param fenye
	 * @return
	 */
	List<Roles> selectRole(Fenye<Roles> fenye);

	/**
	 * 添加角色
	 * 
	 * @param roles
	 * @return
	 */
	Integer addRole(Roles roles);

	/**
	 * 修改角色
	 * 
	 * @param roles
	 * @return
	 */
	Integer updateRole(Roles roles);

	/**
	 * 删除角色
	 * 
	 * @param rid
	 * @return
	 */
	Integer delRoles(Integer rid);

	/**
	 * 根据角色Id查询模块
	 * 
	 * @return
	 */
	List<Modules> selectModules(Integer rid);

	// 根据角色id删除角色模块
	Integer deleteRolesModulesByRid(Integer rid);

	// 添加角色模块表
	Integer insertRolesModulesByName(RoleModules roleModules);

	// 根据用户名查询该用户包含咨询师经理角色
	String selectName(String LonginName);

}
