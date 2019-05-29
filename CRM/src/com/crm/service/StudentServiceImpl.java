package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.StudentMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Roles;
import com.crm.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	public Fenye<Student> selectAllStudent(Fenye<Student> fenye) {
		List<Student> selectStudentAll = studentMapper.selectStudentAll(fenye);
		Integer countStudent = studentMapper.countStudent(fenye);
		fenye.setTotal(countStudent);
		fenye.setRows(selectStudentAll);
		return fenye;
	}

	// 查询角色
	public List<Roles> selectRoles() {
		List<Roles> selectRoles = studentMapper.selectRoles();
		return selectRoles;
	}

	public Student selectAsk(Integer id) {
		Student selectAsk = studentMapper.selectAsk(id);
		return selectAsk;
	}

	public Integer addStudent(Student student, Boolean tur) {
		
		if (tur == true) {// true自动分配
			// 查询出所有咨询师
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Ask> selectAskAll = studentMapper.selectAskAll(sdf.format(date));
			Integer Max = 100;
			Integer selectAskStu = null;
			for (int i = 0; i < selectAskAll.size(); i++) {
				selectAskStu = studentMapper.selectAskStu(sdf.format(date),selectAskAll.get(i).getAskId());
				if (selectAskStu == null) {
					selectAskStu = 0;
				}
				// 谁的学生最少谁优先添加
				if (selectAskStu < Max) {
					Max = selectAskStu;
					student.setAskerId(selectAskAll.get(i).getAskId());
					// 学生相等比权重
				}
			}
		}
		Integer addStudent = studentMapper.addStudent(student);
		return addStudent;
	}

	public Integer deleteStudent(Integer id) {
		Integer deleteStudent = studentMapper.deleteStudent(id);
		return deleteStudent;
	}

	public Integer updateStudent(Student student) {
		Integer updateStudent = studentMapper.updateStudent(student);
		return updateStudent;
	}

	public List<Ask> selectAskName() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		List<Ask> selectAskName = studentMapper.selectAskName(sdf.format(date));
		return selectAskName;
	}

	public Integer addAskName(Student student) {
		Integer i = studentMapper.addAskName(student);
		return i;
	}

	public List<Ask> selectNames() {
		return studentMapper.selectNames();
	}

	public List<Student> selectYifenpei() {
		return studentMapper.selectYifenpei();
	}

	public List<Student> selectWeifenpei() {
		return studentMapper.selectWeifenpei();
	}

	public Integer addAskNames(Student student) {
		Integer addAskNames = studentMapper.addAskNames(student);
		return addAskNames;
	}

	public List<Ask> selectWeightId() {

		return studentMapper.selectWeightId();
	}

	public Integer selectFenLiang(List<Integer> list) {
		//根据选择的学生查询出所有未分配的学生
		List<Student> selecStudent = studentMapper.selecStudent(list);
		Integer updateStudentAsk =null;
		if(selecStudent.size()==0){
			return -1;
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		//查询出所有的当天已经签到的咨询师
		List<Ask> selectAskAll = studentMapper.selectAskAll(sdf.format(date));
		for (int y = 0; y < selecStudent.size(); y++) {
			Integer Max = 100;
			Student student = new Student();
			student.setSid(selecStudent.get(y).getSid());
			for (int i = 0; i < selectAskAll.size(); i++) {
				//查询出已签到的咨询师有多少学生个数
				Integer selectAskStu = studentMapper.selectAskStu(sdf.format(date),selectAskAll.get(i).getAskId());
				//没有为零
				if (selectAskStu == null) {
					selectAskStu=0;
				}
				//循环查出最小的
				if (selectAskStu < Max) {
					Max = selectAskStu;
					student.setAskerId(selectAskAll.get(i).getAskId());
				} 
			}
			//修改学生的咨询师Id
			updateStudentAsk = studentMapper.updateStudentAsk(student);
		}
		return updateStudentAsk;
	}
}
