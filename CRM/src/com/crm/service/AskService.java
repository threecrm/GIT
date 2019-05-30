package com.crm.service;
import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;
import com.crm.entity.Users;
public interface AskService {
	//��ʾ������ѯʦ
	public Fenye<Ask> selectAskAll(Fenye<Ask> fenye);	
	//��ѯ������ѯʦ
	public Fenye<Ask> selectInternetAsk(Fenye<Ask> fenye);
	//��ѯ��ѯʦ
	public Fenye<Ask> selectAskTeacher(Fenye<Ask> fenye);
	//��ѯ��ɫ
	public List<Roles> selectRoles();
     //�޸�
	 public Integer updateAsk(Ask ask);
	 //��ѯ����ѧ��
	public List<Student> selectStudent(Integer askId);
	//ǩ��
	public Integer updateQianDao(Ask ask);
	//ǩ��
	 public	Integer updateQianTui(Ask ask);
	//Ȩ������
	public	List<Ask> paixun(Ask ask);
	/**
	 * һ��ǩ��
	 * @param ask
	 * @return
	 */
	Integer allQiantui(Ask ask);
	/**
	 * ��δǩ������ѯʦ�޸ĳ�δǩ��״̬
	 * @return
	 */
	Integer updateASkStatue();
}
