package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;

public interface StudentMapper {
	// ��ѯ����ѧ��
	List<Student> selectStudentAll(Fenye<Student> fenye);

	// ��ѯѧ������
	Integer countStudent(Fenye fenye);

	// ��ѯ��ɫ
	List<Roles> selectRoles();

	// ��ѯѧ������ѯʦ
	Student selectAsk(Integer id);

	// ���ѧ����Ϣ
	Integer addStudent(Student student);

	// ɾ��ѧ��
	Integer deleteStudent(Integer id);

	// �޸�ѧ��
	Integer updateStudent(Student student);

	// ��ѯ��ѯʦ�������ж��Ƿ�ǩ����
	List<Ask> selectAskName(String date);

	// �ֶ����䣨���������ѯʦ��
	Integer addAskName(Student student);

	// �Զ����䣨���������ѯʦ��
	Integer addAskNames(Student student);

	// ��ѯ��ѯʦ�µ�����
	List<Ask> selectNames();

	// �ѷ���
	List<Student> selectYifenpei();

	// δ����
	List<Student> selectWeifenpei();

	// ����Ȩ�ز���ѯʦid
	List<Ask> selectWeightId();

	/**
	 * ����id��ѯδ�����ѧ��
	 */
	List<Student> selecStudent(List<Integer> list);
	/*������ѯʦid��ѯ��ѯʦ�ж���ѧ��*/
	Integer selectAskStu(String date,Integer askid);
	/**
	 * ��ѯ������ѯʦ������ȡ����ѯʦId
	 * @return
	 */
	List<Ask> selectAskAll(String date);
	//����ѧ��
	Integer updateStudentAsk(Student student);
}
