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
	 * 分页查询
	 */
	public List<Users> selectUser(Fenye<Users> fenye) {
		List<Users> selectUser = usersMapper.selectUser(fenye);
		Integer selectCount = usersMapper.selectCount(fenye);
		fenye.setTotal(selectCount);
		return selectUser;
	}
	/**
	 * 添加用户
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
	 * 删除用户
	 */
	public Integer delUser(Integer uid) {
		// TODO Auto-generated method stub
		return usersMapper.delUser(uid);
	}
	/**
	 * 修改用户
	 */
	public Integer updateUser(Users users) {
		// TODO Auto-generated method stub
		return usersMapper.updateUser(users);
	}
	/**
	 * 锁定用户
	 */
	public Integer lockUser(String LonginName) {
		//查询该用户的角色
		List<Roles> selectRoleNameBy = usersMapper.selectRoleNameBy(LonginName);
		for(int i=0;i <selectRoleNameBy.size();i++){
			if(selectRoleNameBy.get(i).getRoleName().equals("管理员")){
				return -1;
			}
		}
		return usersMapper.lockUser(LonginName);
	}
	/**
	 * 解锁用户
	 */
	public Integer unlockUser(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.unlockUser(LonginName);
	}
	/**
	 * 重置密码
	 */
	public Integer chongzhiPwd(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.chongzhiPwd(LonginName);
	}
	/**
	 * 查询当前用户的角色
	 */
	public List<Roles> selectRoles(String LonginName) {
		// TODO Auto-generated method stub
		return usersMapper.selectRoles(LonginName);
	}
	/**
	 * 设置用户角色
	 */
	public Integer addUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		Integer addUserRoles = null;
		//查询该用户是否拥有该角色
		List<UserRoles> selectUserRoles = usersMapper.selectUserRoles(userRoles);
		//查询咨询经理角色有没有被引用
		List<UserRoles> selectUserRolesByName = usersMapper.selectUserRolesByRoleName(userRoles);
		if(selectUserRolesByName.size()!=0){
			return -2;
		}
		if(selectUserRoles.size()==0){
			addUserRoles = usersMapper.addUserRoles(userRoles);
			/*查询出用户名*/
			Users selectUserName = usersMapper.selectUsersName(userRoles.getUserid());
			/*查询出角色名*/
			Roles selectRolesName = usersMapper.selectRolesName(userRoles.getRoleid());
			
			//判断
			if(selectRolesName.getRoleName().equals("网络咨询师") || selectRolesName.getRoleName().equals("咨询师")){
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
	 * 移除用户角色
	 */
	public Integer delUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		/*查询出用户名*/
		Users selectUserName = usersMapper.selectUsersName(userRoles.getUserid());
		/*查询出角色名*/
		Roles selectRolesName = usersMapper.selectRolesName(userRoles.getRoleid());
		ask.setAskName(selectUserName.getLonginName());
		ask.setRoleNames(selectRolesName.getRoleName());
		/*删除用户角色*/
		Integer delUserRoles = usersMapper.delUserRoles(userRoles);
		Integer delAsk = null;
		   if(delUserRoles>0){
			   delAsk = usersMapper.delAsk(ask);
		   }
		   return delUserRoles;
	}
	

}
