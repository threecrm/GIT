package com.crm.service;



import java.util.List;

import com.crm.entity.Modules;

public interface RoleModulesService {
//	��ѯ��ɫģ��
	List<Modules> selectRoleModules(String loginName);
}
