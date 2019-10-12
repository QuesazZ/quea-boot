package com.quesa.mybootproject.common.util;

import com.google.common.collect.Lists;

import com.quesa.mybootproject.common.exception.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 *
 */
public class DateUtil {
    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    //月份格式
    public static final String MONTH_FORMAT = "yyyy-MM";

    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 默认时间格式
    public static final String DATETIME_NOSECOND_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    public static final String TIME_HOUSER_FORMAT = "HH:mm";

    public static final String TIME_MONTH_DAY_FORMAT = "MM.dd";

    public static final ThreadLocal<DateFormat> DATE_FORMATTER = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static final String DATE_YEAR_MONTH_FORMAT = "yyyy-MM";

    public static final String DATE_DEFAULT_FORMAT_NO_SPACE = "yyyyMMdd";

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
    private static final Object object = new Object();
    public static final Integer daySecond = 24 * 60 * 60;

    public static final Integer HOUR = 60 * 60 * 1000;

    //时间单位 天
    public static final String MONTH = "month";

    //时间单位 天
    public static final String DAY = "day";

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return Date
     */
    public static Date getNowDate() {
        return new DateTime().toDate();
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return Date
     */
    public static String getNowDateToString() {
        return new DateTime().toString(DATE_DEFAULT_FORMAT);
    }

    /**
     * 获取当前日期时间戳
     *
     * @return
     */
    public static Long getNowTimestamp() {
        return getNowDateTime().getTime();
    }

    /**
     * 获取指定日期时间戳
     *
     * @return
     */
    public static Long getDateTimestamp(Date date) {
        return getDate(date).getTime();
    }

    /**
     * 获取指定日期时间戳
     *
     * @return
     */
    public static Long getDateTimestamp(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return getDate(date).getTime();
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return
     */
    public static Date getNowDateTime() {
        return getDate(new Date());
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return
     */
    public static String getNowDateTimeToString() {
        return new DateTime().toString(DATE_DEFAULT_FORMAT);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return DATE
     */
    public static Date getDate(Date date) {
        try {
            return new DateTime(getDateToString(date)).toDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        try {
            date = date.replace(" ", "T");//把空格替换成T
            return new DateTime(date).toDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间戳转日期
     *
     * @param timeStamp
     * @return
     */
    public static Date getDate(long timeStamp) {
        return new Date(timeStamp);
    }


    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String getDateToString(Date date) {
        return new DateTime(date).toString(DATE_DEFAULT_FORMAT);
    }
    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String getDateMonthToString(Date date) {
        return new DateTime(date).toString(MONTH_FORMAT);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String getDateToStringEx(Date date) {
        if (null == date) {
            return StringUtil.EMPTY_STRING;
        }
        return new DateTime(date).toString(DATE_DEFAULT_FORMAT);
    }

    public static String getDateToStringhour(Date date) {
        if (null == date){
            return StringUtil.EMPTY_STRING;
        }
        return new DateTime(date).toString(TIME_DEFAULT_FORMAT);
    }

    /**
     * 日期格式化yyyy-MM-dd hh:MM:ss
     *
     * @param date
     * @return String
     */
    public static String getDateToStrings(Date date) {
        return new DateTime(date).toString(DATETIME_DEFAULT_FORMAT);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return String
     */
    public static String getDateToString(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).toString(DATE_DEFAULT_FORMAT);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateFormatToString(Date date, String formatStr) {
        if (StringUtils.isNotBlank(formatStr)) {
            return new DateTime(date).toString(formatStr);
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String getDateFormatToString(String date, String formatStr) {
        if (StringUtils.isNotBlank(formatStr)) {
            date = date.replace(" ", "T");//把空格替换成T
            return new DateTime(date).toString(formatStr);
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static Date getDateFormat(Date date, String formatStr) {
        if (StringUtils.isNotBlank(formatStr)) {
            return new DateTime(new DateTime(date).toString(formatStr)).toDate();
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static Date getDateFormat(String date, String formatStr) {
        if (StringUtils.isNotBlank(formatStr)) {
            date = date.replace(" ", "T");//把空格替换成T
            return new DateTime(new DateTime(date).toString(formatStr)).toDate();
        }
        return null;
    }
    //-----------------

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return DATE
     */
    public static Date getDateTime(Date date) {
        try {
            return new DateTime(date).toDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化yyyy-MM-dd hh:MM
     *
     * @param date
     * @return String
     */
    public static String getDateToStringYYYYMMDDHHDD(Date date) {
        return new DateTime(date).toString(DATETIME_NOSECOND_FORMAT);
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return DATE
     */
    public static Date getDateTime(String date) {
        try {
            date = date.replace(" ", "T");//把空格替换成T
            return new DateTime(date).toDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String getDateTimeToString(Date date) {
        return new DateTime(date).toString(DATETIME_DEFAULT_FORMAT);
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return String
     */
    public static String getDateTimeToString(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).toString(DATETIME_DEFAULT_FORMAT);
    }

    /**
     * 获取指定日期年份
     *
     * @param date
     * @return
     */
    public static int geYear(Date date) {
        return new DateTime(date).getYear();
    }

    /**
     * 获取指定日期年份
     *
     * @param date
     * @return
     */
    public static int getYear(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).getYear();
    }

    /**
     * 获取指定日期年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return new DateTime(date).getYear();
    }

    /**
     * 获取指定日期月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return new DateTime(date).getMonthOfYear();
    }

    /**
     * 获取指定日期月份
     *
     * @param date
     * @return
     */
    public static int getMonth(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).getMonthOfYear();
    }

    /**
     * 获取指定日期天数（号）
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        return new DateTime(date).getDayOfMonth();
    }

    /**
     * 获取指定日期天数（号）
     *
     * @param date
     * @return
     */
    public static int getDay(String date) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).getDayOfMonth();
    }


    /**
     * 获取当前年
     *
     * @return
     */
    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 比较两日期大小
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date>otherDate则返回true,否则false
     */
    public static Boolean whenGT(String date, String otherDate) {
        date = date.replace(" ", "T");//把空格替换成T
        otherDate = otherDate.replace(" ", "T");//把空格替换成T
        return whenGT(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期大小
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date>otherDate则返回true,否则false
     */
    public static Boolean whenGT(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDateTime(date);
        Date otherDateTmp = getDateTime(otherDate);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 比较两日期是否相等
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQ(String date, String otherDate) {
        date = date.replace(" ", "T");//把空格替换成T
        otherDate = otherDate.replace(" ", "T");//把空格替换成T
        return whenEQ(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期是否相等
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQ(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDateTime(date);
        Date otherDateTmp = getDateTime(otherDate);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time == 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 比较两日期是否在同一天
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQDay(String date, String otherDate) {
        date = date.replace(" ", "T");//把空格替换成T
        otherDate = otherDate.replace(" ", "T");//把空格替换成T
        return whenEQ(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期是否在同一天
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQDay(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDate(date);
        Date otherDateTmp = getDate(otherDate);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time == 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取提前多少个月
     *
     * @param monty
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }

    /**
     * 获取两日期差，返回年月日差值
     *
     * @param date1
     * @param date2
     * @return
     */
    public static List<Integer> getDiffDates(String date1, String date2) {
        String dates = "";
        List<Integer> diffList = new ArrayList<Integer>();
        try {
            Date startDate = getDate(date1);
            Date endDate = getDate(date2);
            Calendar calS = Calendar.getInstance();
            // 开始时间
            calS.setTime(startDate);
            int startY = calS.get(Calendar.YEAR);
            int startM = calS.get(Calendar.MONTH);
            int startD = calS.get(Calendar.DATE);
            int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 结束时间
            calS.setTime(endDate);
            int endY = calS.get(Calendar.YEAR);
            int endM = calS.get(Calendar.MONTH);
            // 处理起止日期为同一天，默认服务为一天 示例：2016-01-01 至 2016-01-01
            int endD = calS.get(Calendar.DATE) + 1;
            int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            int lday = endD - startD;
            // 每月按照30天计算
            if (endD < startD) {
                endM = endM - 1;
                calS.add(Calendar.MONTH,-1);
                //取上一个月的最大天数
                int actualMaximum = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
                lday = actualMaximum - startD + endD;
            }
            /*
             * 按照正常日期计算 if (lday<0) { endM = endM -1; lday = startDayOfMonth+
			 * lday; }
			 */
            // 处理服务天数问题，示例：2016-01-01 到 2017-12-31 实际上是1年
            if (lday == endDayOfMonth) {
                endM = endM + 1;
                lday = 0;
            }
            int mos = (endY - startY) * 12 + (endM - startM);
            int lyear = mos / 12;
            int lmonth = mos % 12;
            diffList.add(lyear);
            diffList.add(lmonth);
            diffList.add(lday);
            dates = lyear + "年" + lmonth + "个月" + lday + "天";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffList;
    }

    /**
     * 获取两日期差，返回年月日差值
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Integer> getDiffDates(Date startDate, Date endDate) {
        String dates = "";
        List<Integer> diffList = new ArrayList<Integer>();
        try {
            Calendar calS = Calendar.getInstance();
            // 开始时间
            calS.setTime(startDate);
            int startY = calS.get(Calendar.YEAR);
            int startM = calS.get(Calendar.MONTH);
            int startD = calS.get(Calendar.DATE);
            int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 结束时间
            calS.setTime(endDate);
            int endY = calS.get(Calendar.YEAR);
            int endM = calS.get(Calendar.MONTH);
            // 处理起止日期为同一天，默认服务为一天 示例：2016-01-01 至 2016-01-01
            int endD = calS.get(Calendar.DATE) + 1;
            int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            int lday = endD - startD;
            // 每月按照30天计算
            if (endD < startD) {
                endM = endM - 1;
                lday = 30 - startD + endD;
            }
            /*
             * 按照正常日期计算 if (lday<0) { endM = endM -1; lday = startDayOfMonth+
			 * lday; }
			 */
            // 处理服务天数问题，示例：2016-01-01 到 2017-12-31 实际上是1年
            if (lday == endDayOfMonth) {
                endM = endM + 1;
                lday = 0;
            }
            int mos = (endY - startY) * 12 + (endM - startM);
            int lyear = mos / 12;
            int lmonth = mos % 12;
            diffList.add(lyear);
            diffList.add(lmonth);
            diffList.add(lday);
            dates = lyear + "年" + lmonth + "个月" + lday + "天";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffList;
    }

    /**
     * 获取两日期相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDiffDays(Date startDate, Date endDate) {
        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) {
            return 0;
        }
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return String 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        date = date.replace(" ", "T");//把空格替换成T
        return new DateTime(date).plusMonths(yearAmount).toString();
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return new DateTime(date).plusMonths(yearAmount).toDate();
    }

    /**
     * 增加日期的月份。失败返回null
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return String 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        date = date.replace(" ", "T");//把空格替换成T
        return getDateToString(new DateTime(date).plusMonths(monthAmount).toDate());
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return new DateTime(date).plusMonths(monthAmount).toDate();
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        date = date.replace(" ", "T");//把空格替换成T
        String s = new DateTime(date).plusDays(dayAmount).toString();
        if (s.contains("T")) {
            s = s.split("T")[0];
        }
        return s;
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return new DateTime(date).plusDays(dayAmount).toDate();
    }

    /**
     * 设置指定日期（天）。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date setDay(Date date, int dayAmount) {
        DateTime dateTime = new DateTime(date);
        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dayAmount, dateTime.getHourOfDay(), dateTime.getMillisOfDay()).toDate();
    }

    /**
     * 设置指定日期（天）。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date setDay(String date, int dayAmount) {
        date = date.replace(" ", "T");//把空格替换成T
        DateTime dateTime = new DateTime(date);
        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dayAmount, dateTime.getHourOfDay(), dateTime.getMillisOfDay()).toDate();
    }

    /**
     * 设置固定日期，如果输入数字大于该月最大日期那么默认为当月最大日期
     *
     * @param date
     * @param dayAmount
     * @return
     */
    public static Date setDayPlus(String date, int dayAmount,String maxDateStr) {
        date = date.replace(" ", "T");//把空格替换成T
        DateTime dateTime = new DateTime(date);
        if (dayAmount >= 29) {
            Date[] dates = DateUtil.computeMonthFirstAndLastDay(DateUtil.getDate(date), 0);
            String dateStr = DateUtil.getDateToString(dates[1]);
            String dayStr = dateStr.substring(dateStr.length() - 2);
            Integer day = Integer.valueOf(dayStr);
            if (dayAmount > day) {
                {
                    dayAmount = day;
                }
            }
        }
        Date retDate = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dayAmount, dateTime.getHourOfDay(), dateTime.getMillisOfDay()).toDate();
        clearHHMMSS(retDate);
        Date maxDate = DateUtil.getDate(maxDateStr);
        clearHHMMSS(maxDate);
        if (retDate.after(maxDate)) {
            retDate = maxDate;
        }
        return retDate;
    }

    /**
     * 获取日期是一周中的第几天 每周从周一开始
     *
     * @param date
     * @return
     */
    public static int getDateInWeekFewDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String key = sdf.format(getDateFormat(date, DATE_DEFAULT_FORMAT));
        if ("星期一".equals(key) || "Mon".equals(key) || "MON".equals(key)) {
            return 1;
        } else if ("星期二".equals(key) || "Tue".equals(key) || "TUE".equals(key)) {
            return 2;
        } else if ("星期三".equals(key) || "Wed".equals(key) || "WED".equals(key)) {
            return 3;
        } else if ("星期四".equals(key) || "Thu".equals(key) || "THU".equals(key)) {
            return 4;
        } else if ("星期五".equals(key) || "Fri".equals(key) || "FRI".equals(key)) {
            return 5;
        } else if ("星期六".equals(key) || "Sat".equals(key) || "SAT".equals(key)) {
            return 6;
        }
        return 7;
    }

    /**
     * 计算得到日期区间的所有日期
     * 例如：from=2017-06-25 to=2017-06-28 那么他们之间的日期就是 2017-06-26 2017-06-27
     *
     * @param from
     * @param to
     * @return
     */
    public static List<Date> computeDateRangeDate(Date from, Date to) {
        List<Date> dateRange = new ArrayList<Date>();
        from = DateUtil.getDateFormat(from, DATE_DEFAULT_FORMAT);
        to = DateUtil.getDateFormat(to, DATE_DEFAULT_FORMAT);
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(getDateFormat(from, DATE_DEFAULT_FORMAT));
        startCal.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(getDateFormat(to, DATE_DEFAULT_FORMAT));
        while (startCal.before(endCal)) {
            dateRange.add(startCal.getTime());
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateRange;
    }

    /**
     * 获取指定日期所在月总天数
     *
     * @param sourceDate
     * @return
     */
    public static int getMonthDayCount(Date sourceDate) {
        Calendar d = Calendar.getInstance();
        d.setTime(sourceDate);
        return d.getActualMaximum(Calendar.DATE);
    }
    /**
     * 获取指定日期所在月最后一天
     *
     * @param sourceDate
     * @return
     */
    public static Date getMonthLastDay(Date sourceDate) {
        Calendar d = Calendar.getInstance();
        d.setTime(sourceDate);
        d.set(Calendar.DAY_OF_MONTH,d.getActualMaximum(Calendar.DATE));
        return d.getTime();
    }
    /**
     * 获取指定日期所在月第一天
     *
     * @param sourceDate
     * @return
     */
    public static Date getMonthFirstDay(Date sourceDate) {
        Calendar d = Calendar.getInstance();
        d.setTime(sourceDate);
        d.set(Calendar.DAY_OF_MONTH,1);
        return d.getTime();
    }
    /**
     * Date转换为LocalDate
     */
    public static LocalDate dateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }
    /**
     * LocalDate转换为Date
     */
    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    /**
     * 计算某月一号和最后一天
     *
     * @param sourceDate    源日期
     * @param monthInterval 目标月与当前月月差值（正负均可）
     * @return
     */
    public static Date[] computeMonthFirstAndLastDay(Date sourceDate, int monthInterval) {
        if (null == sourceDate) {
            throw new ParamException("源日期不能为空！");
        }
        Date[] destDates = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);
        cal.add(Calendar.MONTH, monthInterval);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        destDates[0] = DateUtil.getDate(DATE_FORMATTER.get().format(cal.getTime()));
        if (monthInterval < 0) {
            cal.setTime(sourceDate);
            if (monthInterval < -1) {
                cal.add(Calendar.MONTH, monthInterval + 1);
            }
        }
        if (monthInterval >= 0) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_MONTH, 0);
        destDates[1] = DateUtil.getDate(DATE_FORMATTER.get().format(cal.getTime()));

        return destDates;
    }

    /**
     * 获取当前日期的下一天
     *
     * @return
     */
    public static String getAfterDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return DateUtil.getDateToString(date);
    }

    /**
     * 获取当前日期的前一天
     *
     * @return
     */
    public static String getBeforeDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return DateUtil.getDateToString(date);
    }

    /**
     * 获取两日期相差的天数(格式转为YMD格式 消除HMS带来的误差)
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDiffDaysEx(Date startDate, Date endDate) {
        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) {
            return 0;
        }
        try {
            DateFormat df = DATE_FORMATTER.get();
            startDate = df.parse(df.format(startDate));
            endDate = df.parse(df.format(endDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24)) + 1;
    }

    /**
     * 比较两日期大小
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date>otherDate则返回true,否则false
     */
    public static Boolean whenGTOrEQ(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDateTime(date);
        Date otherDateTmp = getDateTime(otherDate);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time >= 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 计算得到日期区间的所有日期（包含开始日期）
     * 例如：from=2017-06-25 to=2017-06-28 那么他们之间的日期就是 2017-06-25 2017-06-26 2017-06-27
     *
     * @param from
     * @param to
     * @return
     */
    public static List<Date> computeDateRangeDateEx(Date from, Date to) {
        List<Date> dateRange = new ArrayList<Date>();
        from = DateUtil.getDateFormat(from, DATE_DEFAULT_FORMAT);
        to = DateUtil.getDateFormat(to, DATE_DEFAULT_FORMAT);
        dateRange.add(from);
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(getDateFormat(from, DATE_DEFAULT_FORMAT));
        startCal.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(getDateFormat(to, DATE_DEFAULT_FORMAT));
        while (startCal.before(endCal)) {
            dateRange.add(startCal.getTime());
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dateRange;
    }

    /**
     * 格式化日期 yyyy-MM
     *
     * @param date
     * @return
     */
    public static Date getYearMonth(Date date) {
        return getDate(new DateTime(date).toString(DATE_YEAR_MONTH_FORMAT));
    }
    /**
     * 格式化日期 yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getYearMonthToString(Date date) {
        return new DateTime(date).toString(DATE_YEAR_MONTH_FORMAT);
    }

    /**
     * 格式化日期 yyyy-MM 月份-1
     *
     * @param date
     * @return
     */
    public static Date getPreYearMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static int compareYearMonth(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        int y1 = c1.get(Calendar.YEAR);
        int m1 = c1.get(Calendar.MONTH);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int y2 = c2.get(Calendar.YEAR);
        int m2 = c2.get(Calendar.MONTH);
        if (y1 == y2) {
            return m1 - m2;
        }
        return y1 - y2;
    }

    public static int compareYearMonthDay(Date d1, Date d2) {
        int compareYearMonth = compareYearMonth(d1, d2);
        if (compareYearMonth == 0) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(d1);
            int day1 = c1.get(Calendar.DAY_OF_MONTH);

            Calendar c2 = Calendar.getInstance();
            c2.setTime(d2);
            int day2 = c2.get(Calendar.DAY_OF_MONTH);
            return day1 - day2;
        }
        return compareYearMonth;
    }

    /**
     * 给日期加上小时
     *
     * @param date yyyy-MM-dd格式的日期
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        //当前日期毫秒数
        long currentTime = date.getTime();
        //计算小时总计的毫秒数
        long hourTime = hour * 60 * 60 * 1000;
        //总的毫秒数
        long totalTime = currentTime + hourTime;
        return new Date(totalTime);
    }


    /**
     * 判断两个日期区间是否存在交叉
     *
     * @param firstBeginDate
     * @param firstEndDate
     * @param secondBeginDate
     * @param secondEndDate
     * @return 如果有交叉返回true  否则返回false
     */
    public static boolean checkDateRangeCross(Date firstBeginDate,Date firstEndDate,Date secondBeginDate,Date secondEndDate){
        return ((firstBeginDate.getTime() >= secondBeginDate.getTime())
                        && firstBeginDate.getTime() < secondEndDate.getTime())
                        ||
                        ((firstBeginDate.getTime() > secondBeginDate.getTime())
                                && firstBeginDate.getTime() <= secondEndDate.getTime())
                        ||
                        ((secondBeginDate.getTime() >= firstBeginDate.getTime())
                                && secondBeginDate.getTime() < firstEndDate.getTime())
                        ||
                        ((secondBeginDate.getTime() > firstBeginDate.getTime())
                                && secondBeginDate.getTime() <= firstEndDate.getTime());
    }

    /**
     * 判断两个日期区间是否存在交叉
     *
     * @param firstBeginDate
     * @param firstEndDate
     * @param secondBeginDate
     * @param secondEndDate
     * @return 如果有交叉返回true  否则返回false
     */
    public static boolean checkDateRangeCross(long firstBeginDate,long firstEndDate,long secondBeginDate,long secondEndDate){
        return ((firstBeginDate >= secondBeginDate)
                && firstBeginDate < secondEndDate)
                ||
                ((firstBeginDate > secondBeginDate)
                        && firstBeginDate <= secondEndDate)
                ||
                ((secondBeginDate >= firstBeginDate)
                        && secondBeginDate < firstEndDate)
                ||
                ((secondBeginDate > firstBeginDate)
                        && secondBeginDate <= firstEndDate);
    }

    /**
     * 判断两个日期区间存在交叉的时间段
     *
     * @param smallBeginDate
     * @param smallEndDate
     * @param bigBeginDate
     * @param bigEndDate
     * @return 如果存在交叉，就返回交叉的是简单，不存在交叉就返回空
     */
    public static List<Date> dateRangeCrossTime(Date smallBeginDate,Date smallEndDate,Date bigBeginDate,Date bigEndDate){
        if (!checkDateRangeCross(smallBeginDate,smallEndDate,bigBeginDate,bigEndDate)) {
            return null;
        }
        List<Date> dateList = Lists.newArrayList();
        if (whenGT(smallBeginDate,bigBeginDate) && whenGT(bigEndDate,smallEndDate)) {
            dateList.add(smallBeginDate);
            dateList.add(smallEndDate);
        } else if (whenGT(smallBeginDate,bigBeginDate) && whenGT(smallEndDate,bigEndDate)) {
            dateList.add(smallBeginDate);
            dateList.add(bigEndDate);
        } else if (whenGT(bigBeginDate,smallBeginDate) && whenGT(bigEndDate,smallEndDate)) {
            dateList.add(bigBeginDate);
            dateList.add(smallEndDate);
        } else if (whenGT(bigBeginDate,smallBeginDate) && whenGT(smallEndDate,bigEndDate)) {
            dateList.add(bigBeginDate);
            dateList.add(bigEndDate);
        }
        return dateList;
    }

    //小红点项目 获取开始显示红点的日期
    public static Date getRedSpotDate(){
        Date redSpotDate = DateUtil.getDate("2018-2-21");
        return redSpotDate;
    }

    /**
     * 获得两个日期之间的所有月份
     * @param minDate
     * @param maxDate
     * @return
     * @throws Exception
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 获得两个日期之间的所有日期
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private static List<Date> dateSplit(Date startDate, Date endDate) throws Exception {
        if (!startDate.before(endDate)){
            throw new Exception("开始时间应该在结束时间之后");
        }
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }

    /**
     * 获取某一时间段特定星期几的日期
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param weekDays 星期几
     * @param dateTime 具体时间
     * getDates("2013-04-01", "2014-04-14","星期一|星期二|星期日");
     * @return 返回时间数组
     */
    public static List<Date> getDates(Date beginTime, Date endTime, String weekDays, Date dateTime) throws Exception {
        String dateFrom = null;
        String dateEnd = null;
        long time = 1L;
        long perDayMilSec = 24 * 60 * 60 * 1000;
        List<Date> dateList = new ArrayList<>();
        SimpleDateFormat sdfhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");
        //需要查询的星期系数
        String strWeekNumber = weekForNum(weekDays);
        String houreTime = null;
        if(dateTime!=null){
            houreTime = hms.format(dateTime);
        }
        try {
            dateFrom = sdf.format(beginTime.getTime() - perDayMilSec);
            dateEnd = sdf.format(endTime);
            while (true) {
                time = sdf.parse(dateFrom).getTime();
                time = time + perDayMilSec;
                Date date = new Date(time);
                dateFrom = sdf.format(date);
                if (dateFrom.compareTo(dateEnd) <= 0) {
                    //查询的某一时间的星期系数
                    Integer weekDay = dayForWeek(date);
                    //判断当期日期的星期系数是否是需要查询的
                    if (strWeekNumber.indexOf(weekDay.toString())!=-1) {
//                        Date timeDate = sdf.parse(dateFrom+" "+houreTime);
                        //原有的清空的时间保留了日期
                        Date timeDate = sdfhms.parse(dateFrom+" "+houreTime);
                        dateList.add(timeDate);
                    }
                } else {
                    break;
                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return dateList;
    }

    //等到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer dayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 得到对应星期的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     * @param weekDays 星期格式  星期一|星期二
     */
    public static String weekForNum(String weekDays){
        //返回结果为组合的星期系数
        String weekNumber = "";
        //解析传入的星期
        if(weekDays.indexOf("|")!=-1){//多个星期数
            String []strWeeks = weekDays.split("\\|");
            for(int i=0;i<strWeeks.length;i++){
                weekNumber = weekNumber + "" + getWeekNum(strWeeks[i]).toString();
            }
        }else{//一个星期数
            weekNumber = getWeekNum(weekDays).toString();
        }

        return weekNumber;

    }

    //将星期转换为对应的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer getWeekNum(String strWeek){
        Integer number = 1;//默认为星期日
        if("星期日".equals(strWeek)){
            number = 1;
        }else if("星期一".equals(strWeek)){
            number = 2;
        }else if("星期二".equals(strWeek)){
            number = 3;
        }else if("星期三".equals(strWeek)){
            number = 4;
        }else if("星期四".equals(strWeek)){
            number = 5;
        }else if("星期五".equals(strWeek)){
            number = 6;
        }else if("星期六".equals(strWeek)){
            number = 7;
        }
        return number;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取时间段内固定日期集合
     * @param start
     * @param end
     * @param dayOfMonth
     * @return
     */
    public static List<Date> getFixedDateList(Date start,Date end,int dayOfMonth){
        List<Date> dateList = new ArrayList<>();
        List<Integer> diff = getDiffDates(start,end);
//        int totalMonth = diff.get(0)*12 + diff.get(1) + (diff.get(2) > 0?1:0);
        int totalMonth = diff.get(1);
        //如果目标天小于开始日期的天那么从下月开始
        if (DateUtil.getDay(start) <= dayOfMonth){
            Date[] dates = DateUtil.computeMonthFirstAndLastDay(DateUtil.getDate(start), 0);
            String dateStr = DateUtil.getDateToString(dates[1]);
            String dayStr = dateStr.substring(dateStr.length() - 2);
            Integer day = Integer.valueOf(dayStr);
            if (dayOfMonth <= day) {
                String computeDate = dateStr.substring(0,dateStr.lastIndexOf("-")+1);
                Date targetDate = DateUtil.getDate(computeDate + dayOfMonth);
                dateList.add(targetDate);
            }
        }
        //如果目标天大于结束日期的天那么只计算到结束日期的上个月
        if (DateUtil.getDay(end) < dayOfMonth){
            totalMonth --;
        }
        String sourceDate = DateUtil.getDateToString(start);
        for (int i = 0;i<totalMonth;i++){
            sourceDate = DateUtil.getDateToString(DateUtil.addMonth(sourceDate,1));
            String computeDate = sourceDate.substring(0,sourceDate.lastIndexOf("-")+1);
            Date[] dates = DateUtil.computeMonthFirstAndLastDay(DateUtil.getDate(sourceDate), 0);
            String dateStr = DateUtil.getDateToString(dates[1]);
            String dayStr = dateStr.substring(dateStr.length() - 2);
            Integer day = Integer.valueOf(dayStr);
            if (dayOfMonth <= day) {
                Date targetDate = DateUtil.getDate(computeDate + dayOfMonth);
                dateList.add(targetDate);
            }
        }
        return dateList;
    }

    /**
     * 获取时间段内固定日期时间集合
     * @param start
     * @param end
     * @param dateTime
     * @return
     */
    public static List<Date> getFixedDateTimeList(Date start,Date end,Date dateTime){
        List<Date> dateList = new ArrayList<>();
        List<Integer> diff = getDiffDates(start,end);
        String datetime = getDateFormatToString(dateTime,DATETIME_DEFAULT_FORMAT);
        int dayOfMonth = Integer.parseInt(datetime.substring(8,10));
        String hms = datetime.substring(11,19);
//        int totalMonth = diff.get(0)*12 + diff.get(1) + (diff.get(2) > 0?1:0);
        int totalMonth = diff.get(1);
        //如果目标天小于开始日期的天那么从下月开始
        if (DateUtil.getDay(start) <= dayOfMonth){
            Date[] dates = DateUtil.computeMonthFirstAndLastDay(DateUtil.getDate(start), 0);
            String dateStr = DateUtil.getDateToString(dates[1]);
            String dayStr = dateStr.substring(dateStr.length() - 2);
            Integer day = Integer.valueOf(dayStr);
            if (dayOfMonth <= day) {
                String computeDate = dateStr.substring(0,dateStr.lastIndexOf("-")+1);
                computeDate = computeDate + dayOfMonth + " " +hms;
                Date targetDate = DateUtil.getDate(computeDate);
                dateList.add(targetDate);
            }
        }
        //如果目标天大于结束日期的天那么只计算到结束日期的上个月
        if (DateUtil.getDay(end) < dayOfMonth){
            totalMonth --;
        }
        String sourceDate = DateUtil.getDateToString(start);
        for (int i = 0;i<totalMonth;i++){
            sourceDate = DateUtil.getDateToString(DateUtil.addMonth(sourceDate,1));
            String computeDate = sourceDate.substring(0,sourceDate.lastIndexOf("-")+1);
            Date[] dates = DateUtil.computeMonthFirstAndLastDay(DateUtil.getDate(sourceDate), 0);
            String dateStr = DateUtil.getDateToString(dates[1]);
            String dayStr = dateStr.substring(dateStr.length() - 2);
            Integer day = Integer.valueOf(dayStr);
            if (dayOfMonth <= day) {
                computeDate = computeDate + dayOfMonth + " " +hms;
                Date targetDate = DateUtil.getDate(computeDate);
                dateList.add(targetDate);
            }
        }
        return dateList;
    }


    /**
     * 获取固定日期
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> getData(Date begin, Date end, int day)
    {
        Calendar l_begin = Calendar.getInstance();
        l_begin.clear();
        l_begin.setTime(begin);

        Calendar l_end = Calendar.getInstance();
        l_end.clear();
        l_end.setTime(end);
        List<Date> list = new ArrayList<Date>();
        boolean ifOk = true;
        if (day == l_begin.get(Calendar.DATE))
        {
            list.add(new Date(l_begin.getTimeInMillis()));
        }
        do
        {
            l_begin.add(Calendar.DATE, 1);
            if (day == l_begin.get(Calendar.DATE) && l_begin.compareTo(l_end) == -1)
            {
                list.add(new Date(l_begin.getTimeInMillis()));
            }
            if (l_begin.compareTo(l_end) == 1)
            {
                ifOk = false;
            }
        } while (ifOk);
        return list;
    }

    /**
    * @Description:  yyyyMMddHHmmss字符串转化为日期
    * @Param: [time]
    * @return: java.util.Date
    * @Author: cx
    * @Date: 2018/5/17
    */
    public static Date getStringTimeToDate(String time,String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Description:  yyyyMMddHHmmss字符串转化为日期
     * @Param: [time]
     * @return: java.util.Date
     * @Author: cx
     * @Date: 2018/5/17
     */
    public static boolean dateEqInMinute(Date date,Date otherDate)
    {
        String dateStr = getDateFormatToString(date,"yyyyMMddHHmm");
        String otherDateStr = getDateFormatToString(otherDate,"yyyyMMddHHmm");
        if(dateStr != null && otherDateStr!= null) {
            return dateStr.equals(otherDateStr);
        }
        return false;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date
     *            日期
     * @param dateType
     *            日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType)
    {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null)
        {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 小时
     */
    public static int getHour(String date)
    {
        return getHour(getDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date
     *            日期
     * @return 小时
     */
    public static int getHour(Date date)
    {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 分钟
     */
    public static int getMinute(String date)
    {
        return getMinute(getDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date
     *            日期
     * @return 分钟
     */
    public static int getMinute(Date date)
    {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date)
    {
        return getSecond(getDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date
     *            日期
     * @return 秒钟
     */
    public static int getSecond(Date date)
    {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param secondAmount
     *            增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount)
    {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null 一天86400秒
     *
     * @param date
     *            日期
     * @param secondAmount
     *            增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount)
    {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date
     *            日期字符串
     * @param dateType
     *            类型
     * @param amount
     *            数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount)
    {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null)
        {
            Date myDate = getDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = getString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date
     *            日期
     * @param dateType
     *            类型
     * @param amount
     *            数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount)
    {
        Date myDate = null;
        if (date != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param dateStyle
     *            日期风格
     * @return 日期
     */
    public static Date getDate(String date, DateStyle dateStyle)
    {
        Date myDate = null;
        if (dateStyle != null)
        {
            myDate = getDate(date, dateStyle.getValue());
        }
        return myDate;
    }
    /**
     * 将日期转化为指定格式日期。失败返回null。
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 日期
     * @throws RuntimeException
     *             爬出运行时异常
     * @throws ParseException
     *             转换异常
     */
    public static Date getDate(Date date, String pattern)
    {
        Date myDate = null;
        if (date != null)
        {
            String dateStr = getString(date, pattern);
            try
            {
                myDate = getDateFormat(pattern).parse(dateStr);
            } catch (ParseException | RuntimeException e)
            {
            }
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date
     *            日期
     * @param dateStyle
     *            日期风格
     * @return 日期字符串
     */
    public static String getString(Date date, DateStyle dateStyle)
    {
        String dateString = null;
        if (dateStyle != null)
        {
            dateString = getString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 日期字符串
     */
    public static String getString(Date date, String pattern)
    {
        String dateString = "";
        if (date != null)
        {
            try
            {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e)
            {
            }
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return 日期
     */
    public static Date getDate(String date, String pattern)
    {
        Date myDate = null;
        if (date != null)
        {
            try
            {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e)
            {

            }
        }
        return myDate;
    }

    /**
     * 获取日期字符串的日期风格。失败返回null。
     *
     * @param date
     *            日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date)
    {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values())
        {
            if (style.isShowOnly())
            {
                continue;
            }
            Date dateTmp = null;
            if (date != null)
            {
                try
                {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length())
                    {
                        dateTmp = null;
                    }
                } catch (Exception e)
                {
                }
            }
            if (dateTmp != null)
            {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null)
        {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }
    /**
     * 获取精确的日期
     *
     * @param timestamps
     *            时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps)
    {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0)
        {
            if (timestamps.size() > 1)
            {
                for (int i = 0; i < timestamps.size(); i++)
                {
                    for (int j = i + 1; j < timestamps.size(); j++)
                    {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty())
                {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++)
                    {
                        if (minAbsoluteValue > absoluteValues.get(i))
                        {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1)
                {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1)
                    {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else
            {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0)
        {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern
     *            日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException
     *             异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException
    {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null)
        {
            synchronized (object)
            {
                if (dateFormat == null)
                {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }
    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }
    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getDayOfMonth(int year, int month){
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期距离今天结束还多少秒
     * @param currentDate
     * @return
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    /**
     * 获取哪个月第一天日期
     * @param offset  偏移量  0 是当月 -1 上月 以此类推
     * @return
     */
    public static Date getFirstDayMonth(int offset) {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, offset);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return getDate(c.getTime());
    }
    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }
    /**
     * 获得某月最后一天的日期
     * @return
     */
    public static Date getLastDayOfMonth(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }
    /**
     * 获得某月最后一天的日期
     * @return
     */
    public static Date getLastDayOfMonth(final String date) {
        return getLastDayOfMonth(DateUtil.getDate(date));
    }
    /**
     * 获取两个日期相差多少小时
     * @param date
     * @param otherDate
     * @return
     */
    public static long getDiffHour(Date date,Date otherDate) {
        return Math.abs((date.getTime() - otherDate.getTime()) / HOUR);
    }

    /**
     * 判断选择的日期是否是指定周 仅比较同一年
     * @param date  给定判断时间
     * @param offset  偏移量 0 为本周 -1 为上周
     * @return
     */
    public static boolean isDesignWeek(Date date,int offset){
        if (getYear(date) != getYear(new Date())) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if(paramWeek - currentWeek == offset){
            return true;
        }
        return false;
    }
    /**
     * 判断选择的日期是否是指定天 仅比较同一年
     * @param date  给定判断时间
     * @param offset  偏移量 0 为当天 -1 为昨天
     * @return
     */
    public static boolean isDesignDay(Date date,int offset) {
        if (getYear(date) != getYear(new Date())) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(date);
        int compareDay = calendar.get(Calendar.DAY_OF_YEAR);
        if (compareDay - currentDay == offset) {
            return true;
        }
        return false;
    }
    /**
     * 判断选择的日期是否是指定月、仅比较同一年
     * @param date  给定判断时间
     * @param offset  偏移量 0 为当月 -1 为上个月
     * @return
     */
    public static boolean isDesignMonth(Date date,int offset) {
        if (getYear(date) != getYear(new Date())) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.setTime(date);
        int compareMonth = calendar.get(Calendar.MONTH);
        if (compareMonth - currentMonth == offset) {
            return true;
        }
        return false;
    }
    /**
     * 获取给定日期上周一和上周日的日期集合
     * @param date  给定时间
     * @return
     */
    public static List<Date> getLastWeek(Date date) {
        Calendar monCalenday = Calendar.getInstance();
        Calendar sunCalenday = Calendar.getInstance();
//        monCalenday.setFirstDayOfWeek(Calendar.MONDAY);
//        sunCalenday.setFirstDayOfWeek(Calendar.MONDAY);
        monCalenday.setTime(date);
        sunCalenday.setTime(date);
        //设置时间成本周第一天(周日)
        monCalenday.set(Calendar.DAY_OF_WEEK,1);
        sunCalenday.set(Calendar.DAY_OF_WEEK,1);

        //上周日时间
        sunCalenday.add(Calendar.DATE, 0);
        //上周一时间
        monCalenday.add(Calendar.DATE, -6);
        return Lists.newArrayList(monCalenday.getTime(),sunCalenday.getTime());
    }


    /**
     * 获取两个日期相差几个月
     * @param start
     * @param end
     * @return
     */
    public static int getDiffMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
    }
    public static void main(String[] args) {
//        try {
//            getFixedDateList(getDate("2013-04-01"),getDate("2014-04-14"),10);
////            String[] dateList = getDates("2013-04-01", "2014-04-14","星期一|星期二|星期日",new Date());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String time = "180521140000";
        System.out.println(getStringTimeToDate(time,"yyMMddHHmmss"));
//        String time = "180521140000";
//        System.out.println(getStringTimeToDate(time,"yyMMddHHmmss"));

//        Date beginTime = DateUtil.getStringTimeToDate("180608140000","yyMMddHHmmss");
//        Date endTime = DateUtil.getStringTimeToDate("180606140130","yyMMddHHmmss");
//        Date toDate = DateUtil.addDay(endTime, 2);
//        boolean flag = DateUtil.dateEqInMinute(beginTime,toDate);
//        System.out.println(flag);
//        Date[] date = computeMonthFirstAndLastDay(getDate("2016-11"),0);
//        for(int i=0; i<date.length; i++){
//        }
        Date monthLastDay = getMonthLastDay(new Date());
        System.out.println(getDateToString(monthLastDay));

//        Date d1 = DateUtil.getDate("2017-1-31");
//        Date d3 = DateUtil.getDate("2017-10-03");
//        Date d4 = DateUtil.getDate("2017-12-30");
        Integer diffDays = DateUtil.getDiffDays(getDate("2018-08-19"), getDate("2018-08-24"));
        System.out.println(diffDays);
    }
    /**
     * 获得下个月的日期
     *
     * @param date
     * @return
     */
    public static Date nextMonthDate(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }
    /**
     * 清空时分秒
     * @param date
     * @return
     */
    public static void clearHHMMSS(Date date){
        if (date==null) {
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 将时分秒,毫秒域清零
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date.setTime(cal.getTime().getTime());
    }

    /***
     * @Author 程达
     * @Description  根据20180807184921 转换2018-08-07 18:49:21
     * @Date 18:57 2018/8/7
     */
    public static String getStringTime(String str){
        if(StringUtil.isNotEmpty(str)){
            String year = str.substring(0, 4);
            String moneth = str.substring(4, 6);
            String day = str.substring(6, 8);
            String housr = str.substring(8, 10);
            String minute = str.substring(10,12);
            String second = str.substring(12, 14);
            String time = year+"-"+moneth+"-"+day+" "+housr+":"+minute+":"+second;
            return time;
        }else{
            return "";
        }
    }


    public static int getMonthBetweenDate(Date startDateStr, Date endDateStr){
        int month = 0;
        Calendar calS = Calendar.getInstance();
        calS.setTime(startDateStr);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDateStr);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        //处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE)+1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();
        if (endDateStr.compareTo(startDateStr)<0) {
            return month;
        }
        int lday = endD-startD;
        if (lday<0) {
            endM = endM -1;
            lday = startDayOfMonth+ lday;
        }
        //处理天数问题，如：2011-01-01 到 2013-12-31  2年11个月31天     实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM+1;
            lday =0;
        }
        int mos = (endY - startY)*12 + (endM- startM);
        int lyear = mos/12;
        int lmonth = mos%12;
        month = lyear*12+lmonth;
        return month;
    }



}
