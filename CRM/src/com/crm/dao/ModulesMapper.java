package com.crm.dao;

import java.util.List;

import com.crm.entity.Modules;
import com.crm.entity.RoleModules;

public interface ModulesMapper {
	/***
	 * ���ݸ�ģ��id��ѯ��ģ��
	 * @param id
	 * @return
	 */
		List<Modules> selectModulesSubmodule(Integer id);
		
		/**
		 * ��Ӹ�ģ��
		 * @param modules
		 * @return
		 */
		Integer addParentModules(Modules modules);
		
		/**
		 * �����ģ��
		 * @param modules
		 * @return
		 */
		Integer addModules(Modules modules);
		/**
		 * ����mid��ѯģ��
		 * @param mid
		 * @return
		 */
		List<Modules> GetModuleById(Integer mid);
		/***
		 * �޸�ģ����Ϣ
		 * @param modules
		 * @return
		 */
		Integer updateModules(Modules modules);
		/**
		 * ��ѯ��ģ����û�б�����
		 * @param mid
		 * @return
		 */
		List<RoleModules> selectModules(Integer mid);
		/**
		 * ��ѯ��ģ������û����ģ��
		 * @param mid
		 * @return
		 */
		List<Modules> selectChildren(Integer mid);
		
		/***
		 * ɾ��ģ��
		 * @param mid
		 * @return
		 */
		Integer deleteModules(Integer mid);
		/**
		 * �������Ʋ�ѯģ��
		 * @param modules
		 * @return
		 */
		List<Modules> selectMoudleByName(Modules modules);

}
