 package com.trip.base.util;


 import org.apache.log4j.Logger;
 import org.joda.time.DateTime;

 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 import java.util.StringTokenizer;


 /**
  * 系统日期公共类

  */
 public class DateUtil {
      /**
       * yyyy-MM-dd
       */
      public static final String DATE_FORMAT = "yyyy-MM-dd";
      /**
       * yyyy-MM-dd kk:mm:ss
       */
      public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
      /**
       * yyyyMMdd
       */
      public static final String DATE_NOFUll_FORMAT = "yyyyMMdd";
      /**
       * yyyyMM
       */
      public static final String YEAR_FORMAT="yyyyMM";

      /**
       * yyyyMMddkk
       */
      public static final String HOUR_NOFULL_FORMAT = "yyyyMMddkk";
      /**
       * yyyyMMddkkmmss
       */
      public static final String TIME_NOFUll_FORMAT = "yyyyMMddkkmmss";

      private static Logger log = Logger.getLogger(DateUtil.class);

     private static final SimpleDateFormat miniFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
     /**
      * 取得系统当前时间格式为："yyyyMMddHHmmss"，如"20080808080808"
      * @return  String
      */
     public static String getSystemTime(){
         String time = "";
         Date today = new Date();
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
         time = format.format(today);
         return time;
     }

     public static String format(Date date, String pattern) {
         return (new DateTime(date)).toString(pattern);
     }

     /**
      * 取得指定格式的系统当前时间

      * @param dateFormat 时间格式：如 yyyyMMddHHmmss 代表 年月日时分秒
      * @return String
      */
     public static String getSystemTime(String dateFormat){
         String time = "";
         Date today = new Date();
         SimpleDateFormat format = new SimpleDateFormat(dateFormat);
         time = format.format(today);
         return time;
     }

     public static String getCurrentDate(String pattern) {
             Calendar calendar = Calendar.getInstance();
             Date date = calendar.getTime();
             return dateToString(date, pattern);
     }

      /**
      * 格式转换yyyy-MM-dd kk:mm:ss 和 yyyyMMddkkmmss 相互转换
      *
      * @param value String
      * @return String
      */
     public static String stringFormat(String value) {
         String sReturn = "";
         if (value == null) return sReturn;
         if (value.length() == 14) {
             sReturn = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8) + " " + value.substring(8, 10)
                     + ":" + value.substring(10, 12) + ":" + value.substring(12, 14);
             return sReturn;
         }
         if (value.length() == 19) {
             sReturn = value.substring(0, 4) + value.substring(5, 7) + value.substring(8, 10) + value.substring(11, 13)
                     + value.substring(14, 16) + value.substring(17, 19);
             return sReturn;
         }
         return sReturn;
     }

     /**
      * 字符串转换成日期，如果需转换的字符串为NULL，则返回为NULL
      *
      * @param value String 需转换的字符串
      * @return Date 日期
      */
     public static Date stringToDate(String value, String pattern) {
         if (value == null) {
             return null;
         }
         if (value.equals("")) {
             return null;
         }
         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
         Date date = new Date();
         try {
             date = sdf.parse(value);
         }
         catch (Exception ex) {
             ex.printStackTrace();
         }
         return date;
     }

     /**
      * 日期转换成字符串，如果需转换的日期为NULL，则返回为NULL（默认格式为日期）

      *
      * @param date    Date 需转换的日期

      * @param pattern String 转换成字符型的日期格式

      * @return String 日期字符串

      */
     public static String dateToString(Date date, String pattern) {
         if (date == null) {
             return null;
         }
         String format = pattern;
         if (format == null) format = DateUtil.DATE_FORMAT;
         if (format.equals("")) format = DateUtil.DATE_FORMAT;
         SimpleDateFormat sdf = new SimpleDateFormat(format);
         return sdf.format(date);
     }

     /**
      * 将日期分割成一个字符串数组
      *
      * @param date String
      * @return String[]
      */
     public static String[] dateDivision(String date) {
         String[] value = new String[3];
         StringTokenizer token = new StringTokenizer(date, "-");
         for (int i = 0; token.hasMoreTokens(); i++) {
             value[i] = token.nextToken();
         }
         return value;
     }

     /**
      * 返回俩日期之间的差（秒）
      *
      * @param sDateFrom String 起始日期
      * @param sDateEnd  String 结束日期
      * @return long 日期差

      */
     public static long getSecondsDate(String sDateFrom, String sDateEnd) {
         long lDateFrom = 0, lDateEnd = 0;
         SimpleDateFormat format = new SimpleDateFormat(TIME_NOFUll_FORMAT);
         try {
             lDateFrom = (format.parse(sDateFrom)).getTime();
             lDateEnd = (format.parse(sDateEnd)).getTime();
             return (lDateEnd - lDateFrom) / 1000;
         } catch (Exception ex) {
             log.error("解析日期时出错"+ex.getMessage());
             return 0;
         }
     }

     public static String datetimeNull(String string) {
         //判断输入的字符串是否为null或“”，如果是，则返回字符串“null”，否则返回原字符串（带上引号），，
         //主要用于数据表中数据插入datetime类型的数据时进行转换
         String resultString = "null";
         if ((string != null))
             if (! (string.equals("")))
                 resultString = "'" + string + "'";
         return resultString;
     }

     public static String oralcleDatetimeNull(String string) {
         //判断输入的字符串是否为null或“”，如果是，则返回字符串“null”，否则返回原字符串（带上引号），，
         //主要用于数据表中数据插入datetime类型的数据时进行转换
         String resultString = "null";
         if ((string != null))
             if (! (string.equals("")))
                 resultString = "to_date('" + string + "','yyyy-mm-dd hh24:mi:ss')";
         return resultString;
     }

     /**
      * 将ORACLE数据库中取出的日期类型的数据转换为YYYY-MM-DD HI24:MI:SS 的形式

      *
      * @param string String
      * @return String
      */
     public static String oracleDate(String string) {
         String result = "";
         if (string == null) return result;
         if (string.equals("")) return result;
         if (string.length() < 20) return string;
         string = string.substring(0, 19);
         return string;
     }

     public static boolean beforeNextMonth(String time, String pattern) {
         log.debug("(1) time:" + time + ", pattern:" + pattern);
         Date nextmonth = getNextMonth().getTime();
         Date then = stringToDate(time, pattern);
         return nextmonth.after(then);
     }

     public static boolean isNextMonth(String time, String pattern) {
         log.debug("(2) time:" + time + ", pattern:" + pattern);
         Calendar nextmonth = getNextMonth();
         Calendar then = GregorianCalendar.getInstance();
         then.setTime(stringToDate(time, pattern));
         return nextmonth.get(Calendar.YEAR) == then.get(Calendar.YEAR) &&
                 nextmonth.get(Calendar.MONTH) == then.get(Calendar.MONTH);
     }

     private static Calendar getNextMonth() {
         Calendar cal = GregorianCalendar.getInstance();
         cal.add(Calendar.MONTH, 1);
         cal.set(Calendar.DATE, 1);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);
         return cal;
     }

     /**
      * 获得两个日期的时间差（单位为秒）
      * @param date1
      * @param date2
      * @return
      */
     public static int distance(Date date1, Date date2) {
         return (int) ((date2.getTime() - date1.getTime())/1000);
     }

     /**
      * 取得当天的日期并格式化（不带时分秒）
      * @return
      */
     public static String getNowFormatDate() {
         Calendar calendar = new GregorianCalendar();
         String sTemp = "";
         sTemp = "" + calendar.get(Calendar.YEAR) + "-";
         if ((calendar.get(Calendar.MONTH) + 1) < 10)
             sTemp += "0";
         sTemp += (calendar.get(Calendar.MONTH) + 1) + "-";
         if (calendar.get(Calendar.DATE) < 10)
             sTemp += "0";
         sTemp += calendar.get(Calendar.DATE);
         return sTemp;
     }



     /**
      * add by luotao 2009-3-17
      * //java中对日期的加减操作

       //gc.add(1,-1)表示年份减一.
       //gc.add(2,-1)表示月份减一.
       //gc.add(3.-1)表示周减一.
       //gc.add(5,-1)表示天减一.
      * @return 加减后的日期
      */
     @SuppressWarnings("static-access")
     public static String getFormatDate(int value1,int value2) {
         String newDate="";
         Date date = new Date();
         SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(date);
         gc.add(value1,value2);
         gc.set(gc.get(gc.YEAR),gc.get(gc.MONTH),gc.get(gc.DATE));
         newDate=df.format(gc.getTime());//获取加减后的日期
          return newDate;
     }
 //    /**
 //	 * 取得数据库时间

 //	 * @return
 //	 */
 //	public static String getDataBaseTime(){
 //		return GetDate.getInstance().dataBaseTime();
 //	}
     /**
      * 取系统时间包括毫秒

      * @return
      */
     public static String getSystemTimeByMi(){
         Calendar CD = Calendar.getInstance();
           String YY = String.valueOf(CD.get(Calendar.YEAR));
           String MM = String.valueOf(CD.get(Calendar.MONTH)+1);
           String DD = String.valueOf(CD.get(Calendar.DATE));
           String HH = String.valueOf(CD.get(Calendar.HOUR_OF_DAY));
           String NN = String.valueOf(CD.get(Calendar.MINUTE));
           String SS = String.valueOf(CD.get(Calendar.SECOND));
           String MI = String.valueOf(CD.get(Calendar.MILLISECOND));
           String temp = YY+MM+DD+HH+NN+SS+MI;
         return temp;
     }

     public static String fomatDate(String dateStr){
         if(dateStr==null){
             return null;
         }else{
             return dateStr.replaceAll("-", "");
         }

     }

     public static String getCurrentTime(String dateFormate) {
         SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
         return dateFormat.format(new Date(System.currentTimeMillis()));
     }

     public static String formatDate(Date date){
         if(date==null){
             return "";
         }
         String dateStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
         return dateStr;
     }

     public static String formatDate(Date date,String formatstr){
         if(date==null){
             return "";
         }
         String dateStr=new SimpleDateFormat(formatstr).format(date);
         return dateStr;
     }


     public static Date parseDate(String date,String format)  {
         SimpleDateFormat sdf = new SimpleDateFormat(format);
         try {
             return sdf.parse(date);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return null;
     }

    public static void main(String[] args) {
        DateUtil util = new DateUtil();
        Date d=DateUtil.stringToDate("20190503","yyyyMMdd");
        Date d1=DateUtil.stringToDate("20190502","yyyyMMdd");
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)-2);
        Date d3= cal.getTime();
        System.out.println(DateUtil.dateToString(d3,"yyyyMMdd"));
        if(d.after(d1)||d.equals(d1) ){
            System.out.println("aa");
        }else {
            System.out.println("bb");
        }
    }
     public static String getMSDateString(){
         return miniFormat.format(new Date());
     }
 }
