package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;

public interface RoleMapper {
	/**
	 * 分页查询用户角色
	 * @param fenye
	 * @return
	 */
	List<Roles> selectRole(Fenye<Roles> fenye);
	/**
	 * 查询总条数
	 * @param fenye
	 * @return
	 */
	Integer selectCount(Fenye<Roles> fenye);
	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	Integer addRole(Roles roles);
	
	/**
	 * 修改角色
	 * @param roles
	 * @return
	 */
	Integer updateRole(Roles roles);
	/**
	 * 删除角色
	 * @param rid
	 * @return
	 */
	Integer delRoles(Integer rid);
	/**
	 * 根据角色Id查询模块
	 * @return
	 */
	List<RoleModules> selectModules(Integer rid);
	
	/**
	 * 查询是否有用户拥有该角色
	 * @param userRoles
	 * @return
	 */
	List<UserRoles> selectRoles(Integer rid);
	
	//根据父模块id查询子模块
	List<Modules> selectModulesSubmodule(Integer id);
	/**
	 * 根据角色名查询角色
	 * @param roles
	 * @return
	 */
	List<Roles> selectRoleByName(Roles roles);
	//根据模块id查询中間表
	List<RoleModules> selectRoleModules(Integer id);
	//根据模块id查询中間表
	List<Modules> selectMid(Integer id);
	//根据角色id删除角色模块
	Integer deleteRolesModulesByRid(Integer rid);
	//添加角色模块表
	Integer insertRolesModulesByName(RoleModules roleModules);
}
