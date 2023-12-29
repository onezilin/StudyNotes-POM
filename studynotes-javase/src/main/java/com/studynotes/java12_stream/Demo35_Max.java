package com.studynotes.java12_stream;

/**
 * Description: max()，min() 传入 Comparator 获取最大值和最小值，转化为Optionl
 */
public class Demo35_Max {
    public static void main(String[] args) throws Exception {
        System.out.println(
                Demo34_Collect.FileToWords.stream("D:\\accounts.json").count());
        System.out.println(
                Demo34_Collect.FileToWords.stream("D:\\accounts.json")
                        .min(String.CASE_INSENSITIVE_ORDER)
                        .orElse("NONE"));
        System.out.println(
                Demo34_Collect.FileToWords.stream("D:\\accounts.json")
                        .max(String.CASE_INSENSITIVE_ORDER)
                        .orElse("NONE"));
    }
}
