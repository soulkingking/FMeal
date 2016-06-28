package com.fragrantmeal.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringHandler {
	
	/**
	 * Timestamp转化为String
	 * @param ts Timestamp对象
	 * @return
	 */
	public static String timeStampToStr(Timestamp ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒
		return sdf.format(ts);
	}
	
	/**
	 * String 转化成 Timestamp
	 * @param str 必须是 yyyy-MM-dd HH:mm:ss 格式
	 * @return
	 */
	public static Timestamp strToTimestamp(String str){
		return Timestamp.valueOf(str);
	}

	public static String timeTostr(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String getSerial(Date date, int index) {
		long msel = date.getTime(); 
		SimpleDateFormat fm = new SimpleDateFormat("MMddyyyyHHmmssSS"); 
		msel += index; 
		date.setTime(msel); 
		String serials = fm.format(date); 
		return serials;
	}
	
	/**
	 * 通过当前系统时间和随机数生成ID
	 */
	public static String createSerialId(long curSysTime) {
		SimpleDateFormat fm = new SimpleDateFormat("yMMddHHmmssSS"); 
		StringBuffer sb = new StringBuffer(fm.format(new Date(curSysTime))); 
		Random random = new Random();
		return sb.append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10)).toString();
	}

	public static String changehtml(String str) {
		String change = "";
		if (str != null && !str.equals("")) {
			change = str.replace("&", "&amp;");
			change = change.replace(" ", "&nbsp;");
			change = change.replace("<", "&lt;");
			change = change.replace(">", "&gt;");
			change = change.replace("\"", "&quot;");
			change = change.replace("\r\n", "<br>");
		}
		return change;
	}
}
