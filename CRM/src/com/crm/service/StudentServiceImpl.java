package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.MessageMapper;
import com.crm.dao.StudentMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.GenZong;
import com.crm.entity.Message;
import com.crm.entity.Roles;
import com.crm.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired  
	private StudentMapper studentMapper;
	@Autowired
    private MessageMapper messageMapper;
	
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
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Ask> selectAskAll = studentMapper.selectAskAll(sdf.format(date));
			student.setCreatTimes(s.format(date));
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
		student.setIsValid("是");
		Integer addStudent = studentMapper.addStudent(student);
		return addStudent;
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
	/**
	 * 手动分配方法
	 */
	public Integer addAskName(List<Integer> list,Integer askid) {
		
		//查询出所有未分配的学生
		List<Student> selecStudent = studentMapper.selecStudent(list);
		//没查到返回-1说明你选中的学生已经被分配过了，没有要分配的学生了
		if(selecStudent.size()==0){
			return -1;
		}
		if(askid==null){
			return -2;
		}
		Integer j = null;
		//如果有分配的学生，循环所有学生
		for(int i=0;i<selecStudent.size();i++){
			//进行分配
			j = studentMapper.addAskName(selecStudent.get(i).getSid(),askid);
			//记录给某一个咨询师分配多少学生
			Message message =new Message();
			message.setAskId(askid);
			message.setSid(selecStudent.get(i).getSid());
			messageMapper.insertMessage(message);
		}
		return j;
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
		if(selectAskAll.size()==0){
			return -2;
		}
		for (int y = 0; y < selecStudent.size(); y++) {
			Message message =new Message();
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
					message.setAskId(selectAskAll.get(i).getAskId());
				} 
			}
			//修改学生的咨询师Id
			updateStudentAsk = studentMapper.updateStudentAsk(student);
			
			message.setSid(selecStudent.get(y).getSid());
			Integer insertMessage=messageMapper.insertMessage(message);
		}
		return updateStudentAsk;
	}

	//失效
	public Integer updateShixiaoStudent(String Sname) {
		// TODO Auto-generated method stub
		return studentMapper.updateShixiaoStudent(Sname);
	}

	@Override
	public List<Student> selectshixiaostudents() {
		// TODO Auto-generated method stub
		return studentMapper.selectshixiaostudents();
	}
}
