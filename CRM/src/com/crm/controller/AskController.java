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

	// 显示所有咨询师
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

	// 查看角色
	@RequestMapping(value = "/selectA", method = RequestMethod.POST)
	@ResponseBody
	public List<Roles> selectA() {
		List<Roles> selectRoles = askService.selectRoles();
		return selectRoles;
	}

	// 显示所有网络咨询师(网络咨询师表)
	@RequestMapping(value = "/selectInternetAsk", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Ask> selectInternetAsk(Integer page, Integer rows) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		askService.selectInternetAsk(fenye);
		return fenye;
	}

	// 显示所有咨询师（咨询师表）
	@RequestMapping(value = "/selectAskTeacher", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Ask> selectAskTeacher(Integer page, Integer rows) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		askService.selectAskTeacher(fenye);
		return fenye;
	}

	// 修改
	@RequestMapping(value = "/updateAsk", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateAsk(Ask ask) {
		Integer updateAsk = askService.updateAsk(ask);
		return updateAsk;
	}

	// 查看网络咨询师下的学生
	@RequestMapping(value = "/selectStudent", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectStudent(Integer askId) {
		List<Student> selectStudent = askService.selectStudent(askId);
		return selectStudent;
	}

	// 签到
	@RequestMapping(value = "/updateQianDao", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateQianDao(Ask ask, HttpSession session) {
		// 签到时间
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		// 限制签到时间早上7点到8点之间
		String b = "07:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "08:40:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);

		sign_in.setUid(ask.getAskId());
		String str = sd.format(dat);
		String substring = str.substring(0, 10);// 截取时间字符串
		//根据用户名称查询用户id
		Users selectUsers = askMapper.selectUsers(ask.getAskName());
		sign_in.setUid(selectUsers.getUid());
		sign_in.setCreat_time(substring);
		// 查询用户当天签到了没有
		Sign_in selectSignByTime = sign_inService.selectSignByTime(sign_in);
		// 等1说明在该时间段可以签到
		if (sjd == 1) {
			if (selectSignByTime != null) {
				// 当天已经签到过了
				return 2;
			} else {
				sign_in.setLoginUserName(ask.getAskName());
				sign_in.setCreat_time(sd.format(dat));
				Integer i = sign_inService.addSign(sign_in);
				if (i > 0) {
					askService.updateQianDao(ask);
				}
				// 签到成功
				return i;
			}
		} else if (sjd == 2) {// 迟到
			if (selectSignByTime != null) {
				// 当天已经签到过了
				return 2;
			} else {
				// 签到成功，但是迟到
				sign_in.setLoginUserName(ask.getAskName());
				sign_in.setCreat_time(sd.format(dat));
				Integer i = sign_inService.addChidaoSign(sign_in);
				if (i > 0) {
					askService.updateQianDao(ask);
				}
				return -1;
			}
		} else if (sjd == 3) {
			// 上班时间还没到
			return 3;
		} else {

		}
		return null;
	}

	// 签退
	@RequestMapping(value = "/updateQianTui", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateQianTui(Ask ask,HttpSession session) {

		// 签退时间
		Date dat = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a1 = PdDate.strToDate(s.format(dat));
		// 限制签退时间晚上20点到21点之间
		String b = "20:00:00";
		Date b1 = PdDate.strToDate(b);
		String c = "21:00:00";
		Date c1 = PdDate.strToDate(c);
		Integer sjd = PdDate.sjd(a1, b1, c1);
		//根据用户名称查询用户id
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
				// 签退成功
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("已签退");
				selectSign.setEnd_time(sd.format(dat));
				Integer i = sign_inService.updateSign(selectSign);
				if(i>0){
					askService.updateQianTui(ask);
				}
				return 1;
			} else if (sjd == 2) {
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("已签退");
				selectSign.setEnd_time(sd.format(dat));
				Integer i = sign_inService.updateSign(selectSign);
				if(i>0){
					askService.updateQianTui(ask);
				}
				return 2;
			} else if (sjd == 3) {
				// 签退成功，但是早退
				sign_in.setLoginUserName(ask.getAskName());
				selectSign.setEndState("早退");
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

	// 权重排序
	@RequestMapping(value = "/paixu", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> paixu(Ask ask) {
		List<Ask> paixun = askService.paixun(ask);
		return paixun;
	}

	/**
	 * 把未签到的咨询师修改为未签到状态
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
