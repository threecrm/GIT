package com.crm.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dao.RoleMapper;
import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.service.RoleService;
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private Fenye<Roles> fenye;
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectRole",method=RequestMethod.POST)
	public Fenye<Roles> selectUser(Integer page,Integer rows,String RoleName){
		fenye.setPage((page-1)*rows);
		fenye.setPageSize(rows);
		fenye.setRoleName(RoleName);
		List<Roles> selectRole = roleService.selectRole(fenye);
		fenye.setRows(selectRole);
		return fenye;
	}
	/**
	 * 添加角色
	 * @param roles
	 * @return
	 */
	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	@ResponseBody
	public Integer addRole(Roles roles){
		return roleService.addRole(roles);
	}
	/**
	 * 修改角色
	 * @param roles
	 * @return
	 */
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	@ResponseBody
	public Integer updateRole(Roles roles){
		return roleService.updateRole(roles);
	}
	/**
	 * 删除角色
	 */
	@RequestMapping(value="/delRoles",method=RequestMethod.POST)
	@ResponseBody
	public  Integer delRoles(Integer rid) {
		return roleService.delRoles(rid);
	}
	/**
	 * 设置权限模块
	 */
	@RequestMapping(value="/selectModules",method=RequestMethod.POST)
	@ResponseBody
	public  List<Modules> selectModules(Integer rid) {
		List<Modules> selectModules = roleService.selectModules(rid);
		return selectModules;
	}
	/**
	 * 修改角色模块
	 * @param parentIds
	 * @param rId
	 * @return
	 */
	@RequestMapping(value="/rolesModules",method=RequestMethod.POST)
	@ResponseBody
	public Boolean rolesModules(String parentIds,Integer rId,HttpServletRequest request) {
		
		roleService.deleteRolesModulesByRid(rId);
		
		List<RoleModules> selectModules = roleMapper.selectModules(rId);
		
		if(selectModules.size()==0){
			if(parentIds.equals("")){
				return true;
			}else{
				String[] split = parentIds.split(",");
				for(int i=0;i<split.length;i++){
					RoleModules roleModules = new RoleModules();
					roleModules.setRoleid(rId);
					roleModules.setModuleid(Integer.parseInt(split[i]));
					roleService.insertRolesModulesByName(roleModules);
				}
			}
		}
		
		return true;
	}
	@RequestMapping(value="/selectRoleName",method=RequestMethod.POST)
	@ResponseBody
	public String selectRoleName(String loginName,HttpSession session) {
		String selectName = roleService.selectName(loginName);
		session.setAttribute("selectName",selectName );
		session.setAttribute("selectNameTrue","true" );
		return selectName;
	}
}
