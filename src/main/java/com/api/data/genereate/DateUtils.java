package com.api.data.genereate;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间转换工具类
 */
public class DateUtils {


    public final static String YYYY = "yyyy";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String MM_DDHHMMSSSS = "MM-dd HH:mm:ss";
    public final static String HHMM = "HH:mm";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String MM_DD = "MM-dd";
    public final static String HH_MM_SS = "HH:mm:ss";

    /**
     * 返回YYYY-MM 为格式的时间字符
     *
     * @return String
     */
    public static String getSysmonth() {
        return DateTime.now().toString(YYYY_MM);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdate() {
        return DateTime.now().toString(YYYY_MM_DD);
    }

    /**
     * 返回YYYY-MM-DD HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystime() {
        return DateTime.now().toString(YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 返回MM-ddHH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getDateAndTime() {
        return DateTime.now().toString(MM_DDHHMMSSSS);
    }

    /**
     * 返回HH:mm 为格式的时间字符
     *
     * @return String
     */
    public static String getTime() {
        return DateTime.now().toString(HHMM);
    }

    /**
     * @param format
     * @return
     */
    public static String dateToStr(String format) {
        return DateTime.now().toString(format);
    }

    /**
     * 返回YYYYMMDDHHmmss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystimeNoSign() {
        return DateTime.now().toString(YYYYMMDDHHMMSS);
    }

    /**
     * 返回YYYYMMDD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdateNoSign() {
        return DateTime.now().toString(YYYYMMDD);
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 返回当前年
     *
     * @return int
     */
    public static int getYear() {
        return DateUtil.year(DateUtil.date());
    }

    /**
     * 返回当前月份
     *
     * @return int
     */
    public static int getMonth() {
        return DateUtil.month(DateUtil.date());
    }

    /**
     * 返回当天是当月的第几号
     *
     * @return int
     */
    public static int getDay() {
        return DateUtil.dayOfMonth(DateUtil.date());
    }

    /**
     * 返回当前小时
     *
     * @return String
     */
    public static int getHour() {
        return DateUtil.thisHour(true);
    }

    /**
     * 返回当前毫秒数
     *
     * @return String
     */
    public static long getMillisecond() {
        return DateUtil.millsecond(DateUtil.date());
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且日加上一年
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddAYear() {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.YEAR, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且日加上一天
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddADay() {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 返回YYYY-MM-DD 为格式的时间字符,并且小时加上传入的个数
     *
     * @return String
     * @author zpy
     */
    public static String getSysdateAddHour(int i) {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, i);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }


    /**
     * 获取几天前的时间 时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param beforeDay
     * @return
     */
    public static String getByBeforeDay(int beforeDay) {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -beforeDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String towDaysBefore = sdf.format(cal1.getTime());
        return towDaysBefore;
    }

    /**
     * 比较日期dt1 在dt2前返回1，dt1在dt2后返回-1，相等返回0
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 把日期字符串转换为具体日期刊
     *
     * @param dateString
     * @param format     可以是yyyy-MM-dd HH:mm:ss，yyyyMMddHHmmss，yyyy-MM-dd任何需要得到的模式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {
        Date tempDate = null;
        if (dateString == null) {
            return tempDate;
        }
        if ("".equals(dateString)) {
            return tempDate;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            tempDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tempDate;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static String getYearLastDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD).format(currYearLast);
    }

    /**
     * 相差时间天数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuot(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 根据传入参数，返回卡证有效截止日期
     *
     * @param isSpanPeriod 是否跨自然周期
     * @param finalEndDate 固定截止日期
     *                     IsSpanPeriod=1 返回当前时间加一年
     *                     IsSpanPeriod=0 用FinalEndDate和当前年末比较，谁更早，返回谁
     * @return
     */
    public static String getCardEndDate(String isSpanPeriod, String finalEndDate) {
        if (isSpanPeriod == null) {
            return "";
        }
        if ("1".equals(isSpanPeriod)) {
            return getSysdateAddAYear() + " 24:00:00";
        } else if ("0".equals(isSpanPeriod)) {
            String aa = getYearLastDay(getYear()) + " 24:00:00";
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                if (finalEndDate == null) {
                    return aa;
                }
                int num = compareDate(dateformat.parse(aa), dateformat.parse(finalEndDate));
                if (num == -1) {
                    return aa;
                } else if (num >= 0) {
                    return finalEndDate;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }


    /**
     * 根据传入参数，判断dt1是否比dt2晚 hours个小时
     *
     * @param dt1
     * @param dt2
     * @param hours 小时数
     * @return true 大于  false 小于
     */
    public static boolean compareDate(String dt1, String dt2, int hours) {
        Date tempDate1 = stringToDate(dt1, "yyyy-MM-dd HH:mm:ss");
        Date tempDate2 = stringToDate(dt2, "yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(tempDate1);
        long d1 = c.getTimeInMillis();
        c.setTime(tempDate2);
        long d2 = c.getTimeInMillis();
        return (d1 - d2) > (1000 * 60 * 60) * hours;
    }

    /**
     * 毫秒数转换为"yyyy-MM-dd HH:mm:ss"格式时间
     *
     * @return String
     */
    public static String getMillisecond(Long millisecond) {
        try {
            Date date = new Date(millisecond);
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
            return sdf.format(date);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        //把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        //日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 返回当前月份的yyyy-MM-dd的数组
     *
     * @return
     */
    public static String[] getCurrentMonthAllDaysArray() {
        String prefixDate = getSysmonth();
        int days = getCurrentMonthLastDay();
        String[] daysArray = new String[days];
        for (int i = 0; i < days; i++) {
            if (i < 9) {
                daysArray[i] = prefixDate + "-0" + (i + 1);
            } else {
                daysArray[i] = prefixDate + "-" + (i + 1);
            }
        }
        return daysArray;
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上天数后的日期
     *
     * @param dateStr
     * @param addDay
     * @return
     */
    public static String getStrDateToAddDay(String dateStr, int addDay) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, addDay);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上小时后的日期
     *
     * @param dateStr
     * @param addHour
     * @return
     */
    public static String getStrDateToAddHour(String dateStr, int addHour) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, addHour);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }

    /**
     * 返回YYYY_MM_DD_HH_mm_ss 为格式的时间字符，计算时间 加上分钟后的日期
     *
     * @param dateStr
     * @param addMinutes
     * @return
     */
    public static String getStrDateToAddMinutes(String dateStr, int addMinutes) {
        Date date = stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.MINUTE, addMinutes);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss).format(date);
    }


    /**
     * 判断 当前时间在开始时间与结束时间之间
     *
     * @param startTime 开始时间  yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间  yyyy-MM-dd HH:mm:ss
     * @return 计算错误返回0
     */
    public static Boolean compareCurrentDate(String startTime, String endTime, String format) {
        Date startDate = DateUtils.stringToDate(startTime, format);
        Date nowDate = DateUtils.stringToDate(DateUtils.getSystime(), format);
        Date endDate = DateUtils.stringToDate(endTime, format);
        long d1 = startDate.getTime();
        long d2 = nowDate.getTime();
        long d3 = endDate.getTime();
        if ((d3 > d2) && (d2 > d1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断 当前时间在开始时间与结束时间之间 HH:mm
     *
     * @param startTime 开始时间  HH:mm
     * @param endTime   结束时间  HH:mm
     * @return 计算错误返回0
     */
    public static Boolean compareCurrentTime(String startTime, String endTime, String format) {
        Date startDate = DateUtils.stringToDate(startTime, format);
        Date nowDate = DateUtils.stringToDate(DateUtils.getTime(), format);
        Date endDate = DateUtils.stringToDate(endTime, format);
        long d1 = startDate.getTime();
        long d2 = nowDate.getTime();
        long d3 = endDate.getTime();
        if ((d3 > d2) && (d2 > d1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 相差时间秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuotS(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 判断 时间1是否大于时间2
     *
     * @return 计算错误返回0
     */
    public static Boolean compareDateTime(String time1, String time2, String format) {
        Date date1 = DateUtils.stringToDate(time1, format);
        Date date2 = DateUtils.stringToDate(time2, format);
        if (date1.getTime() > date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回MM-DD 为格式的时间字符
     *
     * @return String
     */
    public static String getSysdate2() {
        return DateTime.now().toString(MM_DD);
    }

    /**
     * 返回YYYY-MM-DD HH:mm:ss 为格式的时间字符
     *
     * @return String
     */
    public static String getSystime2() {
        return DateTime.now().toString(HH_MM_SS);
    }

    /**
     * 计算出开始时间和结束时间的毫秒差，判断是否超过设定的超时时间，返回描述时间和描述信息字符串
     *
     * @param overrideTime 设定的超时时间
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return
     */
    public static String millisecondDifference(long overrideTime, long startTime, long endTime) {
        long s = endTime - startTime;
        String str = s > overrideTime ? s + "ms,超时" : s + "ms";
        return str;
    }

    /**
     * 求相差时间天数（不是整天数的，算多一天）
     *
     * @param time1  结束时间
     * @param time2  开始时间
     * @param format 时间格式
     * @return
     */
    public static long getDaysNumber(String time1, String time2, String format) {
        long quot = 0;
        long resNum = 0;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            resNum = date1.getTime() - date2.getTime();
            quot = resNum / 86400000;
            if (resNum % 86400000 > 0) {
                quot += 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 时间转换格式
     *
     * @param time
     * @return
     */
    public static String fromTimeToStr(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        return dateNowStr;
    }

    public static void main(String[] args) {
        System.out.println(isMonday());
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        return dateString;
    }

    /**
     * 获取距离第二天凌晨0:00:01的毫秒数
     *
     * @return
     */
    public static long getMilliSecondNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 1);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }

    /**
     * 是否是星期一
     *
     * @return
     */
    public static boolean isMonday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 2;
    }

    /**
     * yy-MM-DD 表示在某年某月某日 UTC
     *
     * @param timeStr
     * @return
     */
    public static long parseTime(String timeStr) {
        String[] timeArr = StringUtils.split(timeStr, "-");
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(zone);
        calendar.set(Calendar.YEAR, Integer.parseInt(timeArr[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(timeArr[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeArr[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * D-HH-MM，表示在每周第D天HH时MM分
     *
     * @param timeStr
     * @return
     */
    public static long parseWeekTime(String timeStr) {
        String[] timeArr = StringUtils.split(timeStr, "-");
        Calendar calendar = Calendar.getInstance();
        int week = Integer.parseInt(timeArr[0]);
        int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, week);
        if (currWeek > week) {
            calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[1]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[2]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某个时间戳的所在周的周几： 周1 ~ 周日：1 ~ 7
     *
     * @param millisTime
     * @param dayOfWeekNum
     * @return
     */
    public static long getWeekNumTime(Long millisTime, int dayOfWeekNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(millisTime);
        calendar.set(Calendar.DAY_OF_WEEK, (dayOfWeekNum % 7) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取某个时间戳的下一个周几： 周1 ~ 周日：1 ~ 7
     *
     * @param millisTime
     * @param dayOfWeekNum
     * @return
     */
    public static long getNextWeekNumTime(Long millisTime, int dayOfWeekNum) {
        dayOfWeekNum = (dayOfWeekNum % 7) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisTime);
        int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeekNum);
        if (currWeek >= dayOfWeekNum) {
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某个时间戳的上一个周几： 周1 ~ 周日：1 ~ 7
     *
     * @param millisTime
     * @param dayOfWeekNum
     * @return
     */
    public static long getLastWeekNumTime(Long millisTime, int dayOfWeekNum) {
        dayOfWeekNum = (dayOfWeekNum % 7) + 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisTime);
        int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeekNum);
        if (currWeek <= dayOfWeekNum) {
            calendar.add(Calendar.WEEK_OF_MONTH, -1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long parseFullTimeStr(String fullTimeStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(fullTimeStr);
            return date.getTime();
        } catch (ParseException e) {
//            LoggerUtil.getInstance().recordException(e);
            e.printStackTrace();
            return 0;
        }
    }

    public static long parseFullTimeStr(String fullTimeStr) {
        return parseFullTimeStr(fullTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取下一天的零点
     */
    public static long getNewDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取当天零点
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某天零点
     *
     * @param date
     * @return
     */
    public static long getZeroClock(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某个时间戳的零点
     *
     * @param dateInMillis
     * @return
     */
    public static long getZeroClock(long dateInMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateInMillis);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某个时间戳的UTC零点
     *
     * @param dateInMillis
     * @return
     */
    public static long getUTCZeroClock(long dateInMillis) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.setTimeInMillis(dateInMillis);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    /**
     * 周一 ~ 周日 : 1 ~ 7
     */
    public static int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day -= 1;
        }
        return day;
    }

    /**
     * 周日 ~ 周六 : 1 ~ 7
     */
    public static int getDayOfWeek(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 计算两个时间戳间隔几天
     */
    public static int getDayInterval(long time1, long time2) {
        long zeroTime1 = getZeroClock(time1);
        long zeroTime2 = getZeroClock(time2);
        long interval = Math.abs(zeroTime2 - zeroTime1);
        int dayInterval = (int) (interval / 86400000);
        return dayInterval;
    }

    /**
     * 计算UTC两个时间戳间隔几天
     */
    public static int getUTCDayInterval(long time1, long time2) {
        long zeroTime1 = getUTCZeroClock(time1);
        long zeroTime2 = getUTCZeroClock(time2);
        long interval = Math.abs(zeroTime2 - zeroTime1);
        return (int) (interval / 86400000);
    }

    /**
     * 计算两个时间戳间隔个小时
     */
    public static int getHourInterval(long time1, long time2) {
        int hour = 0;
        long time = time1;
        while (time2 > time) {
            time += 60 * 60 * 1000;
            hour++;
        }
        return hour - 1;
    }

    /**
     * 根据指定条件获取时间戳
     *
     * @param timeStamp 计算的时间戳：0-取当前时间戳
     * @param hour      计算的小时：大于0-后多少小时 小于0-前多少小时
     */
    public static long getTimeStampByHour(long timeStamp, int hour) {
        Calendar calendar = Calendar.getInstance();
        if (timeStamp != 0) {
            calendar.setTimeInMillis(timeStamp);
        }
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTimeInMillis();
    }

    /**
     * 判断两个时间戳是否是同一天
     */
    public static boolean isSameDay(long time1, long time2) {
        long zeroTime1 = getZeroClock(time1);
        long zeroTime2 = getZeroClock(time2);
        return zeroTime1 == zeroTime2;
    }

    /**
     * 判断两个时间戳是否是UTC同一天
     */
    public static boolean isUTCSameDay(long time1, long time2) {
        long zeroTime1 = getUTCZeroClock(time1);
        long zeroTime2 = getUTCZeroClock(time2);
        return zeroTime1 == zeroTime2;
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek()); // Sunday
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek() + 6); // Saturday
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /***
     * 获取指定时间对应的毫秒数
     * @param time "HH:mm:ss"
     * @return
     */
    public static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getHourOfDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static int getMonthOfDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYearOfDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDayOfYear(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static long getHourOfDayStartTime(long timeStamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 判断当天是否为本月第一天
     *
     * @return
     */
    public static boolean isFirstDayOfMonth() {
        boolean flag = false;
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        if (1 == today) {
            flag = true;
        }
        return flag;
    }

    /**
     * 描述:获取下一个月的第一天.
     *
     * @return
     */
    public static String getNextFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    /**
     * 描述:获取本月的第一天.
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }


    //获取剩余时间(秒)
    public static int getRemainSecond(long time) {
        if (time > System.currentTimeMillis()) {
            return (int) ((time - System.currentTimeMillis()) / 1000);
        }
        return 0;
    }

    //获取剩余时间(毫秒)
    public static long getRemainMillisecond(long time) {
        if (time > System.currentTimeMillis()) {
            return time - System.currentTimeMillis();
        }
        return 0;
    }

    //获取完成时时间戳
    public static long getFinishTimestamp(long needTime) {
        return System.currentTimeMillis() + needTime * 1000;
    }

    /**
     * 计算是否是相邻的两天
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return
     * @throws ParseException
     */
    public static boolean isYesterday(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days)) == 1;
    }

    /**
     * 获取本周第1天到今天的日期
     *
     * @return
     */
    public static List<String> getWeekDay() {
        Calendar cal = Calendar.getInstance();
        ArrayList<String> list = new ArrayList<>();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= n; i++) {
            cal.set(Calendar.DAY_OF_MONTH, date + i - n);
            list.add(i - 1, sdf.format(cal.getTime()));
        }
        return list;
    }


    /**
     * 获取上周的所有日期
     *
     * @return
     */
    public static List<String> getLastWeekDay() {
        ArrayList<String> list = new ArrayList<>();
        // 日期格式转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        Calendar instance = Calendar.getInstance();
        // 调整到上周
        instance.add(Calendar.WEDNESDAY, -1);
        // 调整到上周1
        instance.set(Calendar.DAY_OF_WEEK, 2);
        //循环打印
        for (int i = 1; i <= 7; i++) {
            list.add(format.format(instance.getTime()));
            instance.add(Calendar.DAY_OF_WEEK, 1);
        }
        return list;
    }

    //获取当前时间戳的下一天的时间戳
    public static long getNextDayTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    //获取指定时间戳的下一天的时间戳
    public static long getNextDayTimeStamp(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    //获取指定时间戳的下一小时的时间戳
    public static long getNextHourTimeStamp(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTimeInMillis();
    }

    //获取指定时间戳的后多少分钟的时间戳
    public static long getTimeStampAfterMinute(long timeStamp, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    //获取当前时间戳的上一天的时间戳
    public static long getLastDayTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTimeInMillis();
    }

    //获取指定时间戳的上一天的时间戳
    public static long getLastDayTimeStamp(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 根据指定条件获取时间戳
     *
     * @param timeStamp 计算的时间戳：0-取当前时间戳
     * @param day       计算的天数：大于0-后多少天 小于0-前多少天
     */
    public static long getTimeStampByDay(long timeStamp, int day) {
        Calendar calendar = Calendar.getInstance();
        if (timeStamp != 0) {
            calendar.setTimeInMillis(timeStamp);
        }
        calendar.add(Calendar.DATE, day);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取昨天天某个时间戳
     *
     * @param hour        时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getLastDayOneTimeStamp(int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取今天某个时间戳
     *
     * @param hour        时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getTodayOneTimeStamp(int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取明天某个时间戳
     *
     * @param hour        时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static long getNextDayOneTimeStamp(int hour, int minute, int second, int milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取UTC当天24点时间戳
     *
     * @return
     */
    public static long getUTCTodayEndTime() {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取UTC当天0点时间戳
     *
     * @return
     */
    public static long getUTCTodayStartTime() {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 100);
        return cal.getTimeInMillis();
    }


    /**
     * 获取UTC当天直接时间点时间戳
     * hourOfDay 24小时制
     * 23 59 59 UTC24点
     * 0  0  0  UTC0点
     *
     * @return
     */
    public static long getUTCTodayTime(int hourOfDay, int min, int sec) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);

        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        cal.set(Calendar.MILLISECOND, 100);
        return cal.getTimeInMillis();
    }

    /**
     * 获取UTC时间
     */
    public static long getUTCTime() {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(zone);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取UTC当前时间
     */
    public static Date getUTCDate() {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute).append(":").append(sec);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(UTCTimeBuffer.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }


    /**
     * 获取UTC明天直接时间点时间戳
     *
     * @param hour   时
     * @param minute 分钟
     * @param second 秒
     * @return
     */
    public static long getUTCTomorrowTime(int hour, int minute, int second) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(zone);

        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取指定时间戳对应周内某一时刻的时间戳
     * 周一为第一天
     *
     * @param day  第几天
     * @param hour 第几小时
     * @return
     */
    public static long getThisWeekDayNumTime(long nowTime, int day, int hour) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.setTimeInMillis(nowTime);
        cal.set(Calendar.DAY_OF_WEEK, (day % 7) + 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DATE, 7);
        }
        return cal.getTime().getTime();
    }


    /**
     * 获取下周周某一时刻的时间戳
     * 周一为第一天
     *
     * @param day  第几天
     * @param hour 第几小时
     * @return
     */
    public static long getLastWeekDayNumTime(int day, int hour) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.add(Calendar.WEDNESDAY, +1);
        cal.set(Calendar.DAY_OF_WEEK, (day % 7) + 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DATE, 7);
        }
        return cal.getTime().getTime();
    }


    public static long getLastWeekDayNumTime(long nowTime, int day, int hour) {
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.setTimeInMillis(nowTime);
        cal.add(Calendar.WEDNESDAY, +1);
        cal.set(Calendar.DAY_OF_WEEK, (day % 7) + 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DATE, 7);
        }
        return cal.getTime().getTime();
    }


    /**
     * 时间转换格式yyyyMMddHHmmss
     *
     * @return
     */
    public static String fromDateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 获取下一个5分钟的秒数
     *
     * @return
     */
    public static int getNextMinute5Sec() {
        Calendar cal = Calendar.getInstance();
        int delay = Math.abs((5 - cal.get(Calendar.MINUTE) % 5)) * 60 - cal.get(Calendar.SECOND);
        return delay;
    }

    /**
     * 当前所处的5分钟Shi几案
     *
     * @return
     */
    public static Date getMinute5Date() {
        Calendar cal = Calendar.getInstance();
        int delay = (cal.get(Calendar.MINUTE) / 5) * 5;
        cal.set(Calendar.MINUTE, delay);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getUTCDate(long time) {
        StringBuffer UTCTimeBuffer = new StringBuffer();
        TimeZone zone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(zone);
        cal.setTimeInMillis(time);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute).append(":").append(sec);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(UTCTimeBuffer.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }


    /**
     * 获取天数对应的毫秒数
     *
     * @param day 计算天
     * @return 天对应毫秒数
     */
    public static long getDayToMillis(int day) {
        return (long) day * 24 * 60 * 60 * 1000;
    }
}