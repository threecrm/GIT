package com.crm.filter;

import com.google.gson.Gson;

/**
 * ������ɽ��
 * @author Administrator
 *
 */
public class Result {
	public boolean success;
	public Object message;
	public String remark;
	
	public static String toClient(boolean suc,Object msg,String remark){
		Result r = new Result();
		r.success =suc;
		r.message = msg;
		r.remark=remark;
		Gson t = new Gson();
		return t.toJson(r);
	}
}
