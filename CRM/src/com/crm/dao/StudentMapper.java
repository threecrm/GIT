package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;

public interface StudentMapper {
	// 查询所有学生
	List<Student> selectStudentAll(Fenye<Student> fenye);

	// 查询学生数量
	Integer countStudent(Fenye fenye);

	// 查询角色
	List<Roles> selectRoles();

	// 查询学生和咨询师
	Student selectAsk(Integer id);

	// 添加学生信息
	Integer addStudent(Student student);

	// 删除学生
	Integer deleteStudent(Integer id);

	// 修改学生
	Integer updateStudent(Student student);

	// 查询咨询师姓名（判断是否签到）
	List<Ask> selectAskName(String date);

	// 手动分配（批量添加咨询师）
	Integer addAskName(Student student);

	// 自动分配（批量添加咨询师）
	Integer addAskNames(Student student);

	// 查询咨询师下的姓名
	List<Ask> selectNames();

	// 已分配
	List<Student> selectYifenpei();

	// 未分配
	List<Student> selectWeifenpei();

	// 根据权重查咨询师id
	List<Ask> selectWeightId();

	/**
	 * 根据id查询未分配的学生
	 */
	List<Student> selecStudent(List<Integer> list);
	/*根据咨询师id查询咨询师有多少学生*/
	Integer selectAskStu(String date,Integer askid);
	/**
	 * 查询所有咨询师，并获取到咨询师Id
	 * @return
	 */
	List<Ask> selectAskAll(String date);
	//分配学生
	Integer updateStudentAsk(Student student);
}
