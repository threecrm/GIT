package com.crm.service;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Roles;
import com.crm.entity.Student;

public interface StudentService {
	// ��ҳ��ʾѧ��
	public Fenye selectAllStudent(Fenye<Student> fenye);

	// ��ѯ��ɫ
	public List<Roles> selectRoles();

	// ��ʾ����ѧ����ѯʦ
	public Student selectAsk(Integer id);

	// ����ѧ��
	public Integer addStudent(Student student,Boolean tur);

	// ɾ��ѧ��
	public Integer deleteStudent(Integer id);

	// �޸�ѧ��
	public Integer updateStudent(Student student);

	// ��ѯ��ѯʦ����
	public List<Ask> selectAskName();

	// �ֶ����䣨������ѯʦ��
	public Integer addAskName(Student student);

	// �Զ����䣨������ѯʦ��
	public Integer addAskNames(Student student);

	// ��ѯ��ѯʦ�µ�����
	public List<Ask> selectNames();

	// �ѷ���
	public List<Student> selectYifenpei();

	// δ����
	public List<Student> selectWeifenpei();

	// ����Ȩ�ز���ѯʦid
	public List<Ask> selectWeightId();

	/**
	 * ����
	 */
	Integer selectFenLiang(List<Integer> list);
}