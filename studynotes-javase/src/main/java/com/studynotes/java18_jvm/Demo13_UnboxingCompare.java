package com.studynotes.java18_jvm;

// 通过查看class文件，比较自动装箱、拆箱相等性测试
public class Demo13_UnboxingCompare {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d); // 直接比较，是比较地址是否相等，Integer有IntegerCache
        System.out.println(e == f);
        System.out.println(c == (a + b)); // 若有算术运算符，则是拆箱为基本类型再比较
        System.out.println(c.equals(a + b)); // 先判断类型是否相等，再比较值是否相等
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
