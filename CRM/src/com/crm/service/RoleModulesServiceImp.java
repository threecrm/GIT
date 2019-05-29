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
		
		//���ݵ�¼���û����Ʋ�ѯ�����û���ӵ�е�����ģ��
			List<Modules> selectModules = roleModulesMapper.selectModules(loginName);
			for(int j=0;j<selectModules.size();j++){
				//�ж�parentIdΪ0���Ǹ�ģ��
				if(selectModules.get(j).getParentId()==0){
					Modules m = new Modules();
					m.setId(selectModules.get(j).getId());
					m.setText(selectModules.get(j).getText());
					//�������е���ģ��id��ѯ��ɫģ����Ƿ��������ģ��
					List<Modules> modules = getModules(selectModules.get(j).getId(),loginName);
					m.setChildren(modules);
					a.add(m);
			}
		}
		return a;
	}
	public List<Modules> getModules(Integer id,String loginName){
		//���ݸ�ģ��id��ѯ�������ģ��
			List<Modules> selectModulesSubmodule = roleModulesMapper.selectModulesSubmodule(id,loginName);
			for(int i=0;i<selectModulesSubmodule.size();i++){
				//�ݹ�
				List<Modules> modules = getModules(selectModulesSubmodule.get(i).getId(), loginName);
				selectModulesSubmodule.get(i).setChildren(modules);
			}
		return selectModulesSubmodule;
	}
}

