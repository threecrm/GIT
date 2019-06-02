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

	// 分页显示所有
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

	// 查看学生信息
	@RequestMapping(value = "/selectAsk", method = RequestMethod.POST)
	@ResponseBody
	public Student selectAsk(Integer id) {
		Student selectAsk = studentService.selectAsk(id);
		return selectAsk;
	}

	// 添加学生
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
		} else if (student.getSex().equals("--请选择--")) {
			return -8;
		}else if (student.getPerStatus().equals("--请选择--")) {
			return -9;
		} else if (student.getStuStatus().equals("--请选择--")) {
			return -10;
		}else if (student.getMsgSource().equals("--请选择--")) {
			return -11;
		} else if (student.getSourceUrl().equals("--请选择--")) {
			return -12;
		}else if (student.getIsBaoBei().equals("--请选择--")) {
			return -13;
		} else if (student.getIsReturnVisit().equals("--请选择--")) {
			return -14;
		}
		Integer addStudent = studentService.addStudent(student,tur);
		return addStudent;
	}

	// 修改
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	@ResponseBody
	public Integer updateStudent(Student student) {
		Integer updateStudent = studentService.updateStudent(student);
		return updateStudent;
	}

	// 查询咨询师姓名 判断是否签到
	@RequestMapping(value = "/selectAskName", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectAskName() {
		List<Ask> selectAskName = studentService.selectAskName();
		return selectAskName;
	}

	/**
	 * 
	 * @param stuid 所有的学生ID
	 * @param askid 咨询师id
	 * @return
	 */
	// 手动分配（添加咨询师）
	@RequestMapping(value = "/addAskName", method = RequestMethod.POST)
	@ResponseBody
	public Integer addAskName(String stuid,Integer askid) {
		
		
		// 分割stuid取出所有的学生Id添加到list集合里面
		String[] split = stuid.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.parseInt(split[i]));
		}
		//调service层分配方法
		Integer addAskName = studentService.addAskName(list,askid);
		return addAskName;
	}

	// 自动分配（添加咨询师）
	@RequestMapping(value = "/addAskNames", method = RequestMethod.POST)
	@ResponseBody
	public Integer selectFenLiang(String askerId) {
		// 根据学生id查询出未分配的学生
		String[] split = askerId.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.parseInt(split[i]));
		}
		Integer selectFenLiang = studentService.selectFenLiang(list);
		return selectFenLiang;
	}

	// 查询咨询师姓名
	@RequestMapping(value = "/selectNames", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectNames() {
		List<Ask> selectNames = studentService.selectNames();
		return selectNames;
	}

	// 已分配
	@RequestMapping(value = "/selectYifenpei", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectYifenpei() {
		List<Student> selectYifenpei = studentService.selectYifenpei();
		return selectYifenpei;
	}

	// 未分配
	@RequestMapping(value = "/selectWeifenpei", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> selectWeifenpei() {
		return studentService.selectWeifenpei();
	}

	// 根据权重查咨询师id
	@RequestMapping(value = "/selectWeightId", method = RequestMethod.POST)
	@ResponseBody
	public List<Ask> selectWeightId() {
		List<Ask> selectWeightId = studentService.selectWeightId();
		System.out.println(selectWeightId);
		return selectWeightId;
	}
	// 失效
		@RequestMapping(value = "/updateShixiaoStudents", method = RequestMethod.POST)
		@ResponseBody
		public Integer updateShixiaoStudent(String Sname) {
			return studentService.updateShixiaoStudent(Sname);
		}
		 //查询失效学生
		@RequestMapping(value = "/selectshixiaostudents", method = RequestMethod.POST)
		@ResponseBody
	    List<Student> selectshixiaostudents(){
	    	return studentService.selectshixiaostudents();
	    }

}
