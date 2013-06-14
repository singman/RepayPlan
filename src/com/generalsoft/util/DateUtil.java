package com.generalsoft.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author 卢
 *
 */
public class DateUtil {
	
	/**
	 * 获取web服务器当前时间
	 * @param f 日期格式
	 * @return
	 */
	public static String getCurDate(String f){
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.format(new Date());
	}
	
	/**
	 * 将时间按格式转变为 字符串
	 * @param date 日期
	 * @param f 格式
	 * @return
	 */
	public static String format(Date date,String f){
		if( date == null ){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.format(date);
	}
	/**
	 * 时间  由一个格式转为另一个格式
	 * @param date
	 * @param oldf
	 * @param newf
	 * @return
	 */
	public static String format(String date,String oldf,String newf) {
		if (date == null || date.length() == 0) {
			return "";
		}
		String d = "";
		try {
			DateFormat formater = new SimpleDateFormat(oldf);
			Date _date = formater.parse(date);
			d = new SimpleDateFormat(newf).format(_date);
		} catch (Exception e) {
			return null;
		}
		return d;
	}
	/**
	 * 转换时间字符串格式
	 * @param date 原时间字符串
	 * @param f 新的格式
	 * @return
	 */
	public static Date StrToSimpleDate(String date,String f){
		if (date == null || date.length() == 0) {
			return null;
		}
		try {
			DateFormat formater = new SimpleDateFormat(f);
			return formater.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获得上一个周日
	 * @return
	 */
	public static String getLastWeekMonday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK)-1;
		int offset1 = 1-dayOfWeek;
		calendar1.add(Calendar.DATE, offset1-7);
		String date = sdf.format(calendar1.getTime());
		return date;
	}
	public static String getLastWeekSunday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar2.get(Calendar.DAY_OF_WEEK)-1;
		int offset2 = 7-dayOfWeek;
		calendar2.add(Calendar.DATE, offset2-7);
		String date = sdf.format(calendar2.getTime());
		return date;
	}
	/**
	 * 获得所给日期的下一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	/**
	 * 获得所给日期几天后的日期
	 * @param date
	 * @return
	 */
	public static Date getDayAfterPeriod(Date date,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	/**
	 * 获得所给日期的下一个月
	 * @param date
	 * @return
	 */
	public static Date getNextMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	/**
	 * 获取两个日期之间的实际天数
	 * @param sdate
	 * @param edate
	 * @return
	 */
	public static Integer getDaysBetween(Date sdate, Date edate) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Long l = sdate.getTime()-format.parse(format.format(edate)).getTime();
			Long day = l/(24*60*60*1000); 
			return day.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    /**
     *获取两个日期之间的月份
     * @param dateF     开始日期
     * @param dateS     结束日期
     * @return int
     * 日期格式只能为yyyyMMdd
     */
    public static int getMonthbetween(String dateF,String dateS){
        //拆分年月日
        int yearF = Integer.parseInt(dateF.substring(0,4));
        int monthF = Integer.parseInt(dateF.substring(4,6));
        int dayF = Integer.parseInt(dateF.substring(6,8));
        int yearS = Integer.parseInt(dateS.substring(0,4));
        int monthS = Integer.parseInt(dateS.substring(4,6));
        int dayS = Integer.parseInt(dateS.substring(6,8));
        //计算相差月数
        int month = 0;
        if(yearF < yearS){
            month = (yearS - yearF)*12;
        }
        month += monthS - monthF;
        if(dayS < dayF){
            month--;
        }
        return month;
    }
	/**
	 * 判断当前日期是否在两个日期之间
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static  boolean ifBetween(Date startDate, Date endDate){
		Date now = new Date();
		long nowLong = now .getTime();
		long sLong = startDate.getTime();
		long eLong = endDate.getTime();
		if(nowLong>=sLong && nowLong <= eLong){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 比较日期对象
	 * @param 
	 * @return
	 */
	static public boolean compareDatetoDate(Date d1, Date d2) {

		boolean blnResult = false;
		if (d1.after(d2)) {
			blnResult = false;
		}else if (d1.before(d2)) {
			blnResult = true;
		}else if (d1.equals(d2)) {
			blnResult = true;
		}
		return blnResult;
	}
	/**
	 * 解析浮点数 添加千分制标志
	 * @param 
	 * @return
	 */
	public static String paseNum(String num){
		int index = num.indexOf(".");
		if(index != -1){
			String str1 = num.substring(0, index);
			String str2 = num.substring(index,num.length());
			num = changeStr(str1)+str2;
		}else{
			num = changeStr(num);
		}
		return num;
	}
	/**
	 * 添加千分制标志
	 * @param str
	 * @return
	 */
	public static String changeStr(String str){
		int length = str.length();
		int a = (length-1)/3;
		int m =  length % 3;
		m = m==0?3:m ;
		String bs = str.substring(0,m);
		for(int i = 1;i<=a;i++){
			bs += ",";
			bs += str.substring(m+3*(i-1), m+3*(i-1)+3);
		}
		return bs;
	}
	/**
	 * 去除千分制标志号
	 * @param str
	 * @return
	 */
	public static String removeQfh(String str){
		 int i = 0; 
		 String b = "";
		 for(i=0;i<str.length();i++)  if(str.charAt(i)!=','){
			 b+=str.charAt(i);  
		 }
		 return b;
		 
	}
	/**
	 * 删除字符串中指定的特殊符号，只能去掉一种特殊符号
	 * @param str
	 * @param flag
	 * @return
	 */
	public static String removeFlag(String str ,String flag){
		 char[] ca = flag.toCharArray();
		 int i = 0; 
		 String b = "";
		 for(i=0;i<str.length();i++){
			 if(str.charAt(i)!= ca[0])
			 b+=str.charAt(i);  
		 }
		 return b;
	}
    /**
     *获得指定日期后几个月的日子
     *  @param date
     *  @param dateFormat date对应的日期格式
     * @param format 日期格式  为空则默认 yyyyMMdd
     * @param months 指定月数
     * @return
     */
    public static String getDateAfterMonths(String date,String dateFormat,String format,int months) {
        Calendar calendar = null;
        if (format == null){
            format = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null && dateFormat != null){
            calendar = new GregorianCalendar();
            try {
                calendar.setTime(new SimpleDateFormat(dateFormat).parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            calendar = Calendar.getInstance();
        }
        String newDate = null;
         if (calendar != null){
             calendar.add(Calendar.MONTH, months); //得到某一个月
             int year = calendar.get(Calendar.YEAR);
             int month = calendar.get(Calendar.MONTH)+1;//这里月要加1
             int day = calendar.get(Calendar.DATE);
             newDate = year+"" ;
             if (month<10){
                 newDate+="0"+month;
             }else{
                 newDate+=""+month;
             }
             if (day<10){
                 newDate+="0"+day;
             }else{
                 newDate+=""+day;
             }
             try {
                 newDate= sdf.format(new SimpleDateFormat("yyyyMMdd").parse(newDate));
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }
        return newDate;
    }
    /**
     *获得日期后几个月的日子
     *  @param date
     *  @param dateFormat date对应的日期格式
     * @param format 日期格式  为空则默认 yyyyMMdd
     * @param days 指定月数
     * @return
     */
    public static String getDateAfterDays(String date,String dateFormat,String format,int days) {
        Calendar calendar = null;
        if (format == null){
            format = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null && dateFormat != null){
            calendar = new GregorianCalendar();
            try {
                calendar.setTime(new SimpleDateFormat(dateFormat).parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            calendar = Calendar.getInstance();
        }
        String newDate = null;
        if (calendar != null){
            calendar.add(Calendar.DATE, days); //得到某一个月
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;//这里月要加1
            int day = calendar.get(Calendar.DATE);
            newDate = year+"" ;
            if (month<10){
                newDate+="0"+month;
            }else{
                newDate+=""+month;
            }
            if (day<10){
                newDate+="0"+day;
            }else{
                newDate+=""+day;
            }
            try {
                newDate= sdf.format(new SimpleDateFormat("yyyyMMdd").parse(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return newDate;
    }
    public static void main(String[] args){
           System.out.println(getDateAfterMonths("20130508","yyyyMMdd","yyyy-MM-dd",3));
    }

}
