package com.studynotes.java99_other.demo02_date;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Description: 根据时间戳生成小时分布图
 * 例如：2022-03-15 12:00:00 生成的小时分布图为 12-14h
 */
public class Demo10_HourHistogram {

    @Test
    public void test() {
        long timestamp = 1676764800000L; // 2023-02-19 08:00:00 UTC+08:00
        System.out.println(generateHourHistogram(timestamp));
    }

    private Integer generateHourHistogram(long timestamp) {
        String[] histogramArr = {"0-2h", "2-4h", "4-6h", "6-8h", "8-10h", "10-12h",
                "12-14h", "14-16h", "16-18h", "18-20h", "20-22h", "22-24h"};
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("UTC"));
        LocalTime localTime = localDateTime.toLocalTime();
        LocalTime start = LocalTime.MIN;
        int size = histogramArr.length;
        for (int i = 0; i < size; i++) {
            LocalTime end = start.plusHours(2);
            if ((localTime.isAfter(start) || localTime.equals(start)) && (i == size - 1 || localTime.isBefore(end))) {
                return i;
            }
            start = end;
        }
        return null;
    }

    @Test
    public void testInRange() {
        long timestamp = 1722415158000L; // 2024-07-31 08:39:18 UTC+08:00
        String startTime = "2024-07-31 00:00:00";
        String endTime = "2024-07-31 23:59:59";
        String startHour = "08:00:00";
        String endHour = "10:00:00";
        System.out.println(isInRange(timestamp, startTime, endTime, 11));
    }

    /**
     * Description: 判断是否在指定时间段内，并且在指定小时内
     */
    private boolean isInRange(long timestamp, String startTime, String endTime, int hourHistogramIndex) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("UTC"));
        LocalTime localTime = localDateTime.toLocalTime();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTimeLdt = LocalDateTime.parse(startTime, dateFormat);
        LocalDateTime endTimeLdt = LocalDateTime.parse(endTime, dateFormat);
        boolean falg1 = (localDateTime.isAfter(startTimeLdt) || localDateTime.isEqual(startTimeLdt)) && (localDateTime.isBefore(endTimeLdt) || localDateTime.isEqual(endTimeLdt));
        LocalTime zeroLt = LocalTime.MIN;
        LocalTime startHourLt = zeroLt.plusHours(hourHistogramIndex * 2L);
        LocalTime endHourLt = hourHistogramIndex * 2L + 2 >= 24 ? LocalTime.MAX : startHourLt.plusHours(2);
        boolean falg2 = (localTime.isAfter(startHourLt) || localTime.equals(startHourLt)) && localTime.isBefore(endHourLt);
        return falg1 && falg2;
    }
}
