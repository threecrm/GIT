package com.crm.service;


import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface CounselorService {

	//��ѯѧ��
		Fenye<Student> SelectStudent(Fenye<Student> studentfenye);
		
		//���׷������
		Integer Insert(Network_trace network_trace);
		
		//��ѯ׷�ٱ�����
		Fenye<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye);
	
		//��ѯ����׷��,����ѧ����Ϣ�� ����
	    Fenye<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye);
		/////
		
        //ɾ��׷�ټ�¼
	   Integer Delete(Integer n_id);
	   
	   //�޸ĵ�¼��Ϣ
		Integer UpdateUserInfo(Users users);
		
		//�޸��û�����
		Integer UpdateUserPassword(Users users);
		
		//��ѯ�û���Ϣ
		Users selectUsersInfo(Integer uid);
		
		//�޸�ѧ����Ϣ
		 Integer UpdateStudentInfo(Student student);
		 
		// ��ѯ��ѯʦ��Ϣ
		 Ask selectAskInfo(String LonginName);
		 
		// ��ѯ�û�����
		Users selectUsersPassword(Users u);
}
