package com.studynotes.java99_other.demo02_date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Description: 本地时间戳转换为 UTC 时间
 */
public class Demo08_Local2UTC {

    public static void main(String[] args) {
        long timestamp = 1676754000000L; // 本地时间戳时间戳：2023-02-19 05:00:00 UTC+08:00
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("本地时间戳直接转换为时间，会多算八个小时: " + dateFormat.format(date));

        // 转换为 LocalDateTime，指定时间戳的时区为 UTC
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("UTC"));
        System.out.println("本地时间: " + localDateTime);

        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM-dd");
        String month = localDateTime.format(monthFormat);
        System.out.println("月份: " + month);

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        String time = localDateTime.format(timeFormat);
        System.out.println("时分: " + time);
    }
}
