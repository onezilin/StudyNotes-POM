package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * 只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，
 * 如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
 */
public class Demo12_Finally {

    @Test
    void test() {
        System.out.println(f(1));
        System.out.println(f(2));
        System.out.println(f(3));
        System.out.println(f(4));
    }

    public int f(int i) {
        System.out.println("Initialization that requires cleanup");
        try {
            System.out.println("Point 1");
            if (i == 1) return i;
            System.out.println("Point 2");
            if (i == 2) throw new Exception();
            System.out.println("End");
            return i;
        } catch (Exception e) {
            System.out.println(1111111111);
            return i;
        } finally {
            System.out.println("Performing cleanup");
            if(i == 4) return i;
        }
    }
}
