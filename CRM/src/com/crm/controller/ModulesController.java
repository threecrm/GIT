package com.crm.controller;


import java.util.List;








import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dao.RoleModulesMapper;
import com.crm.entity.Modules;
import com.crm.entity.Users;
import com.crm.service.ModulesService;
import com.crm.service.RoleModulesService;

@Controller
public class ModulesController {
	@Autowired
	private RoleModulesService roleModulesService;
	@Autowired
	private ModulesService modulesService;
	@Autowired
	private RoleModulesMapper roleModulesMapper;
	/**
	 * ��ѯģ��
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value="roleModules",method=RequestMethod.POST)
	@ResponseBody
	public 	List<Modules> selectRoleModules(String loginName,HttpSession session){
		List<Modules> selectRoleModules = roleModulesService.selectRoleModules(loginName);
		
		Users selectUser = roleModulesMapper.selectUser(loginName);
		session.setAttribute("uid",selectUser.getUid());
		session.setAttribute("LoginUserName",selectUser.getLonginName());
		return selectRoleModules;
	}
	
	/**
	 * ���ݸ�ģ��id��ѯ��ģ��
	 * @return
	 */
	@RequestMapping(value="modules",method=RequestMethod.POST)
	@ResponseBody
	public 	List<Modules> selectModules(){
		List<Modules> selectModulesSubmodule = modulesService.selectModulesSubmodule(0);
		return selectModulesSubmodule;
	}
	
	/**
	 * ��Ӹ�ģ��
	 * @param modules
	 * @return
	 */
	@RequestMapping(value="/addParentModules",method=RequestMethod.POST)
	@ResponseBody
	public Integer addParentModules(Modules modules){
		return modulesService.addParentModules(modules);
	}
	/***
	 * �����ģ��
	 * @param modules
	 * @return
	 */
	@RequestMapping(value="/addModules",method=RequestMethod.POST)
	@ResponseBody
	public Integer addModules(Modules modules){
		return modulesService.addModules(modules);
	}
	/**
	 * �޸�ģ��
	 * @param modules
	 * @return
	 */
	@RequestMapping(value="/updateModules",method=RequestMethod.POST)
	@ResponseBody
	public Integer updateModules(Modules modules){
		return modulesService.updateModules(modules);
	}
	/***
	 * ɾ��ģ��
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/deleteModules",method=RequestMethod.POST)
	@ResponseBody
	public Integer deleteModules(Integer mid){
		return modulesService.deleteModules(mid);
	}
	/**
	 * ����mid��ѯģ��
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/GetModuleById",method=RequestMethod.POST)
	@ResponseBody
	public List<Modules> GetModuleById(Integer mid) {
		return modulesService.GetModuleById(mid);
	}
}
