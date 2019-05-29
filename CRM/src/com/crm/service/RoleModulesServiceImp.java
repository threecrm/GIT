package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.RoleModulesMapper;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
@Service
public class RoleModulesServiceImp implements RoleModulesService{
	@Autowired
	private RoleModulesMapper roleModulesMapper;
	@Autowired
	private RoleModulesMapper modulesMapper;
	public 	List<Modules> selectRoleModules(String loginName) {
		List<Modules> a = new ArrayList<Modules>();
		
		//根据登录的用户名称查询到给用户所拥有的所有模块
			List<Modules> selectModules = roleModulesMapper.selectModules(loginName);
			for(int j=0;j<selectModules.size();j++){
				//判断parentId为0的是父模块
				if(selectModules.get(j).getParentId()==0){
					Modules m = new Modules();
					m.setId(selectModules.get(j).getId());
					m.setText(selectModules.get(j).getText());
					//根据所有的子模块id查询角色模块表是否有这个子模块
					List<Modules> modules = getModules(selectModules.get(j).getId(),loginName);
					m.setChildren(modules);
					a.add(m);
			}
		}
		return a;
	}
	public List<Modules> getModules(Integer id,String loginName){
		//根据父模块id查询下面的子模块
			List<Modules> selectModulesSubmodule = roleModulesMapper.selectModulesSubmodule(id,loginName);
			for(int i=0;i<selectModulesSubmodule.size();i++){
				//递归
				List<Modules> modules = getModules(selectModulesSubmodule.get(i).getId(), loginName);
				selectModulesSubmodule.get(i).setChildren(modules);
			}
		return selectModulesSubmodule;
	}
}

