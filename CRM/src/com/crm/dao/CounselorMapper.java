package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface CounselorMapper {
	 //��ѯѧ��������
		Integer SelectCount(Fenye <Student> fenye);
		//��ѯѧ������
		List<Student> SelectStudent(Fenye <Student> fenye);
		
		//��Ӱݷõ�����
		Integer InsertInfo(Network_trace network_trace);
		
		//��ѯ����׷��ѧ���� ������
		Integer SelectNetworkTraceCount(Fenye<Network_trace> fenye);
		//��ѯ����׷��ѧ��������
		List<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye);
		
	///////
		//��ѯ����׷��,����ѧ����Ϣ�� ������
		Integer SeleteNetwork_traceLogCount(Fenye<Network_trace> fenye);
		//��ѯ����׷��,����ѧ����Ϣ�� ����
		List<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye);
	/////
		
	//ɾ��׷�ټ�¼
	Integer  Delete(Integer n_id);
	
	//�޸ĵ�¼��Ϣ
    Integer UpdateUserInfo(Users users);
    //�޸��û�����
    Integer UpdateUserPassword(Users users);
	
	//����ID ��ѯ�û���Ϣ
	Users selectUsersInfo(Integer uid);
	
	//�޸�ѧ����Ϣ
	 Integer UpdateStudentInfo(Student student);
	
	//��ѯ��ѯʦ��Ϣ
	 Ask selectAskInfo(String LonginName);
	
	 //��ѯ��ѯʦ����
	 Users selectUsersPassword(Users u);
	 
	 
}
