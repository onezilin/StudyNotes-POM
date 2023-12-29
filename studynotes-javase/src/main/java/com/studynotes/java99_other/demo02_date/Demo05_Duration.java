package com.studynotes.java99_other.demo02_date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

// duration用来表示两个时间戳之间差值
public class Demo05_Duration {
    public static void main(String[] args) {
        Duration duration1 = Duration.between(Instant.ofEpochMilli(new Date().getTime() + 30000), Instant.now());
        System.out.println(duration1);
        System.out.println("Duration区间是否为0: " + duration1.isZero());
        System.out.println("Duration区间是否为负: " + duration1.isNegative());

        System.out.println("Duration区间值的秒数: " + duration1.getSeconds());
        System.out.println("Duration区间值的纳秒数: " + duration1.getNano());
        System.out.println("Duration的度量单位: " + duration1.getUnits());

        System.out.println("Duration区间相差几天: " + duration1.toDays());
        System.out.println("Duration区间差几小时: " + duration1.toHours());
        System.out.println("Duration区间相差几分钟: " + duration1.toMinutes());
        System.out.println("Duration区间相差几毫秒: " + duration1.toMillis());

        Duration duration2 = Duration.between(LocalDateTime.parse("2020-09-21T01:02:03"), LocalDateTime.parse("2020" +
                "-09-22T02:03:04"));
        System.out.println("localoDateTime1和localDateTime2之间的时间差: " + duration2.toMillis());
    }
}
