package com.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Sign_in;
import com.crm.filter.PdDate;
import com.crm.service.Sign_inService;

@Controller
public class Sign_inController {
	@Autowired
	private Sign_inService sign_inService;
	@Autowired
	private Sign_in sign_in;

	/**
	 * ǩ��
	 * 
	 * @param sign_in
	 * @return
	 */
	@RequestMapping(value = "/addSign", method = RequestMethod.POST)
	@ResponseBody
	public Integer addSign(HttpSession session) {
		//ǩ��ʱ��
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		//����ǩ��ʱ������7�㵽8��֮��
		String b = "07:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "08:40:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);

		Integer uid = (Integer) session.getAttribute("uid");
		String LoginUserName = (String)session.getAttribute("LoginUserName");
		sign_in.setUid(uid);
		String str = sd.format(dat);
		String substring = str.substring(0, 10);//��ȡʱ���ַ���

		sign_in.setCreat_time(substring);
		//��ѯ�û�����ǩ����û��
		Sign_in selectSignByTime = sign_inService.selectSignByTime(sign_in);
		//��1˵���ڸ�ʱ��ο���ǩ��
		if (sjd == 1) {
			if (selectSignByTime != null) {
				// �����Ѿ�ǩ������
				return 2;
			} else {
				sign_in.setLoginUserName(LoginUserName);
				sign_in.setCreat_time(sd.format(dat));
				Integer i = sign_inService.addSign(sign_in);
				// ǩ���ɹ�
				return i;
			}
		} else if (sjd == 2) {//�ٵ�
			if (selectSignByTime != null) {
				// �����Ѿ�ǩ������
				return 2;
			} else {
				// ǩ���ɹ������ǳٵ�
				sign_in.setLoginUserName(LoginUserName);
				sign_in.setCreat_time(sd.format(dat));
				sign_inService.addChidaoSign(sign_in);
				return -1;
			}
		} else if (sjd == 3) {
			// �ϰ�ʱ�仹û��
			return 3;
		} else {

		}
		return null;
	}

	/**
	 * ǩ��
	 * 
	 * @param sign_in
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateSign", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateSign(HttpSession session) {
		//ǩ��ʱ��
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		//����ǩ��ʱ������20�㵽21��֮��
		String b = "20:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "21:00:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);

		Integer uid = (Integer) session.getAttribute("uid");
		String LoginUserName = (String)session.getAttribute("LoginUserName");
		sign_in.setUid(uid);
		String str = sd.format(dat);
		String substring = str.substring(0, 10);

		sign_in.setCreat_time(substring);

		Sign_in selectSign = sign_inService.selectSign(sign_in);
		if (selectSign.getEndState() == null) {
			if (sjd == 1) {
				// ǩ�˳ɹ�
				sign_in.setLoginUserName(LoginUserName);
				selectSign.setEndState("��ǩ��");
				selectSign.setEnd_time(sd.format(dat));
				sign_inService.updateSign(selectSign);
				return 1;
			} else if (sjd == 2) {
				sign_in.setLoginUserName(LoginUserName);
				selectSign.setEndState("��ǩ��");
				selectSign.setEnd_time(sd.format(dat));
				sign_inService.updateSign(selectSign);
				return 2;
			} else if (sjd == 3) {
				// ǩ�˳ɹ�����������
				sign_in.setLoginUserName(LoginUserName);
				selectSign.setEndState("����");
				selectSign.setEnd_time(sd.format(dat));
				sign_inService.updateSign(selectSign);
				return 3;
			}
		} else {
			return 0;
		}
		return null;
	}

	/**
	 * ��ѯ�ҵĿ���
	 * 
	 * @param sign_in
	 * @return
	 */
	@RequestMapping(value = "/selectMySign", method = RequestMethod.POST)
	@ResponseBody
	public List<Sign_in> selectMySign(HttpSession session) {
		// TODO Auto-generated method stub
		Integer uid = (Integer) session.getAttribute("uid");
		return sign_inService.selectMySign(uid);

	}

}
