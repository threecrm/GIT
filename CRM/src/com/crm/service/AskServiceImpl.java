package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.AskMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Student;
@Service
public class AskServiceImpl implements AskService{
	@Autowired
	private AskMapper askMapper;
	//��ʾ����
	public Fenye<Ask> selectAskAll(Fenye<Ask> fenye) {
		List<Ask> selectAsk = askMapper.selectAsk(fenye);
		Integer selectCountAsk = askMapper.selectCountAsk(fenye);
		fenye.setRows(selectAsk);
		fenye.setTotal(selectCountAsk);
		return fenye;
	}
  //��ѯ������ѯʦ
	public Fenye<Ask> selectInternetAsk(Fenye<Ask> fenye) {
		List<Ask> selectInternetAsk = askMapper.selectInternetAsk(fenye);
		fenye.setRows(selectInternetAsk);
		return fenye;
	}
  //��ѯ��ѯʦ
	public Fenye<Ask> selectAskTeacher(Fenye<Ask> fenye) {
		List<Ask> selectAskTeacher = askMapper.selectAskTeacher(fenye);
		fenye.setRows(selectAskTeacher);
		return fenye;
	}
   //��ѯ��ɫ
	public List<Roles> selectRoles() {
		List<Roles> selectRoles = askMapper.selectRoles();
		return selectRoles;
	}
	//�޸�������ѯʦ
	public Integer updateAsk(Ask ask) {
		
		return askMapper.updateAsk(ask);
	}
	//�鿴������ѯʦ
	public List<Student> selectStudent(Integer askId) {
		List<Student> selectStudent = askMapper.selectStudent(askId);
		return selectStudent;
	}
	//ǩ��
	public Integer updateQianDao(Ask ask) {
		Integer updateQianDao = askMapper.updateQianDao(ask);
		return updateQianDao;
	}
	//ǩ��
	public Integer updateQianTui(Ask ask) {
		Integer updateQianTui = askMapper.updateQianTui(ask);
		return updateQianTui;
	}
	//Ȩ������
	public List<Ask> paixun(Ask ask) {
		return askMapper.paixun(ask);
	}


}
