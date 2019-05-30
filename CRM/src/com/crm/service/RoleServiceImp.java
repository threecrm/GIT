package com.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.ModulesMapper;
import com.crm.dao.RoleMapper;
import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private Fenye<Roles> fenye;
	@Autowired
	private ModulesMapper modulesMapper;
	/**
	 * 分页查询
	 */
	public List<Roles> selectRole(Fenye<Roles> fenye) {
		List<Roles> selectRole = roleMapper.selectRole(fenye);
		Integer selectCount = roleMapper.selectCount(fenye);
		fenye.setTotal(selectCount);
		return selectRole;
	}
	/**
	 * 添加角色
	 */
	public Integer addRole(Roles roles) {
		Integer addRole =null;
		List<Roles> selectRoleByName = roleMapper.selectRoleByName(roles);
		if(selectRoleByName.size()==0){
			addRole = roleMapper.addRole(roles);
		}else{
			addRole=-1;
		}
		return addRole;
	}
	/**
	 * 修改角色
	 */
	public Integer updateRole(Roles roles) {
		Integer updateRole =null;
		List<Roles> selectRoleByName = roleMapper.selectRoleByName(roles);
		if(selectRoleByName.size()==0){
			updateRole = roleMapper.updateRole(roles);
		}else{
			updateRole=-1;
		}
		return updateRole;
	}
	/**
	 * 删除角色
	 */
	public Integer delRoles(Integer rid) {
		Integer delRoles=null;
		List<RoleModules> selectModules = roleMapper.selectModules(rid);
		List<UserRoles> selectUserRoles = roleMapper.selectRoles(rid);
		if(selectModules.size()==0 && selectUserRoles.size()==0){
			delRoles = roleMapper.delRoles(rid);
	     }
		else{
			return -1;
		}
		return delRoles;
		
	}
	
	
	List<Integer> ids = null;
	List<Modules> ab =null;
	public List<Modules> selectModules(Integer rid) {
		
		ids = new ArrayList<Integer>();
		List<RoleModules> selectModules = roleMapper.selectModules(rid);
		for(int x=0;x<selectModules.size();x++){
			List<Modules> selectMid = roleMapper.selectMid(selectModules.get(x).getModuleid());
			for(int y=0;y<selectMid.size();y++){
				if(selectMid.get(y).getParentId()!=0){
					ids.add(selectMid.get(y).getId());
				}
			}
		}
		for(int i=0;i<ids.size();i++){
//			System.out.println(id.get(i));
		}
		ab = modulesMapper.selectModulesSubmodule(0);
		for(int i=0;i<ab.size();i++){
			List<Modules> modules1 = getModules1(ab.get(i).getId());
			ab.get(i).setChildren(modules1);
		}
		return ab;
	}
	public List<Modules> getModules1(Integer id){
		List<Modules> c = getModules(id);
		for(int j=0;j<c.size();j++){
			if(ids.contains(c.get(j).getId())){
				List<Modules> modules = getModules1(c.get(j).getId());
				if(modules.size()==0){
					c.get(j).setChecked(true);
				}
				c.get(j).setChildren(modules);
				}
		}
		return c;
	}
	
	
	public List<Modules> getModules(Integer id){
		List<Modules> b = modulesMapper.selectModulesSubmodule(id);
		for(int j=0;j<b.size();j++){
			List<Modules> c = getModules(b.get(j).getId());
			b.get(j).setChildren(c);
		}
		return b;
	}
	public Integer deleteRolesModulesByRid(Integer rid) {
		Integer deleteRolesModulesByRid = roleMapper.deleteRolesModulesByRid(rid);
		return deleteRolesModulesByRid;
	}
	public Integer insertRolesModulesByName(RoleModules roleModules) {
		Integer insertRolesModulesByName = roleMapper.insertRolesModulesByName(roleModules);
		return insertRolesModulesByName;
	}
	@Override
	public String selectName(String LonginName) {
		List<Roles> selectName = roleMapper.selectName(LonginName);
		for(int i=0;i<selectName.size();i++){
			if("咨询师经理".equals(selectName.get(i).getRoleName())){
				return "true";
			}
		}
		return "false";
	}
	

}
