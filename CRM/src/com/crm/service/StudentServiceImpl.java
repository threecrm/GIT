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

	// ��ѯ��ɫ
	public List<Roles> selectRoles() {
		List<Roles> selectRoles = studentMapper.selectRoles();
		return selectRoles;
	}

	public Student selectAsk(Integer id) {
		Student selectAsk = studentMapper.selectAsk(id);
		return selectAsk;
	}

	public Integer addStudent(Student student, Boolean tur) {
		if (tur == true) {// true�Զ�����
			// ��ѯ��������ѯʦ
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
				// ˭��ѧ������˭�������
				if (selectAskStu < Max) {
					Max = selectAskStu;
					student.setAskerId(selectAskAll.get(i).getAskId());
					// ѧ����ȱ�Ȩ��
				}
			}
		}
		student.setIsValid("��");
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
	 * �ֶ����䷽��
	 */
	public Integer addAskName(List<Integer> list,Integer askid) {
		
		//��ѯ������δ�����ѧ��
		List<Student> selecStudent = studentMapper.selecStudent(list);
		//û�鵽����-1˵����ѡ�е�ѧ���Ѿ���������ˣ�û��Ҫ�����ѧ����
		if(selecStudent.size()==0){
			return -1;
		}
		if(askid==null){
			return -2;
		}
		Integer j = null;
		//����з����ѧ����ѭ������ѧ��
		for(int i=0;i<selecStudent.size();i++){
			//���з���
			j = studentMapper.addAskName(selecStudent.get(i).getSid(),askid);
			//��¼��ĳһ����ѯʦ�������ѧ��
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
		//����ѡ���ѧ����ѯ������δ�����ѧ��
		List<Student> selecStudent = studentMapper.selecStudent(list);
		Integer updateStudentAsk =null;
		if(selecStudent.size()==0){
			return -1;
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		//��ѯ�����еĵ����Ѿ�ǩ������ѯʦ
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
				
				//��ѯ����ǩ������ѯʦ�ж���ѧ������
				Integer selectAskStu = studentMapper.selectAskStu(sdf.format(date),selectAskAll.get(i).getAskId());
				//û��Ϊ��
				if (selectAskStu == null) {
					selectAskStu=0;
				}
				//ѭ�������С��
				if (selectAskStu < Max) {
					Max = selectAskStu;
					student.setAskerId(selectAskAll.get(i).getAskId());
					message.setAskId(selectAskAll.get(i).getAskId());
				} 
			}
			//�޸�ѧ������ѯʦId
			updateStudentAsk = studentMapper.updateStudentAsk(student);
			
			message.setSid(selecStudent.get(y).getSid());
			Integer insertMessage=messageMapper.insertMessage(message);
		}
		return updateStudentAsk;
	}

	//ʧЧ
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
