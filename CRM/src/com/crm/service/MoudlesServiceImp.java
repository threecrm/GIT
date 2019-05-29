package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.ModulesMapper;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
@Service
public class MoudlesServiceImp implements ModulesService {
	@Autowired
	private ModulesMapper modulesMapper;
	/**
	 * ���ݸ�ģ��id��ѯ��ģ��
	 */
	public List<Modules> selectModulesSubmodule(Integer id) {
		List<Modules> a = modulesMapper.selectModulesSubmodule(0);
		for(int i=0;i<a.size();i++){
			List<Modules> c = getModules((a.get(i)).getId());
			a.get(i).setChildren(c);
		}
		return a;
	}
	public List<Modules> getModules(Integer id){
		List<Modules> b = modulesMapper.selectModulesSubmodule(id);
			for(int j=0;j<b.size();j++){
				List<Modules> c = getModules(b.get(j).getId());
				b.get(j).setChildren(c);
			}
		return b;
	}
	/**
	 * ��Ӹ�ģ��
	 */
	public Integer addParentModules(Modules modules) {
		// TODO Auto-generated method stub
		Integer addParentModules =null;
		List<Modules> selectRoleByName = modulesMapper.selectMoudleByName(modules);
		if(selectRoleByName.size()==0){
			addParentModules = modulesMapper.addParentModules(modules);
		}else{
			addParentModules = -1;
		}
		return addParentModules;
		
	}
	/**
	 * �����ģ��
	 */
	public Integer addModules(Modules modules) {
		// TODO Auto-generated method stub
		Integer addModules =null;
		List<Modules> selectRoleByName = modulesMapper.selectMoudleByName(modules);
		if(selectRoleByName.size()==0){
			addModules = modulesMapper.addModules(modules);
		}else{
			addModules=-1;
		}
		return addModules;
	}
	/**
	 * �޸�ģ����Ϣ
	 */
	public Integer updateModules(Modules modules) {
		// TODO Auto-generated method stub
		return modulesMapper.updateModules(modules);
	}
	/**
	 * ɾ��ģ��
	 */
	public Integer deleteModules(Integer mid) {
		// TODO Auto-generated method stub
		Integer deleteModules =null;
		List<RoleModules> selectModules = modulesMapper.selectModules(mid);
		List<Modules> selectChildren = modulesMapper.selectChildren(mid);
		     if(selectModules.size()==0 && selectChildren.size()==0 ){
		    	 deleteModules=modulesMapper.deleteModules(mid);
		     }else{
		    	 return -1;
		     }
		return deleteModules;
	}
	/**
	 * ����mid��ѯģ��
	 */
	public List<Modules> GetModuleById(Integer mid) {
		// TODO Auto-generated method stub
		return modulesMapper.GetModuleById(mid);
	}

}
