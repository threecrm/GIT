package com.crm.service;



import java.util.List;

import com.crm.entity.Modules;

public interface RoleModulesService {
//	²éÑ¯½ÇÉ«Ä£¿é
	List<Modules> selectRoleModules(String loginName);
}
