package com.crm.dao;

import java.util.List;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Network_trace;
import com.crm.entity.Student;
import com.crm.entity.Users;

public interface CounselorMapper {
	/**
	 * ��ѯѧ��������
	 * @param fenye
	 * @return
	 */
	Integer SelectCount(Fenye<Student> fenye);

	/**
	 *  ��ѯѧ������
	 * @param fenye
	 * @return
	 */
	List<Student> SelectStudent(Fenye<Student> fenye);

	/**
	 * ��Ӱݷõ�����
	 * @param fenye
	 * @return
	 */
	Integer InsertInfo(Network_trace network_trace);

	// 
	/**
	 * ��ѯ����׷��ѧ���� ������
	 * @param fenye
	 * @return
	 */
	Integer SelectNetworkTraceCount(Fenye<Network_trace> fenye);

	/**
	 * ��ѯ����׷��ѧ��������
	 * @param fenye
	 * @return
	 */
	List<Network_trace> SelectNetworkTrace(Fenye<Network_trace> fenye);

	/**
	 * ��ѯ����׷��,����ѧ����Ϣ�� ������
	 * @param fenye
	 * @return
	 */
	Integer SeleteNetwork_traceLogCount(Fenye<Network_trace> fenye);

	/**
	 * ��ѯ����׷��,����ѧ����Ϣ�� ����
	 * @param fenye
	 * @return
	 */
	List<Network_trace> SeleteNetwork_traceLog(Fenye<Network_trace> fenye);

	/**
	 * ɾ��׷�ټ�¼
	 * @param fenye
	 * @return
	 */
	Integer Delete(Integer n_id);

	/**
	 * �޸ĵ�¼��Ϣ
	 * @param fenye
	 * @return
	 */
	Integer UpdateUserInfo(Users users);

	/**
	 * �޸��û�����
	 * @param fenye
	 * @return
	 */
	Integer UpdateUserPassword(Users users);

	/**
	 * ����ID ��ѯ�û���Ϣ
	 * @param fenye
	 * @return
	 */
	Users selectUsersInfo(Integer uid);

	/**
	 * �޸�ѧ����Ϣ
	 * @param fenye
	 * @return
	 */
	Integer UpdateStudentInfo(Student student);

	/**
	 * ��ѯ��ѯʦ��Ϣ
	 * @param fenye
	 * @return
	 */
	Ask selectAskInfo(String LonginName);

	/**
	 * ��ѯ��ѯʦ����
	 * @param fenye
	 * @return
	 */
	Users selectUsersPassword(Users u);

}
