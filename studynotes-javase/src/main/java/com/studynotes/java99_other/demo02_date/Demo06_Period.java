package com.studynotes.java99_other.demo02_date;

import java.time.LocalDate;
import java.time.Period;

// Period用来表示两个日期之间的差值，比如年月日和年月日之间的差值
public class Demo06_Period {
    public static void main(String[] args) {
        LocalDate localDate1 = LocalDate.of(2020, 4, 1);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);

        System.out.println(period);

        System.out.println("区间是否为0: " + period.isZero());
        System.out.println("区间是否为为负: " + period.isNegative());

        System.out.println("年与年区间的相差几年: " + period.getYears());
        System.out.println("区间的相差几月: " + period.getMonths());
        System.out.println("区间的相差几日: " + period.getDays());

        System.out.println("区间相差多少个月: " + period.toTotalMonths());
    }
}
