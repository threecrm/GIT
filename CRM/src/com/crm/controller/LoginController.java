package com.crm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Roles;
import com.crm.entity.Users;
import com.crm.filter.Result;
import com.crm.service.LoginService;
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(Users users,HttpSession session,HttpServletRequest request,HttpServletResponse response,String isLogin,String LonginName,String yzm){
		//查询出所有的用户
		List<Users> selectUsers = loginService.selectUsers();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> logName = new ArrayList<String>();
		//取出所有的用户名称并添加到list集合里面 logName
		for(int j=0;j<selectUsers.size();j++){
			logName.add(selectUsers.get(j).getLonginName());
		}
		int time = 60*60*24*7;
		//判断验证码是否为空
		if(yzm!=null){
			 response.setContentType("text/html;charset=utf-8");
			 //获取到后台生成的验证码
			 String y = (String) request.getSession().getAttribute("yzm");
			 //yzm.toUpperCase()将小写字母转换成大写 判断前端和后台生成的验证码转换之后是否相等
			 if (yzm.toUpperCase().equals(y.toUpperCase())){
				 //判断用户名是否存在
				 if(logName.contains(users.getLonginName())){
					 	//存在循环所有用户
						for(int i=0;i<selectUsers.size();i++){
							//找到相等的符合用户名称的用户
							if(selectUsers.get(i).getLonginName().equals(users.getLonginName())){
								//根据用户名称和前台传过来的密码来查询该用户的密码是否正确
								Users selectUsersPass = loginService.selectUsersPass(users);
								//不为空正确
								if(selectUsersPass!=null){
									//判断用户的状态是否被锁定
									if("否".equals(selectUsers.get(i).getIsLockout())){
										//未锁定 判断是否七天免登录 yes(七天免登录)
										if(isLogin.equals("yes")){
											try {
												//用户名 密码进行转码（因为用户名可能是中文）
												String name = URLEncoder.encode(users.getLonginName(), "UTF-8");
												String value = URLEncoder.encode(users.getPassWord(), "UTF-8");
												//用户名 密码存进cookie
												Cookie longinName = new Cookie("longinName", name);
												//设置cookie有效时间为7天
												longinName.setMaxAge(time);
												Cookie Modules = new Cookie("name", name);
												Modules.setMaxAge(time);
												Cookie is = new Cookie("isLogin", isLogin);
												is.setMaxAge(time);
												Cookie passWord = new Cookie("passWord", value);
												//响应给前台
												passWord.setMaxAge(time);
												response.addCookie(longinName);
												response.addCookie(is);
												response.addCookie(passWord);
												response.addCookie(Modules);
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}else{
											//不是七天免登录 将用户名和密码存进session
											Cookie is = new Cookie("isLogin", isLogin);
											is.setMaxAge(time);
											response.addCookie(is);
											session.setAttribute("isdenglu", users.getLonginName());
											session.setAttribute("isdengluPw", users.getPassWord());
											//清除cookie
											cookie(request, response);
										}
										//设置登录时间
										users.setLastLoginTime(format.format(new Date()));
										//将密码错误次数修改成1 1（密码错误次数初始为1）
										users.setPsdWrongTime(1);
										loginService.updateUsers(users);
										users.setLastLoginTime(null);
										users.setPsdWrongTime(null);
										session.setAttribute("uid", selectUsers.get(i).getUid());
										return Result.toClient(true,selectUsers.get(i),"登录成功");
										
									}else{
										return Result.toClient(false, "","用戶已被锁定！！");
									}
								}else{//判断密码输入次数是否是1，是跳过，不是修改成1
									//查询该用户拥有的所有的角色
									List<Roles> selectRolesName = loginService.selectRolesName(users);
									List<String> rolesName = new ArrayList<String>();
									//将所有的用户名添加到list集合中 rolesName
									for(int u=0;u<selectRolesName.size();u++){
										rolesName.add(selectRolesName.get(u).getRoleName());
									}
									//判断是否包含管理员角色
									if(rolesName.contains("管理员")){
										     				//管理员密码错误不会被锁定
									}else{
										//密码错误次数等不等3
										if(selectUsers.get(i).getPsdWrongTime()!=3){
											//密码错误次数加1
											users.setPsdWrongTime(selectUsers.get(i).getPsdWrongTime()+1);
											loginService.updateUsers(users);
											users.setPsdWrongTime(null);
										}else{
											
											//等3锁定用户
											users.setIsLockout("是");
											users.setLockTime(format.format(new Date()));
											loginService.updateUsers(users);
											users.setIsLockout(null);
											users.setLockTime(null);
										}
									}
									return Result.toClient(false, "","密码错误！");
								}
							}
						}
					}else{
						return Result.toClient(false, "","没有此用户");
					}
			 }else{
				 return Result.toClient(false, "","验证码错误");
			 }
			 
		}
		
		return "";
	}
	@RequestMapping(value="/drop",method=RequestMethod.POST)
	@ResponseBody
	public Object drop(HttpServletRequest request,HttpServletResponse response){
		cookie(request, response);
		return true;
	}
	
	public static void cookie(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		  for (int i = 0; i < cookies.length; i++){
				 if("longinName".equals(cookies[i].getName())){
		        	  cookies[i].setMaxAge(0);
		        	  response.addCookie(cookies[i]);
				 }
		         if("passWord".equals(cookies[i].getName())){
		        	  cookies[i].setMaxAge(0);
		        	  response.addCookie(cookies[i]);
				 }
		  }
	}
	
	/**
	 * 修改个人信息
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/updatePim",method=RequestMethod.POST)
    @ResponseBody
	public Integer updatePim(Users users){
		return loginService.updatePim(users);
	}
}
