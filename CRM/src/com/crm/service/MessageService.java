package com.crm.service;

import java.util.List;

import com.crm.entity.Message;
import com.crm.entity.Users;

public interface MessageService {
	//�����û����Ʋ�ѯ�û�
		Users seleUsers(String Name);
		
		//�����ʾ��Ϣ
		Integer insertMessage(Message message);
		//��ѯ��ʾ��Ϣ
		List<Message> SelectMessage(Integer AskId);
		//ɾ����ʾ��Ϣ
		Integer DeleteMessage(Integer AskId);
}
