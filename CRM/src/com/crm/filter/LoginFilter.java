package com.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建HttpServletRequest对象
		HttpServletRequest req=(HttpServletRequest) request;
		//创建HttpServletResponse对象
		HttpServletResponse resp=(HttpServletResponse) response;
		//获取 请求路径
		String path = req.getRequestURI();
		//获取登录后的用户名
		//创建session对象
		HttpSession session = req.getSession();
		String LonginName =null;
		String passWord = null;
		String isLogin = null;
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		 if(cookies!=null){
			  for (int i = 0; i < cookies.length; i++){
			         if("longinName".equals(cookies[i].getName())){
			        	LonginName = cookies[i].getValue();
					 }
			         if("passWord".equals(cookies[i].getName())){
			        	 passWord = cookies[i].getValue();
					 }
			         if("isLogin".equals(cookies[i].getName())){
			        	 isLogin = cookies[i].getValue();
					 }
			  }
		 }
		 String isdenglu = (String) session.getAttribute("isdenglu");
		 String isdengluPw = (String) session.getAttribute("isdengluPw");
		// 登陆页面无需过滤
		if(path.indexOf("/login.jsp") > -1) {
			chain.doFilter(req, resp);
			return;
		}
		// 判断如果没有取到用户信息,就跳转到登陆页面
		if(cookies==null){
			resp.sendRedirect("login.jsp");
		}else{
			 if("yes".equals(isLogin)){
				 if (LonginName == null || "".equals(LonginName) || passWord ==null || "".equals(passWord)) {
					 // 跳转到登陆页面
				   resp.sendRedirect("login.jsp");
				 } else {
					// 已经登陆,继续此次请求
					chain.doFilter(request, response);
				 }
			 }else{
				 if(isdenglu==null || "".equals(isdenglu) || isdengluPw==null || "".equals(isdengluPw)){
						resp.sendRedirect("login.jsp");
				 }else{
						// 已经登陆,继续此次请求
						chain.doFilter(request, response);
				 }
			 }
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
