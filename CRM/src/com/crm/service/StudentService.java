package com.crm.service;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Roles;
import com.crm.entity.Student;

public interface StudentService {
	// 分页显示学生
	public Fenye selectAllStudent(Fenye<Student> fenye);

	// 查询角色
	public List<Roles> selectRoles();

	// 显示所有学生咨询师
	public Student selectAsk(Integer id);

	// 添加学生
	public Integer addStudent(Student student,Boolean tur);

	// 删除学生
	public Integer deleteStudent(Integer id);

	// 修改学生
	public Integer updateStudent(Student student);

	// 查询咨询师姓名
	public List<Ask> selectAskName();

	/**
	 * 
	 * @param stuid 所有的学生ID
	 * @param askid 咨询师id
	 * @return
	 */
	// 手动分配（添加咨询师）
	public Integer addAskName(List<Integer> list,Integer askid);

	// 查询咨询师下的姓名
	public List<Ask> selectNames();

	// 已分配
	public List<Student> selectYifenpei();

	// 未分配
	public List<Student> selectWeifenpei();

	// 根据权重查咨询师id
	public List<Ask> selectWeightId();

	/**
	 * 分量
	 */
	Integer selectFenLiang(List<Integer> list);
}
