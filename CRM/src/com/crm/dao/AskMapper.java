package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface AskMapper {
	//��ҳ��ʾ��ѯʦ����
	List<Ask> selectAsk(Fenye<Ask> fenye);
	//��ѯ������ѯʦ����
	Integer selectCountAsk(Fenye<Ask> fenye);
	//��ѯ������ѯʦ
	List<Ask> selectInternetAsk(Fenye<Ask> fenye);
	//��ѯ��ѯʦ
	List<Ask> selectAskTeacher(Fenye<Ask> fenye);	
	//��ѯ��ɫ
	List<Roles> selectRoles();
	//�޸�������ѯʦ
	Integer updateAsk(Ask ask);
	//��ѯ����ѧ��
	List<Student> selectStudent(Integer askId);
	//ǩ��
	Integer updateQianDao(Ask ask);
	//ǩ��
	Integer updateQianTui(Ask ask);
	//Ȩ������
	List<Ask> paixun(Ask ask);
	//�����û����Ʋ�ѯ�û�
	Users selectUsers(String longinName);
	/**
	 * ��ѯ������δǩ������ѯʦ
	 * @param ask
	 * @return
	 */
	List<Ask> selectAskStatue(String date);
	/**
	 * δǩ������ѯʦ�޸�δǩ��
	 * @param ask
	 * @return
	 */
	Integer updateAskStatue(Ask ask);

}
