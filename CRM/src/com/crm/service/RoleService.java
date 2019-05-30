package com.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;

public interface RoleService {
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param fenye
	 * @return
	 */
	List<Roles> selectRole(Fenye<Roles> fenye);

	/**
	 * ��ӽ�ɫ
	 * 
	 * @param roles
	 * @return
	 */
	Integer addRole(Roles roles);

	/**
	 * �޸Ľ�ɫ
	 * 
	 * @param roles
	 * @return
	 */
	Integer updateRole(Roles roles);

	/**
	 * ɾ����ɫ
	 * 
	 * @param rid
	 * @return
	 */
	Integer delRoles(Integer rid);

	/**
	 * ���ݽ�ɫId��ѯģ��
	 * 
	 * @return
	 */
	List<Modules> selectModules(Integer rid);

	// ���ݽ�ɫidɾ����ɫģ��
	Integer deleteRolesModulesByRid(Integer rid);

	// ��ӽ�ɫģ���
	Integer insertRolesModulesByName(RoleModules roleModules);

	// �����û�����ѯ���û�������ѯʦ�����ɫ
	String selectName(String LonginName);

}
