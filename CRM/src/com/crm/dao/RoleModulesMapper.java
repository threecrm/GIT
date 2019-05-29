package com.crm.dao;

import java.util.List;

import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;


public interface RoleModulesMapper {
	//���ݵ�¼����ѯģ��
	List<Modules> selectModules(String loginName);
	//���ݸ�ģ��id��ѯ��ģ��
	List<Modules> selectModulesSubmodule(Integer id,String LonginNmae);
	//�����û�����ѯ�û�
	Users selectUser(String LoginName);
}
