package com.fd.font.cloud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具，授权时间，授权过期时间等
 * @author XiRuiQiang
 *
 */
public class FontCloudDateUtil {
	/**
	 * 获取授权失效时间,从现在起,6个月后失效
	 * @author XiRuiQiang
	 * @return
	 */
	public static Date getUnauthzDateFormNow(){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH,6);
		return calendar.getTime();
	}
	public static Date getUnauthzDateFormDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,6);
		return calendar.getTime();
	}
	/**
	 * 计算授权是否失效
	 * @author XiRuiQiang
	 * @param zuthzDate 授权开始时间
	 * @return
	 */
	public static boolean isUnzuthz(Date zuthzDate){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(zuthzDate);
		calendar.add(Calendar.MONTH,6);
		return (calendar.getTime().getTime()-new Date().getTime())<0;
	}
	/**
	 * 计算是否可以重新授权(在15天内)
	 * @param authDate 授权过期时间
	 * @return
	 */
	public static boolean canReauth(Date unAuthDate){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(unAuthDate);
		calendar.add(Calendar.DAY_OF_MONTH,-15);
		long start15=unAuthDate.getTime()-calendar.getTime().getTime();
		long now15=new Date().getTime()-calendar.getTime().getTime();
		return (start15-now15)>0;
	}
	/**
	 * 计算现在到明天00:00:00多长时间(秒)
	 * @author XiRuiQiang
	 * @return
	 */
	public static Long getToLaseTime(){
		Calendar t = Calendar.getInstance();
		Long now = t.getTimeInMillis();
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR_OF_DAY, 0);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		Long die = t.getTimeInMillis();
		return (die - now) / 1000;
	}
	
	public static String getNowDateStr(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	/**
	 * 计算验证码失效时间
	 * @author XiRuiQiang
	 * @return
	 */
	public static Long getSmsDieTime() {
		Calendar t = Calendar.getInstance();
		t.add(Calendar.MINUTE, 10);
		Long die = t.getTimeInMillis();
		return die;
	}
}
