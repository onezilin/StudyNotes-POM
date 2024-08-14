package com.studynotes.java99_other.demo02_date;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Description: 判断时间是否在有效范围内
 */
public class Demo09_IsEffective {

    @Test
    public void test() throws ParseException {
        String KSRQ = "04-20"; // 日期在 [04-30, 03-21]，支持跨年
        String JSRQ = "04-20";
        String KSSJ = "20:00"; // 时间在 [20:00, 06:00]，支持跨天
        String JSSJ = "06:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date test = dateFormat.parse("2023-02-18 21:00:00");
        LocalDateTime localDateTime = test.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        boolean result = isEffective(KSRQ, JSRQ, KSSJ, JSSJ, localDateTime);
        System.out.println(result);
    }

    private boolean isEffective(String KSRQ, String JSRQ, String KSSJ, String JSSJ, LocalDateTime localDateTime) {
        boolean skipYear = KSRQ.compareTo(JSRQ) > 0; // 判断 KSRQ 和 JSRQ 是否跨年
        boolean skipDay = KSSJ.compareTo(JSSJ) > 0; // 判断 KSSJ 和 JSSJ 是否跨天
        try {
            LocalDateTime monthLdt = LocalDateTime.of(skipYear ? 1971 : 1970, localDateTime.getMonth().getValue(),
                    localDateTime.getDayOfMonth(), 0, 0);
            LocalDateTime timeLdt = LocalDateTime.of(1970, 1, 1, localDateTime.getHour(), localDateTime.getMinute());
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM-dd");
            LocalDateTime ksrqLdt =
                    Instant.ofEpochMilli(monthFormat.parse(KSRQ).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime jsrqLdt =
                    Instant.ofEpochMilli(monthFormat.parse(JSRQ).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime jsrqLdtTemp = jsrqLdt; // 记录 JSRQ
            if (skipYear) jsrqLdt = jsrqLdt.plusYears(1); // 跨年，则加一年
            if (skipDay) jsrqLdt = jsrqLdt.minusDays(1); // 跨天，则减一天

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            LocalDateTime kssjLdt =
                    Instant.ofEpochMilli(timeFormat.parse(KSSJ).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime jssjLdt =
                    Instant.ofEpochMilli(timeFormat.parse(JSSJ).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (skipDay) jssjLdt = jssjLdt.plusDays(1); // 跨天，则加一天

            boolean monthFlag =
                    (monthLdt.isAfter(ksrqLdt) || monthLdt.isEqual(ksrqLdt)) && (monthLdt.isBefore(jsrqLdt) || monthLdt.isEqual(jsrqLdt));
            boolean timeFlag =
                    (timeLdt.isAfter(kssjLdt) || timeLdt.isEqual(kssjLdt)) && (timeLdt.isBefore(jssjLdt) || timeLdt.isEqual(jssjLdt));
            if (skipDay) {
                // 最后一天的 00:00
                LocalDateTime zeroLdt = LocalDateTime.of(1970, 1, 1, 0, 0);
                monthFlag = monthFlag || monthLdt.isEqual(jsrqLdtTemp);
                timeFlag =
                        timeFlag || (timeLdt.isEqual(zeroLdt) || timeLdt.isAfter(zeroLdt)) && (timeLdt.isBefore(jssjLdt) || timeLdt.isEqual(jssjLdt));
            }
            return monthFlag && timeFlag;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
