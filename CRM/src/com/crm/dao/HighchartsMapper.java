package com.crm.dao;

public interface HighchartsMapper {
	//��ԃ����ǩ������
	Integer selectHighchartsZC(Integer uid);
	//���˴���
	Integer selectHighchartsZT(Integer uid);
	//�ٵ�����
	Integer selectHighchartsCD(Integer uid);
}
