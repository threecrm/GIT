package com.crm.dao;

import java.util.List;

public interface HighchartsMapper {
	// ��ԃ����ǩ��������
	Integer selectHighchartsZC(String date);

	// ��ѯ��ѯʦ����
	Integer selectHighchartsZS();

	// ��ѯ�м����ٵ�
	Integer selectHighchartsCD(String date);
	
}
