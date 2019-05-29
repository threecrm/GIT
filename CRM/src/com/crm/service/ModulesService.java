package com.crm.service;

import java.util.List;

import com.crm.entity.Modules;

public interface ModulesService {
	/**
	 * 根据父模块id查询子模块
	 * @param id
	 * @return
	 */
	List<Modules> selectModulesSubmodule(Integer id);
	/**
	 * 添加父模块
	 * @param modules
	 * @return
	 */
	Integer addParentModules(Modules modules);
	/**
	 * 添加子模块
	 * @param modules
	 * @return
	 */
	Integer addModules(Modules modules);
	
	/**
	 * 根据mid查询模块
	 * @param mid
	 * @return
	 */
	List<Modules> GetModuleById(Integer mid);
	
	/***
	 * 修改模块信息
	 * @param modules
	 * @return
	 */
	Integer updateModules(Modules modules);
	
	/***
	 * 删除模块
	 * @param mid
	 * @return
	 */
	Integer deleteModules(Integer mid);


}
