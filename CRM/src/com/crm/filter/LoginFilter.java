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
		//����HttpServletRequest����
		HttpServletRequest req=(HttpServletRequest) request;
		//����HttpServletResponse����
		HttpServletResponse resp=(HttpServletResponse) response;
		//��ȡ ����·��
		String path = req.getRequestURI();
		//��ȡ��¼����û���
		//����session����
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
		// ��½ҳ���������
		if(path.indexOf("/login.jsp") > -1) {
			chain.doFilter(req, resp);
			return;
		}
		// �ж����û��ȡ���û���Ϣ,����ת����½ҳ��
		if(cookies==null){
			resp.sendRedirect("login.jsp");
		}else{
			 if("yes".equals(isLogin)){
				 if (LonginName == null || "".equals(LonginName) || passWord ==null || "".equals(passWord)) {
					 // ��ת����½ҳ��
				   resp.sendRedirect("login.jsp");
				 } else {
					// �Ѿ���½,�����˴�����
					chain.doFilter(request, response);
				 }
			 }else{
				 if(isdenglu==null || "".equals(isdenglu) || isdengluPw==null || "".equals(isdengluPw)){
						resp.sendRedirect("login.jsp");
				 }else{
						// �Ѿ���½,�����˴�����
						chain.doFilter(request, response);
				 }
			 }
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
