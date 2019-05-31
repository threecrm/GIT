package com.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.UsersMapper;
import com.crm.entity.Ask;
import com.crm.entity.Fenye;
import com.crm.entity.Roles;
import com.crm.entity.UserRoles;
import com.crm.entity.Users;
@Service         
public class UsersServiceImp implements UsersService {
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private Fenye<Users> fenye;
	@Autowired
	private Ask ask;
	@Autowired
	private Users users;
	@Autowired
	private Roles roles;

	/**
	 * ��ҳ��ѯ
	 */
	public List<Users> selectUser(Fenye<Users> fenye) {
		List<Users> selectUser = usersMapper.selectUser(fenye);
		Integer selectCount = usersMapper.selectCount(fenye);
		fenye.setTotal(selectCount);
		return selectUser;
	}
	/**
	 * ����û�
	 */
	public Integer insertUser(Users users) {
		Integer insertUser =null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Users> selectUserName = usersMapper.selectUserName(users);
		if(selectUserName.size()==0){
			users.setCreateTime(sdf.format(new Date()));
			insertUser = usersMapper.insertUser(users);
		}else{
			insertUser=-1;
		}
		
		return insertUser;
	}
	/**
	 * ɾ���û�
	 */
	public Integer delUser(Integer uid) {
		// TODO Auto-generated method stub
		return usersMapper.delUser(uid);
	}
	/**
	 * �޸��û�
	 */
	public Integer updateUser(Users users) {
		// TODO Auto-generated method stub
		return usersMapper.updateUser(users);
	}
	/**
	 * �����û�
	 */
	public Integer lockUser(String LonginName) {
		//��ѯ���û��Ľ�ɫ
		List<Roles> selectRoleNameBy = usersMapper.selectRoleNameBy(LonginName);
		for(int i=0;i <selectRoleNameBy.size();i++){
			if(selectRoleNameBy.get(i).getRoleName().equals("����Ա")){
				return -1;
			}
		}
		return usersMapper.lockUser(LonginName);
	}
	/**
	 * �����û�
	 */
	public Integer unlockUser(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.unlockUser(LonginName);
	}
	/**
	 * ��������
	 */
	public Integer chongzhiPwd(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.chongzhiPwd(LonginName);
	}
	/**
	 * ��ѯ��ǰ�û��Ľ�ɫ
	 */
	public List<Roles> selectRoles(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.selectRoles(LonginName);
	}
	/**
	 * �����û���ɫ
	 */
	public Integer addUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		Integer addUserRoles = null;
		//��ѯ���û��Ƿ�ӵ�иý�ɫ
		List<UserRoles> selectUserRoles = usersMapper.selectUserRoles(userRoles);
		//��ѯ��ѯ�����ɫ��û�б�����
		List<UserRoles> selectUserRolesByName = usersMapper.selectUserRolesByRoleName(userRoles);
		if(selectUserRolesByName.size()!=0){
			return -2;
		}
		if(selectUserRoles.size()==0){
			addUserRoles = usersMapper.addUserRoles(userRoles);
			/*��ѯ���û���*/
			Users selectUserName = usersMapper.selectUsersName(userRoles.getUserid());
			/*��ѯ����ɫ��*/
			Roles selectRolesName = usersMapper.selectRolesName(userRoles.getRoleid());
			
			//�ж�
			if(selectRolesName.getRoleName().equals("������ѯʦ") || selectRolesName.getRoleName().equals("��ѯʦ")){
				Ask ask = new Ask();
				ask.setAskName(selectUserName.getLonginName());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ask.setCheckInTime(dateFormat.format(new Date()));
				ask.setRoleNames(selectRolesName.getRoleName());
				Integer addAsk = usersMapper.addAsk(ask);
				return addAsk;
			}
		}else{
			addUserRoles=-1;
		}
		return addUserRoles;
	}
	/**
	 * �Ƴ��û���ɫ
	 */
	public Integer delUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		/*��ѯ���û���*/
		Users selectUserName = usersMapper.selectUsersName(userRoles.getUserid());
		/*��ѯ����ɫ��*/
		Roles selectRolesName = usersMapper.selectRolesName(userRoles.getRoleid());
		ask.setAskName(selectUserName.getLonginName());
		ask.setRoleNames(selectRolesName.getRoleName());
		/*ɾ���û���ɫ*/
		Integer delUserRoles = usersMapper.delUserRoles(userRoles);
		Integer delAsk = null;
		   if(delUserRoles>0){
			   delAsk = usersMapper.delAsk(ask);
		   }
		   return delUserRoles;
	}
	

}
