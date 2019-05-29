package com.crm.dao;

import java.util.List;

import com.crm.entity.Modules;
import com.crm.entity.RoleModules;

public interface ModulesMapper {
	/***
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
		/**
		 * 查询该模块有没有被引用
		 * @param mid
		 * @return
		 */
		List<RoleModules> selectModules(Integer mid);
		/**
		 * 查询该模块下有没有子模块
		 * @param mid
		 * @return
		 */
		List<Modules> selectChildren(Integer mid);
		
		/***
		 * 删除模块
		 * @param mid
		 * @return
		 */
		Integer deleteModules(Integer mid);
		/**
		 * 根据名称查询模块
		 * @param modules
		 * @return
		 */
		List<Modules> selectMoudleByName(Modules modules);

}
