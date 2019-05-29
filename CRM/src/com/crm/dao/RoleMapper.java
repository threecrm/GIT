package com.crm.dao;

import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Modules;
import com.crm.entity.RoleModules;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;

public interface RoleMapper {
	/**
	 * ��ҳ��ѯ�û���ɫ
	 * @param fenye
	 * @return
	 */
	List<Roles> selectRole(Fenye<Roles> fenye);
	/**
	 * ��ѯ������
	 * @param fenye
	 * @return
	 */
	Integer selectCount(Fenye<Roles> fenye);
	/**
	 * ��ӽ�ɫ
	 * @param roles
	 * @return
	 */
	Integer addRole(Roles roles);
	
	/**
	 * �޸Ľ�ɫ
	 * @param roles
	 * @return
	 */
	Integer updateRole(Roles roles);
	/**
	 * ɾ����ɫ
	 * @param rid
	 * @return
	 */
	Integer delRoles(Integer rid);
	/**
	 * ���ݽ�ɫId��ѯģ��
	 * @return
	 */
	List<RoleModules> selectModules(Integer rid);
	
	/**
	 * ��ѯ�Ƿ����û�ӵ�иý�ɫ
	 * @param userRoles
	 * @return
	 */
	List<UserRoles> selectRoles(Integer rid);
	
	//���ݸ�ģ��id��ѯ��ģ��
	List<Modules> selectModulesSubmodule(Integer id);
	/**
	 * ���ݽ�ɫ����ѯ��ɫ
	 * @param roles
	 * @return
	 */
	List<Roles> selectRoleByName(Roles roles);
	//����ģ��id��ѯ���g��
	List<RoleModules> selectRoleModules(Integer id);
	//����ģ��id��ѯ���g��
	List<Modules> selectMid(Integer id);
	//���ݽ�ɫidɾ����ɫģ��
	Integer deleteRolesModulesByRid(Integer rid);
	//��ӽ�ɫģ���
	Integer insertRolesModulesByName(RoleModules roleModules);
}
