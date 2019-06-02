package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.AskMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Sign_in;
import com.crm.entity.Student;

@Service
public class AskServiceImpl implements AskService {
	@Autowired
	private AskMapper askMapper;

	// ��ʾ����
	public Fenye<Ask> selectAskAll(Fenye<Ask> fenye) {
		List<Ask> selectAsk = askMapper.selectAsk(fenye);
		Integer selectCountAsk = askMapper.selectCountAsk(fenye);
		fenye.setRows(selectAsk);
		fenye.setTotal(selectCountAsk);
		return fenye;
	}

	// ��ѯ������ѯʦ
	public Fenye<Ask> selectInternetAsk(Fenye<Ask> fenye) {
		List<Ask> selectInternetAsk = askMapper.selectInternetAsk(fenye);
		Integer selectNetAskCount = askMapper.selectNetAskCount(fenye);
		fenye.setRows(selectInternetAsk);
		fenye.setTotal(selectNetAskCount);
		return fenye;
	}

	// ��ѯ��ѯʦ
	public Fenye<Ask> selectAskTeacher(Fenye<Ask> fenye) {
		List<Ask> selectAskTeacher = askMapper.selectAskTeacher(fenye);
		Integer selectAskCount = askMapper.selectAskCount(fenye);
		fenye.setRows(selectAskTeacher);
		fenye.setTotal(selectAskCount);
		return fenye;
	}

	// ��ѯ��ɫ
	public List<Roles> selectRoles() {
		List<Roles> selectRoles = askMapper.selectRoles();
		return selectRoles;
	}

	// �޸�������ѯʦ
	public Integer updateAsk(Ask ask) {

		return askMapper.updateAsk(ask);
	}

	// �鿴������ѯʦ
	public List<Student> selectStudent(Integer askId) {
		List<Student> selectStudent = askMapper.selectStudent(askId);
		return selectStudent;
	}

	// ǩ��
	public Integer updateQianDao(Ask ask) {
		Integer updateQianDao = askMapper.updateQianDao(ask);
		return updateQianDao;
	}

	// ǩ��
	public Integer updateQianTui(Ask ask) {
		Integer updateQianTui = askMapper.updateQianTui(ask);
		return updateQianTui;
	}

	// Ȩ������
	public List<Ask> paixun(Ask ask) {
		return askMapper.paixun(ask);
	}
	/**
	 * ��ѯ����ǩ����Ա��
	 */
	/*public List<Sign_in> selectSignByTime(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return askMapper.selectSignByTime(sign_in);
	}*/
	
	
    /**
      *  һ��ǩ�� ǩ����
    */
	@Override
	public Integer allQiantuis(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return askMapper.allQiantuis(sign_in);
	}

	/**
	 * һ��ǩ��
	 */
	@Override
	public Integer allQiantui(Ask ask) {
		// TODO Auto-generated method stub
		return askMapper.allQiantui(ask);
	}
	/**
	 * ��δǩ������ѯʦ�޸�Ϊδǩ��״̬
	 * @return
	 */
	@Override
	public Integer updateASkStatue() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
		//��ѯδǩ������ѯʦ
		List<Ask> selectAskStatue = askMapper.selectAskStatue(sd.format(date));
		Integer updateAskStatue = null;
		for (int i = 0; i < selectAskStatue.size(); i++) {
			Ask ask = new Ask();
			ask.setAskId(selectAskStatue.get(i).getAskId());
			//ѭ���޸�
			updateAskStatue = askMapper.updateAskStatue(ask);
		}
		return updateAskStatue;
	}

	@Override
	public Integer updateFenLiang(Ask ask) {
		Integer updateFenLiang = askMapper.updateFenLiang(ask);
		return updateFenLiang;
	}

}
