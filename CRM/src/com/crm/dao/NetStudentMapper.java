package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Student;

public interface NetStudentMapper {
	 //查看所有未失效学生
    List<Student> selectNetStudent(Fenye<Student> fenye);
    //查看它的数量
    Integer selectNetStudentCount(Fenye<Student> fenye);
    //添加学生信息
    Integer addStudent(Student student);
    //查看失效学生名单
    List<Student>  selectShiXiaoStudent(String askName);
    //查看所有咨询师下的学生
    List<Student> selectAllNetStudent();
    //修改学生
    Integer updateNetStudent(Student student);
    //修改失效
    Integer updateShixiaoStudent(Student student);
    //查询学生和咨询师
    Student selectAsks(Integer id);

    Ask selectAsk(Integer askid);
}
