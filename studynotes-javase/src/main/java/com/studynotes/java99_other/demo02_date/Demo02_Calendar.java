package com.studynotes.java99_other.demo02_date;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Demo02_Calendar {
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        System.out.println("当前时间: " + calendar1);

        Date date1 = calendar1.getTime();
        System.out.println("Calendar转Date: " + date1);

        Instant ins1 = calendar1.toInstant();
        System.out.println("Calendar转Instant: " + ins1);

        calendar1.setTime(date1);
        System.out.println("Date转Calendar: " + calendar1);

        calendar1.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println("一周的第一天是是什么，例如:美国是周日 1，法国是周一 2: " + calendar1.getFirstDayOfWeek());

        System.out.println("年: " + calendar1.get(Calendar.YEAR));
        System.out.println("从0开始几月: " + calendar1.get(Calendar.MONTH));
        System.out.println("日: " + calendar1.get(Calendar.DAY_OF_MONTH));
        System.out.println("周几，详情查看Calendar属性: " + calendar1.get(Calendar.DAY_OF_WEEK));
        System.out.println("时: " + calendar1.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + calendar1.get(Calendar.MINUTE));
        System.out.println("秒: " + calendar1.get(Calendar.SECOND));
        System.out.println("毫秒: " + calendar1.get(Calendar.MILLISECOND));
        System.out.println("时间戳: " + calendar1.getTimeInMillis());

        calendar1.set(1800, Calendar.JANUARY, 1, 12, 20, 20);
        System.out.println("重新设置时间: " + calendar1.getTime());

        calendar1.setWeekDate(2021, 1, Calendar.FRIDAY);
        System.out.println("以周为标准设置时间: " + calendar1.getTime());

        calendar1.setWeekDate(2021, 1, Calendar.MONDAY);
        System.out.println("以周为标准设置时间: " + calendar1.getTime());
    }
}
