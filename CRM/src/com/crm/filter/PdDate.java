package com.crm.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//�ж�һ��ʱ���Ƿ���һ��ʱ���
public class PdDate {
	
	/**
	 * �ַ�תʱ��
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str){
		SimpleDateFormat smf=new SimpleDateFormat("HH:mm:ss");
		Date date=null;
		
		try {
			date=smf.parse(str);
		} catch (ParseException e) {
			
		}
		return date;
	}
	/**
	 * �ж�һ��ʱ���Ƿ���ʱ�����
	 * @param nowTime	����ʱ��
	 * @param beginTime ��ʼʱ��
	 * @param endTime	����ʱ��
	 * @return
	 */
	public static Integer sjd(Date nowTime,Date beginTime,Date endTime){
		Calendar date=Calendar.getInstance();
		date.setTime(nowTime);
		Calendar begin=Calendar.getInstance();
		begin.setTime(beginTime);
		Calendar end=Calendar.getInstance();
		end.setTime(endTime);
		if(date.after(begin)&&date.before(end)){
			return 1;
		}else if(nowTime.compareTo(beginTime)==0||nowTime.compareTo(endTime)==0){
			return 1;
		}else if(date.after(end)){
			//���ڽ���ʱ��ٵ�
			return 2;
		}else if(date.before(begin)){
			//С�ڿ�ʼǩ��ʱ�� ��δ��ʼǩ��
			return 3;
		}else{
			return 0;
		}
	}
}
