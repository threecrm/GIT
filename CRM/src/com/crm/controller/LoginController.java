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
		List<Users> selectUsers = loginService.selectUsers();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> logName = new ArrayList<String>();
		for(int j=0;j<selectUsers.size();j++){
			logName.add(selectUsers.get(j).getLonginName());
		}
		int time = 60*60*24*7;
		if(yzm!=null){
			 response.setContentType("text/html;charset=utf-8");
			 String y = (String) request.getSession().getAttribute("yzm");
			 if (yzm.toUpperCase().equals(y.toUpperCase())){
				 if(logName.contains(users.getLonginName())){
						for(int i=0;i<selectUsers.size();i++){
							if(selectUsers.get(i).getLonginName().equals(users.getLonginName())){
								Users selectUsersPass = loginService.selectUsersPass(users);
								if(selectUsersPass!=null){
									if("否".equals(selectUsers.get(i).getIsLockout())){
										if(isLogin.equals("yes")){
											try {
												String name = URLEncoder.encode(users.getLonginName(), "UTF-8");
												String value = URLEncoder.encode(users.getPassWord(), "UTF-8");
												
												Cookie longinName = new Cookie("longinName", name);
												longinName.setMaxAge(time);
												Cookie Modules = new Cookie("name", name);
												Modules.setMaxAge(time);
												Cookie is = new Cookie("isLogin", isLogin);
												is.setMaxAge(time);
												Cookie passWord = new Cookie("passWord", value);
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
											Cookie is = new Cookie("isLogin", isLogin);
											is.setMaxAge(time);
											response.addCookie(is);
											session.setAttribute("isdenglu", users.getLonginName());
											session.setAttribute("isdengluPw", users.getPassWord());
											cookie(request, response);
										}
//										判断密码输入次数是否是1，是跳过，不是修改成1
										users.setLastLoginTime(format.format(new Date()));
										users.setPsdWrongTime(1);
										loginService.updateUsers(users);
										users.setLastLoginTime(null);
										users.setPsdWrongTime(null);
										session.setAttribute("uid", selectUsers.get(i).getUid());
										return Result.toClient(true,selectUsers.get(i),"登录成功");
										
									}else{
										return Result.toClient(false, "","用戶已被锁定！！");
									}
								}else{
									if(selectUsers.get(i).getPsdWrongTime()!=3){
										users.setPsdWrongTime(selectUsers.get(i).getPsdWrongTime()+1);
										loginService.updateUsers(users);
										users.setPsdWrongTime(null);
									}else{
										users.setIsLockout("是");
										users.setLockTime(format.format(new Date()));
										loginService.updateUsers(users);
										users.setIsLockout(null);
										users.setLockTime(null);
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
}
