package com.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Message;
import com.crm.entity.Roles;
import com.crm.entity.Student;
import com.crm.service.MessageService;
import com.crm.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private Fenye<Student> fenye;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private MessageService messageService;

	// ��ҳ��ʾ����
	@RequestMapping(value = "/selectAllStudent", method = RequestMethod.POST)
	@ResponseBody
	public Fenye<Student> selectAllStudent(Integer page, Integer rows,
			Student student) {
		fenye.setPage((page - 1) * rows);
		fenye.setPageSize(rows);
		fenye.setStudent(student);
		studentService.selectAllStudent(fenye);
		List<Roles> selectRoles = studentService.selectRoles();
		return fenye;
	}

	// �鿴ѧ����Ϣ
	@RequestMapping(value = "/selectAsk", method = RequestMethod.POST)
	@ResponseBody
	public Student selectAsk(Integer id) {
		Student selectAsk = studentService.selectAsk(id);
		return selectAsk;
	}

	// ���ѧ��
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer addStudent(Student student,Boolean tur) {
		if (student.getSname().length() <= 0) {
			return -1;
		} else if (student.getAge()==null) {
			return -2;
		} else if (student.getPhone().length() != 11) {
			return -3;
		} else if (student.getSourceKeyWord().length()<=0) {
			return -4;
		} else if (!student.getQQ().matches("^\\w+@(\\w+\\.)+\\w+$")) {
			return -5;
		} else if (student.getWeiXin().length()<=0) {
			return -6;
		} else if (student.getContent().length()<=0) {
			return -7;
		} else if (student.getSex().equals("--��ѡ��--")) {
			return -8;
		}else if (student.getPerStatus().equals("--��ѡ��--")) {
			return -9;
		} else if (student.getStuStatus().equals("--��ѡ��--")) {
			return -10;
		}else if (student.getMsgSource().equals("--��ѡ��--")) {
			return -11;
		} else if (student.getSourceUrl().equals("--��ѡ��--")) {
			return -12;
		}else if (student.getIsBaoBei().equals("--��ѡ��--")) {
			return -13;
		} else if (student.getIsReturnVisit().equals("--��ѡ��--")) {
			return -14;
		}
		Integer addStudent = studentService.addStudent(student,tur);
		return addStudent;
	}

	// �޸�
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateStudent(Student student) {
		Integer updateStudent = studentService.updateStudent(student);
		return updateStudent;
	}

	// ��ѯ��ѯʦ���� �ж��Ƿ�ǩ��
	@RequestMapping(value = "/selectAskName", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectAskName() {
		List<Ask> selectAskName = studentService.selectAskName();
		return selectAskName;
	}

	/**
	 * 
	 * @param stuid ���е�ѧ��ID
	 * @param askid ��ѯʦid
	 * @return
	 */
	// �ֶ����䣨�����ѯʦ��
	@RequestMapping(value = "/addAskName", method = RequestMethod.POST)
	@ResponseBody
	public Integer addAskName(String stuid,Integer askid) {
		
		
		// �ָ�stuidȡ�����е�ѧ��Id��ӵ�list��������
		String[] split = stuid.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.parseInt(split[i]));
		}
		//��service����䷽��
		Integer addAskName = studentService.addAskName(list,askid);
		return addAskName;
	}

	// �Զ����䣨�����ѯʦ��
	@RequestMapping(value = "/addAskNames", method = RequestMethod.POST)
	@ResponseBody
	public Integer selectFenLiang(String askerId) {
		// ����ѧ��id��ѯ��δ�����ѧ��
		String[] split = askerId.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.parseInt(split[i]));
		}
		Integer selectFenLiang = studentService.selectFenLiang(list);
		return selectFenLiang;
	}

	// ��ѯ��ѯʦ����
	@RequestMapping(value = "/selectNames", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectNames() {
		List<Ask> selectNames = studentService.selectNames();
		return selectNames;
	}

	// �ѷ���
	@RequestMapping(value = "/selectYifenpei", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectYifenpei() {
		List<Student> selectYifenpei = studentService.selectYifenpei();
		return selectYifenpei;
	}

	// δ����
	@RequestMapping(value = "/selectWeifenpei", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectWeifenpei() {
		return studentService.selectWeifenpei();
	}

	// ����Ȩ�ز���ѯʦid
	@RequestMapping(value = "/selectWeightId", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectWeightId() {
		List<Ask> selectWeightId = studentService.selectWeightId();
		System.out.println(selectWeightId);
		return selectWeightId;
	}
	// ʧЧ
		@RequestMapping(value = "/updateShixiaoStudents", method = RequestMethod.POST)
		@ResponseBody
		public Integer updateShixiaoStudent(String Sname) {
			return studentService.updateShixiaoStudent(Sname);
		}
		 //��ѯʧЧѧ��
		@RequestMapping(value = "/selectshixiaostudents", method = RequestMethod.POST)
		@ResponseBody
	    List<Student> selectshixiaostudents(){
	    	return studentService.selectshixiaostudents();
	    }

}
