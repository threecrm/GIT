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

	// 显示所有
	public Fenye<Ask> selectAskAll(Fenye<Ask> fenye) {
		List<Ask> selectAsk = askMapper.selectAsk(fenye);
		Integer selectCountAsk = askMapper.selectCountAsk(fenye);
		fenye.setRows(selectAsk);
		fenye.setTotal(selectCountAsk);
		return fenye;
	}

	// 查询网络咨询师
	public Fenye<Ask> selectInternetAsk(Fenye<Ask> fenye) {
		List<Ask> selectInternetAsk = askMapper.selectInternetAsk(fenye);
		Integer selectNetAskCount = askMapper.selectNetAskCount(fenye);
		fenye.setRows(selectInternetAsk);
		fenye.setTotal(selectNetAskCount);
		return fenye;
	}

	// 查询咨询师
	public Fenye<Ask> selectAskTeacher(Fenye<Ask> fenye) {
		List<Ask> selectAskTeacher = askMapper.selectAskTeacher(fenye);
		Integer selectAskCount = askMapper.selectAskCount(fenye);
		fenye.setRows(selectAskTeacher);
		fenye.setTotal(selectAskCount);
		return fenye;
	}

	// 查询角色
	public List<Roles> selectRoles() {
		List<Roles> selectRoles = askMapper.selectRoles();
		return selectRoles;
	}

	// 修改网络咨询师
	public Integer updateAsk(Ask ask) {

		return askMapper.updateAsk(ask);
	}

	// 查看网络咨询师
	public List<Student> selectStudent(Integer askId) {
		List<Student> selectStudent = askMapper.selectStudent(askId);
		return selectStudent;
	}

	// 签到
	public Integer updateQianDao(Ask ask) {
		Integer updateQianDao = askMapper.updateQianDao(ask);
		return updateQianDao;
	}

	// 签退
	public Integer updateQianTui(Ask ask) {
		Integer updateQianTui = askMapper.updateQianTui(ask);
		return updateQianTui;
	}

	// 权重排序
	public List<Ask> paixun(Ask ask) {
		return askMapper.paixun(ask);
	}
	/**
	 * 查询当天签到的员工
	 */
	/*public List<Sign_in> selectSignByTime(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return askMapper.selectSignByTime(sign_in);
	}*/
	
	
    /**
      *  一键签退 签到表
    */
	@Override
	public Integer allQiantuis(Sign_in sign_in) {
		// TODO Auto-generated method stub
		return askMapper.allQiantuis(sign_in);
	}

	/**
	 * 一键签退
	 */
	@Override
	public Integer allQiantui(Ask ask) {
		// TODO Auto-generated method stub
		return askMapper.allQiantui(ask);
	}
	/**
	 * 把未签到的咨询师修改为未签到状态
	 * @return
	 */
	@Override
	public Integer updateASkStatue() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
		//查询未签到的咨询师
		List<Ask> selectAskStatue = askMapper.selectAskStatue(sd.format(date));
		Integer updateAskStatue = null;
		for (int i = 0; i < selectAskStatue.size(); i++) {
			Ask ask = new Ask();
			ask.setAskId(selectAskStatue.get(i).getAskId());
			//循环修改
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
