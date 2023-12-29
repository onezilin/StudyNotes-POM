package com.studynotes.java99_other.demo02_date;

import java.time.*;
import java.util.Date;

// Instant和Date都是表示从1970-01-01 00:00:00开始的时间戳（也就是从那时候至今的Long类型的纳秒数/毫秒数）
// 区别：
// 1. Instant准确得到纳秒，也就是比Date的毫秒长6位
// 2. Instant和Date都表示UTC 0时区的时间戳，但是Date会根据当前时区进行调整展示的日期时间值
public class Demo04_Instant {
    public static void main(String[] args) {
        Instant ins1 = Instant.now();
        System.out.println("当前时间: " + ins1);

        Instant ins2 = new Date().toInstant();
        System.out.println("Date转Instant: " + ins2);

        Date date1 = Date.from(ins2);
        System.out.println("Instant转Date: " + date1);

        System.out.println("Date时间戳:" + new Date().getTime());
        System.out.println("Instant时间戳: " + ins2.toEpochMilli());
        // Instant使用两个属性分别存储秒和纳秒
        System.out.println("Instant时间戳秒数: " + ins2.getEpochSecond());
        System.out.println("Instant时间戳纳秒:" + ins2.getNano());

        OffsetDateTime odt1 = ins2.atOffset(ZoneOffset.ofHours(8));
        System.out.println("Instan转换时区时间: " + odt1);

        ZonedDateTime zdt1 = ins2.atZone(ZoneId.systemDefault());
        System.out.println("Instant使用时区Id转时区时间: " + zdt1);

        LocalDateTime dateTime1 = zdt1.toLocalDateTime();
        System.out.println("Instant转LocalDateTime: " + dateTime1);

        Instant ins3 = ins2.plusSeconds(3600);
        System.out.println("加1小时: " + ins3);
    }
}
