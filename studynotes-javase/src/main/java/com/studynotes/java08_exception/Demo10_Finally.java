package com.studynotes.java08_exception;

/**
 * Description: 异常会导致异常之后的代码不会执行，无论 try 语句块中是否抛出异常，finally 语句块中的代码总会执行
 * 只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，
 * 如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
 */
public class Demo10_Finally {

    static int count = 0;

    public static void main(String[] args) {
        try {
            if (count++ == 0)
                throw new Exception();
            System.out.println("No exception");
        } catch (Exception e) {
            System.out.println("ThreeException");
        } finally {
            System.out.println("In finally clause");
        }
    }
}
