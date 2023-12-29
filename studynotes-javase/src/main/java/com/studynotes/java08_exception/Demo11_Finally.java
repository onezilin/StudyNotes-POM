package com.studynotes.java08_exception;

/**
 * Description: 无论 try 语句块中是否抛出异常，finally 语句块中的代码总会执行
 */
public class Demo11_Finally {

    public static void main(String[] args) {
        System.out.println("Entering first try block");
        try {
            System.out.println("Entering second try block");
            try {
                throw new Exception();
            } finally {
                System.out.println("finally in 2nd try block");
            }
        } catch (Exception e) {
            System.out.println(
                    "Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }
}


