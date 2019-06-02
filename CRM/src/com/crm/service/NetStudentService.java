package com.crm.service;
import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Student;

public interface NetStudentService {
	 //查看所有网络咨询师
	 public Fenye<Student> selectNetStudent(Fenye<Student> fenye);
	 //添加学生
	 public Integer addStudent(Student student,Boolean tur);
	 //查看失效学生名单
	 public List<Student>  selectShiXiaoStudent(String askName);
	  //查看所有咨询师下的学生
	 public List<Student> selectAllNetStudent();
	  //修改学生
	 public Integer updateNetStudent(Student student);
	 //修改失效
	 public Integer updateShixiaoStudent(String Sname);
	   //查询学生和咨询师
	  public Student selectAsks(Integer id);
	 

}
