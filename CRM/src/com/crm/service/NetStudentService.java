package com.crm.service;
import java.util.List;

import com.crm.entity.Fenye;
import com.crm.entity.Student;

public interface NetStudentService {
	 //�鿴����������ѯʦ
	 public Fenye<Student> selectNetStudent(Fenye<Student> fenye);
	 //���ѧ��
	 public Integer addStudent(Student student,Boolean tur);
	 //�鿴ʧЧѧ������
	 public List<Student>  selectShiXiaoStudent(String askName);
	  //�鿴������ѯʦ�µ�ѧ��
	 public List<Student> selectAllNetStudent();
	  //�޸�ѧ��
	 public Integer updateNetStudent(Student student);
	 //�޸�ʧЧ
	 public Integer updateShixiaoStudent(String Sname);
	   //��ѯѧ������ѯʦ
	  public Student selectAsks(Integer id);
	 

}
