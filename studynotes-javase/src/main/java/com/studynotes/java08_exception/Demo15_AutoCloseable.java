package com.studynotes.java08_exception;

/**
 * Description: 实现 AutoCloseable 接口，可以使用try-with-resource
 * 会在 try 执行完毕后（无论正常或异常）都会先去调用 close 方法，然后再去 catch，也就是 try 之后 catch 之前执行
 */
public class Demo15_AutoCloseable {
    public static void main(String[] args) {
        // 创建时是正序执行,close 时是倒序执行
        try (First f = new First();
             // 注意：这里在创建 Second 对象的始化时抛异常，所以就没有创建相应的对象，也就没有调用close方法
             Second s = new Second();) {
            System.out.println("try");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch");
        }
    }

    static class Reporter implements AutoCloseable {
        String name = getClass().getSimpleName();

        public Reporter() {
            System.out.println("Creating " + name);
        }

        public void close() {
            System.out.println("Closing " + name);
        }
    }

    static class First extends Reporter {
    }

    static class Second extends Reporter {
        public Second() {
            super();
            throw new RuntimeException();
        }
    }
}

