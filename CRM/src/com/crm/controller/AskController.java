package com.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dao.AskMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.Sign_in;
import com.crm.entity.Student;
import com.crm.entity.Users;
import com.crm.filter.PdDate;
import com.crm.service.AskService;
import com.crm.service.Sign_inService;

@Controller
public class AskController {
	@Autowired
	private AskService askService;
	@Autowired
	private Fenye<Ask> fenye;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private Sign_in sign_in;
	@Autowired
	private Sign_inService sign_inService;
	@Autowired
	private AskMapper askMapper;

	// ��ʾ������ѯʦ
	@RequestMapping(value = "/selectAskAll", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Ask> selectAskAll(Integer page, Integer rows, Ask ask) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		fenye.setAsk(ask);
		askService.selectAskAll(fenye);
		List<Roles> selectRoles = askService.selectRoles();
		return fenye;
	}

	// �鿴��ɫ
	@RequestMapping(value = "/selectA", method = RequestMethod.POST)
	@ResponseBody
	public List<Roles> selectA() {
		List<Roles> selectRoles = askService.selectRoles();
		return selectRoles;
	}

	// ��ʾ����������ѯʦ(������ѯʦ��)
	@RequestMapping(value = "/selectInternetAsk", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Ask> selectInternetAsk(Integer page, Integer rows) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		askService.selectInternetAsk(fenye);
		return fenye;
	}

	// ��ʾ������ѯʦ����ѯʦ��
	@RequestMapping(value = "/selectAskTeacher", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Ask> selectAskTeacher(Integer page, Integer rows) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		askService.selectAskTeacher(fenye);
		return fenye;
	}

	// �޸�
	@RequestMapping(value = "/updateAsk", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateAsk(Ask ask) {
		Integer updateAsk = askService.updateAsk(ask);
		return updateAsk;
	}

	// �鿴������ѯʦ�µ�ѧ��
	@RequestMapping(value = "/selectStudent", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectStudent(Integer askId) {
		List<Student> selectStudent = askService.selectStudent(askId);
		return selectStudent;
	}

	// ǩ��
	@RequestMapping(value = "/updateQianDao", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateQianDao(Ask ask, HttpSession session) {
		// ǩ��ʱ��
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		// ����ǩ��ʱ������7�㵽8��֮��
		String b = "07:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "08:40:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);

		sign_in.setUid(ask.getAskId());
		String str = sd.format(dat);
		String substring = str.substring(0, 10);// ��ȡʱ���ַ���
		//�����û����Ʋ�ѯ�û�id
		Users selectUsers = askMapper.selectUsers(ask.getAskName());
		sign_in.setUid(selectUsers.getUid());
		sign_in.setCreat_time(substring);
		// ��ѯ�û�����ǩ����û��
		Sign_in selectSignByTime = sign_inService.selectSignByTime(sign_in);
		// ��1˵���ڸ�ʱ��ο���ǩ��
		if (sjd == 1) {
			if (selectSignByTime != null) {
				// �����Ѿ�ǩ������
				return 2;
			} else {
				sign_in.setLoginUserName(ask.getAskName());
				sign_in.setCreat_time(sd.format(dat));
				Integer i = sign_inService.addSign(sign_in);
				if (i > 0) {
					askService.updateQianDao(ask);
				}
				// ǩ���ɹ�
				return i;
			}
		} else if (sjd == 2) {// �ٵ�
			if (selectSignByTime != null) {
				// �����Ѿ�ǩ������
				return 2;
			} else {
				// ǩ���ɹ������ǳٵ�
				sign_in.setLoginUserName(ask.getAskName());
				sign_in.setCreat_time(sd.format(dat));
				Integer i = sign_inService.addChidaoSign(sign_in);
				if (i > 0) {
					askService.updateQianDao(ask);
				}
				return -1;
			}
		} else if (sjd == 3) {
			// �ϰ�ʱ�仹û��
			return 3;
		} else {

		}
		return null;
	}

	// ǩ��
	@RequestMapping(value = "/updateQianTui", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateQianTui(Ask ask,HttpSession session) {

		// ǩ��ʱ��
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		// ����ǩ��ʱ������20�㵽21��֮��
		String b = "20:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "21:00:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);
		//�����û����Ʋ�ѯ�û�id
		Users selectUsers = askMapper.selectUsers(ask.getAskName());
		sign_in.setUid(selectUsers.getUid());
		String str = sd.format(dat);
		String substring = str.substring(0, 10);

		sign_in.setCreat_time(substring);
		Sign_in selectSign = sign_inService.selectSign(sign_in);
		if(selectSign==null){
			return -1;
		}
		if (selectSign.getEndState() == null) {
			if (sjd == 1) {
				// ǩ�˳ɹ�
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("��ǩ��");
				selectSign.setEnd_time(sd.format(dat));
				Integer i = sign_inService.updateSign(selectSign);
				if(i>0){
					askService.updateQianTui(ask);
				}
				return 1;
			} else if (sjd == 2) {
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("��ǩ��");
				selectSign.setEnd_time(sd.format(dat));
				Integer i = sign_inService.updateSign(selectSign);
				if(i>0){
					askService.updateQianTui(ask);
				}
				return 2;
			} else if (sjd == 3) {
				// ǩ�˳ɹ�����������
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("����");
				selectSign.setEnd_time(sd.format(dat));
				Integer i = sign_inService.updateSign(selectSign);
				if(i>0){
					askService.updateQianTui(ask);
				}
				return 3;
			}
		} else {
			return 0;
		}

		return null;
	}

	// Ȩ������
	@RequestMapping(value = "/paixu", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> paixu(Ask ask) {
		List<Ask> paixun = askService.paixun(ask);
		return paixun;
	}

	/**
	 * ��δǩ������ѯʦ�޸�Ϊδǩ��״̬
	 * @return
	 */
	@RequestMapping(value = "/updateAskStatue", method = RequestMethod.POST)
	@ResponseBody
	public Boolean updateAskStatue() {
		Integer updateASkStatue = askService.updateASkStatue();
		if(updateASkStatue==null){
			return true;
		}else{
			return false;
		}
	}

}
