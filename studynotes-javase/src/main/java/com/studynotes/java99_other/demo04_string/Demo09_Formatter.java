package com.studynotes.java99_other.demo04_string;

import java.util.Formatter;

/**
 * Description: 使用 Formatter 类进行格式化输出，java 的所有格式化功能都是 Formatter 提供的
 */
public class Demo09_Formatter {
    public static void main(String[] args) {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        receiptBuilder.add("Jack's Magic Beans", 4, 4.25);
        receiptBuilder.add("Princess Peas", 3, 5.1);
        receiptBuilder.add("Three Bears Porridge", 1, 14.29);
        System.out.println(receiptBuilder.build());
    }

    static class ReceiptBuilder {
        private double total = 0;
        private Formatter f = new Formatter(new StringBuilder());

        public ReceiptBuilder() {
            // -15 表示左对齐 占十五个字符
            f.format("%-15s %5s %10s%n", "Item", "Qty", "Price");
            f.format("%-15s %5s %10s%n", "----", "---", "-----");
        }

        public void add(String name, int qty, double price) {
            f.format("%-15.15s %5d %10.2f%n", name, qty, price);
            total += price * qty;
        }

        public String build() {
            f.format("%-15s %5s %10.2f%n", "Tax", "", total * 0.06);
            f.format("%-15s %5s %10s%n", "", "", "-----");
            f.format("%-15s %5s %10.2f%n", "Total", "", total * 1.06);
            return f.toString();
        }
    }
}

