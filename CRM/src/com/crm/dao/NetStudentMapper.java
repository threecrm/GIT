package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Student;

public interface NetStudentMapper {
	 //�鿴����δʧЧѧ��
    List<Student> selectNetStudent(Fenye<Student> fenye);
    //�鿴��������
    Integer selectNetStudentCount(Fenye<Student> fenye);
    //���ѧ����Ϣ
    Integer addStudent(Student student);
    //�鿴ʧЧѧ������
    List<Student>  selectShiXiaoStudent(String askName);
    //�鿴������ѯʦ�µ�ѧ��
    List<Student> selectAllNetStudent();
    //�޸�ѧ��
    Integer updateNetStudent(Student student);
    //�޸�ʧЧ
    Integer updateShixiaoStudent(Student student);
    //��ѯѧ������ѯʦ
    Student selectAsks(Integer id);

    Ask selectAsk(Integer askid);
}
