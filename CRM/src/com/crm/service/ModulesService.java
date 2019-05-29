package com.crm.service;

import java.util.List;

import com.crm.entity.Modules;

public interface ModulesService {
	/**
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
	
	/***
	 * ɾ��ģ��
	 * @param mid
	 * @return
	 */
	Integer deleteModules(Integer mid);


}
