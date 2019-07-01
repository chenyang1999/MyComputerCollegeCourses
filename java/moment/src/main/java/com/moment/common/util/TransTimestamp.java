package com.moment.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TransTimestamp {
	//date转换成timestamp的相互转换（父子类关系，不能直接转，使用string作为中间介质）
 public static Timestamp getTs(){
	 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=new Date();
		String dateStr = sdf.format(date);
		Timestamp ts = Timestamp.valueOf(dateStr);
		return ts;
 }
 	//将日子再加一天
 public static Date getTomorrow(Date date){ 
     Calendar   calendar   =   new   GregorianCalendar(); 
     calendar.setTime(date); 
     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
     return date=calendar.getTime();   //这个时间就是日期往后推一天的结果
 }
  public static void main(String[] args) {
	  Date date1=new Date();
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		TransTimestamp ts=new TransTimestamp();
		Date date2=ts.getTomorrow(date1);
		Timestamp ts1=new Timestamp(System.currentTimeMillis());
		if(ts1.after(date1)&&ts1.before(date2)){
			System.out.println("shijiannei");
		}else{
			System.out.println("out");
		}
}
}
