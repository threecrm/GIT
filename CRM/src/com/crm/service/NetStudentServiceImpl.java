package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.NetStudentMapper;
import com.crm.entity.Fenye;
import com.crm.entity.Student;
@Service
public class NetStudentServiceImpl implements NetStudentService {
  @Autowired
   private NetStudentMapper netStudentMapper;
  //��ʾ����
	public Fenye<Student> selectNetStudent(Fenye<Student> fenye) {
		List<Student> selectNetStudent = netStudentMapper.selectNetStudent(fenye);
		Integer selectNetStudentCount = netStudentMapper.selectNetStudentCount(fenye);
		fenye.setRows(selectNetStudent);
		fenye.setTotal(selectNetStudentCount);
		return fenye;
	}
// ���ѧ��
public Integer addStudent(Student student,Boolean tur) {
	if(tur!=true){
		student.setAskerId(null);
	}
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	student.setCreatTimes(sdf.format(date));
	Integer addStudent = netStudentMapper.addStudent(student);
	return addStudent;
}
//�鿴ʧЧѧ������
public List<Student> selectShiXiaoStudent(String askName) {
	return netStudentMapper.selectShiXiaoStudent(askName);
}
//�鿴������ѯʦ�µ�ѧ��
public List<Student> selectAllNetStudent() {
	return netStudentMapper.selectAllNetStudent();
}
//�޸�ѧ����Ϣ
public Integer updateNetStudent(Student student) {
	return netStudentMapper.updateNetStudent(student);
}
//�޸�ʧЧ
public Integer updateShixiaoStudent(Student student) {
	return netStudentMapper.updateShixiaoStudent(student);
}
//��ѯѧ������ѯʦ
public Student selectAsks(Integer id) {
	// TODO Auto-generated method stub
	return netStudentMapper.selectAsks(id);
}



}
