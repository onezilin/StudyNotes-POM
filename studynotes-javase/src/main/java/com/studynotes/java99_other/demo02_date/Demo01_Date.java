package com.studynotes.java99_other.demo02_date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Date测试
public class Demo01_Date {
    public static void main(String[] args) {
        Date date1 = new Date();
        System.out.println("当前时间: " + date1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

        String stringDate = sdf.format(date1);
        System.out.println("格式化时间: " + stringDate);

        try {
            Date date2 = sdf.parse(stringDate);
            System.out.println("反格式化时间: " + date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("从1900距今多少年: " + date1.getYear());
        System.out.println("从0开始几月: " + date1.getMonth());
        System.out.println("日: " + date1.getDate());
        System.out.println("时: " + date1.getHours());
        System.out.println("分: " + date1.getMinutes());
        System.out.println("秒: " + date1.getSeconds());
        System.out.println("时间戳: " + date1.getTime());
        System.out.println("0时区和当前时间的时差，以分钟为单位: " + date1.getTimezoneOffset());

        date1.setYear(113);
        date1.setMonth(4);
        System.out.println("重新设置时间: " + date1);

    }
}
