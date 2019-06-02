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
		//��ѯ�����е��û�
		List<Users> selectUsers = loginService.selectUsers();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> logName = new ArrayList<String>();
		//ȡ�����е��û����Ʋ���ӵ�list�������� logName
		for(int j=0;j<selectUsers.size();j++){
			logName.add(selectUsers.get(j).getLonginName());
		}
		int time = 60*60*24*7;
		//�ж���֤���Ƿ�Ϊ��
		if(yzm!=null){
			 response.setContentType("text/html;charset=utf-8");
			 //��ȡ����̨���ɵ���֤��
			 String y = (String) request.getSession().getAttribute("yzm");
			 //yzm.toUpperCase()��Сд��ĸת���ɴ�д �ж�ǰ�˺ͺ�̨���ɵ���֤��ת��֮���Ƿ����
			 if (yzm.toUpperCase().equals(y.toUpperCase())){
				 //�ж��û����Ƿ����
				 if(logName.contains(users.getLonginName())){
					 	//����ѭ�������û�
						for(int i=0;i<selectUsers.size();i++){
							//�ҵ���ȵķ����û����Ƶ��û�
							if(selectUsers.get(i).getLonginName().equals(users.getLonginName())){
								//�����û����ƺ�ǰ̨����������������ѯ���û��������Ƿ���ȷ
								Users selectUsersPass = loginService.selectUsersPass(users);
								//��Ϊ����ȷ
								if(selectUsersPass!=null){
									//�ж��û���״̬�Ƿ�����
									if("��".equals(selectUsers.get(i).getIsLockout())){
										//δ���� �ж��Ƿ��������¼ yes(�������¼)
										if(isLogin.equals("yes")){
											try {
												//�û��� �������ת�루��Ϊ�û������������ģ�
												String name = URLEncoder.encode(users.getLonginName(), "UTF-8");
												String value = URLEncoder.encode(users.getPassWord(), "UTF-8");
												//�û��� ������cookie
												Cookie longinName = new Cookie("longinName", name);
												//����cookie��Чʱ��Ϊ7��
												longinName.setMaxAge(time);
												Cookie Modules = new Cookie("name", name);
												Modules.setMaxAge(time);
												Cookie is = new Cookie("isLogin", isLogin);
												is.setMaxAge(time);
												Cookie passWord = new Cookie("passWord", value);
												//��Ӧ��ǰ̨
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
											//�����������¼ ���û�����������session
											Cookie is = new Cookie("isLogin", isLogin);
											is.setMaxAge(time);
											response.addCookie(is);
											session.setAttribute("isdenglu", users.getLonginName());
											session.setAttribute("isdengluPw", users.getPassWord());
											//���cookie
											cookie(request, response);
										}
										//���õ�¼ʱ��
										users.setLastLoginTime(format.format(new Date()));
										//�������������޸ĳ�1 1��������������ʼΪ1��
										users.setPsdWrongTime(1);
										loginService.updateUsers(users);
										users.setLastLoginTime(null);
										users.setPsdWrongTime(null);
										session.setAttribute("uid", selectUsers.get(i).getUid());
										return Result.toClient(true,selectUsers.get(i),"��¼�ɹ�");
										
									}else{
										return Result.toClient(false, "","�Ñ��ѱ���������");
									}
								}else{//�ж�������������Ƿ���1���������������޸ĳ�1
									//��ѯ���û�ӵ�е����еĽ�ɫ
									List<Roles> selectRolesName = loginService.selectRolesName(users);
									List<String> rolesName = new ArrayList<String>();
									//�����е��û�����ӵ�list������ rolesName
									for(int u=0;u<selectRolesName.size();u++){
										rolesName.add(selectRolesName.get(u).getRoleName());
									}
									//�ж��Ƿ��������Ա��ɫ
									if(rolesName.contains("����Ա")){
										     				//����Ա������󲻻ᱻ����
									}else{
										//�����������Ȳ���3
										if(selectUsers.get(i).getPsdWrongTime()!=3){
											//������������1
											users.setPsdWrongTime(selectUsers.get(i).getPsdWrongTime()+1);
											loginService.updateUsers(users);
											users.setPsdWrongTime(null);
										}else{
											
											//��3�����û�
											users.setIsLockout("��");
											users.setLockTime(format.format(new Date()));
											loginService.updateUsers(users);
											users.setIsLockout(null);
											users.setLockTime(null);
										}
									}
									return Result.toClient(false, "","�������");
								}
							}
						}
					}else{
						return Result.toClient(false, "","û�д��û�");
					}
			 }else{
				 return Result.toClient(false, "","��֤�����");
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
	 * �޸ĸ�����Ϣ
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/updatePim",method=RequestMethod.POST)
    @ResponseBody
	public Integer updatePim(Users users){
		return loginService.updatePim(users);
	}
}
