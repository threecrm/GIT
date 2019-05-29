package com.crm.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//判断一个时间是否在一个时间段
public class PdDate {
	
	/**
	 * 字符转时间
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
	 * 判断一个时间是否在时间段内
	 * @param nowTime	传的时间
	 * @param beginTime 开始时间
	 * @param endTime	结束时间
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
			//大于结束时间迟到
			return 2;
		}else if(date.before(begin)){
			//小于开始签到时间 还未开始签到
			return 3;
		}else{
			return 0;
		}
	}
}
