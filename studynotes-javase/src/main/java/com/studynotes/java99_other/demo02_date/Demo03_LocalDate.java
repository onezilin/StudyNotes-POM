package com.studynotes.java99_other.demo02_date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

// Date非线程安全，设计很差，时区处理复杂
// jdk8中提供新的api
// LocalData（当前日期）,LocalTime（当前时间）,LocalDateTime（当前日期时间）
public class Demo03_LocalDate {
    public static void main(String[] args) {
        // --------------------LocalDate--------------
        LocalDateTime.of(LocalDate.MIN, LocalTime.MIN);
        LocalDate date1 = LocalDate.now();
        System.out.println("当前时间:" + date1);

        LocalDate date2 = LocalDate.of(1800, 1, 1);
        System.out.println("指定日期: " + date2);

        LocalDate date3 = date2.plusWeeks(2);
        System.out.println("加2周: " + date3);

        LocalDate date4 = date3.minusDays(16);
        System.out.println("减16天: " + date4);

        LocalDate date5 = date4.withDayOfMonth(20);
        System.out.println("指定为本月20号: " + date5);

        System.out.println("比较日期大小:" + date4.isAfter(date5));

        System.out.println("是否为闰年:" + date5.isLeapYear());

        LocalDateTime date6 = date5.atTime(12, 20, 20);
        System.out.println("LocalDate转LocalDateTime: " + date6);

        LocalDateTime dateTime1 = LocalDateTime.now();
        System.out.println(dateTime1.getYear() + "年" + dateTime1.getMonthValue() + "月" + dateTime1.getDayOfMonth() + "日"
                + dateTime1.getHour() + "时" + dateTime1.getMinute() + "分"
                + dateTime1.getSecond() + "秒" + dateTime1.getLong(ChronoField.MILLI_OF_SECOND) + "毫秒"
                + " " + "今年第" + dateTime1.getDayOfYear() + "天" + " " + "周" + dateTime1.getDayOfWeek().getValue());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");

        String stringTime = dateTime1.format(formatter);
        System.out.println("格式化日期: " + stringTime);

        LocalDateTime dateTime2 = LocalDateTime.parse(stringTime, formatter);
        System.out.println("反格式化: " + dateTime2);
        // 这里表示LocalDateTime偏移时间，odt输出值还是和dateTime2一样
        OffsetDateTime odt1 = dateTime2.atOffset(ZoneOffset.ofHours(8));
        System.out.println("LocalDataTime转时区时间: " + odt1);

        Instant ins1 = odt1.toInstant();
        System.out.println("LocalDateTime转Instant: " + ins1);

        ZonedDateTime zdt1 = dateTime2.atZone(ZoneId.systemDefault());
        System.out.println("LocalDateTime转时区时间：" + zdt1);

        Instant ins2 = zdt1.toInstant();
        System.out.println("LocalDateTime转Instant: " + ins2);

        System.out.println(Instant.now());
    }
}
