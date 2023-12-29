package com.studynotes.java99_other.demo04_string;

// String.format() 格式化输出
public class Demo07_Printf {

    public static void main(String[] args) {
        int x = 5;
        double y = 5.332542;
        // The old way:
        System.out.println("Row 1: [" + x + " " + y + "]");
        // The new way:
        System.out.format("Row 1: [%d %f]%n", x, y);
        // or
        System.out.printf("Row 1: [%d %f]%n", x, y);
    }
}
