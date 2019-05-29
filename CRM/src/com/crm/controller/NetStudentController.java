package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dao.NetStudentMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Student;
import com.crm.service.NetStudentService;

@Controller
public class NetStudentController {
	@Autowired
	private NetStudentService netStudentService;
	@Autowired
	private NetStudentMapper netStudentMapper;
	@Autowired
	private Fenye<Student> fenye;

	// �鿴����������ѯʦ
	@RequestMapping(value = "/selectNetStudent", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Student> selectNetStudent(Integer page, Integer rows,
			String askName, Student student) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		Ask ask = new Ask();
		ask.setAskName(askName);
		fenye.setAsk(ask);
		fenye.setStudent(student);
		Fenye<Student> selectNetStudent = netStudentService
				.selectNetStudent(fenye);
		return fenye;
	}

	// ���ѧ��
	@RequestMapping(value = "/addNetStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer addStudent(Student student) {
		Ask selectAsk = netStudentMapper.selectAsk(student.getAskerId());
		student.setAskerId(selectAsk.getAskId());
		if (student.getSname().length() <= 0) {
			return -1;
		} else if (student.getSex().equals("--��ѡ��--")) {
			return -2;
		} else if (student.getAge() == null) {
			return -3;
		} else if (student.getPhone().length() != 11) {
			return -4;
		} else if (student.getStuStatus().equals("--��ѡ��--")) {
			return -5;
		} else if (student.getPerStatus().equals("--��ѡ��--")) {
			return -6;
		} else if (student.getMsgSource().equals("--��ѡ��--")) {
			return -7;
		} else if (student.getSourceUrl().equals("--��ѡ��--")) {
			return -8;
		}
		if (student.getSourceKeyWord().length() <= 0) {
			return -9;
		} else if (!student.getQQ().matches("^\\w+@(\\w+\\.)+\\w+$")) {
			return -10;
		}
		if (student.getWeiXin().length() <= 0) {
			return -11;
		} else if (student.getIsBaoBei().equals("--��ѡ��--")) {
			return -12;
		}
		if (student.getContent().length() <= 0) {
			return -13;
		} else if (student.getIsReturnVisit().equals("--��ѡ��--")) {
			return -14;
		}
		return netStudentService.addStudent(student);
	}

	// �鿴ʧЧѧ������
	@RequestMapping(value = "/selectShiXiaoStudent", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectShiXiaoStudent(String askName) {
		return netStudentService.selectShiXiaoStudent(askName);
	}

	// �鿴������ѯʦ�µ�ѧ��
	@RequestMapping(value = "/selectAllNetStudent", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectAllNetStudent() {
		return netStudentService.selectAllNetStudent();
	}

	// �޸�ѧ��
	@RequestMapping(value = "/updateNetStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateNetStudent(Student student) {
		return netStudentService.updateNetStudent(student);
	}

	// �޸�ʧЧ
	@RequestMapping(value = "/updateShixiaoStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateShixiaoStudent(Student student) {
		return netStudentService.updateShixiaoStudent(student);
	}

	// ��ѯѧ������ѯʦ
	@RequestMapping(value = "/selectAsks", method = RequestMethod.POST)
	@ResponseBody
	public Student selectAsks(Integer id) {
		return netStudentService.selectAsks(id);
	}

}
