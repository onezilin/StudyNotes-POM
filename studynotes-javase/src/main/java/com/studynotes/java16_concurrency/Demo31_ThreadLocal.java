package com.studynotes.java16_concurrency;

// ThreadLocal测试
public class Demo31_ThreadLocal {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue() {
            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return null;
        }
    };

    public static void main(String[] args) {
        new Thread(new MyIntegerTask("IntegerTask1")).start();
        new Thread(new MyIntegerTask("IntegerTask2")).start();
    }

    public static class MyIntegerTask implements Runnable {
        private String name;

        MyIntegerTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                // ThreadLocal.get方法获取线程变量
                if (null == Demo31_ThreadLocal.threadLocal.get()) {
                    // ThreadLocal.et方法设置线程变量
                    Demo31_ThreadLocal.threadLocal.set(0);
                    System.out.println("线程" + name + ": 0");
                } else {
                    int num = (Integer) Demo31_ThreadLocal.threadLocal.get();
                    Demo31_ThreadLocal.threadLocal.set(num + 1);
                    System.out.println("线程" + name + ": " + Demo31_ThreadLocal.threadLocal.get());
                    if (i == 3) {
                        Demo31_ThreadLocal.threadLocal.remove();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class MyStringTask implements Runnable {
        private String name;

        MyStringTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (null == Demo31_ThreadLocal.threadLocal.get()) {
                    Demo31_ThreadLocal.threadLocal.set("a");
                    System.out.println("线程" + name + ": a");
                } else {
                    String str = (String) Demo31_ThreadLocal.threadLocal.get();
                    Demo31_ThreadLocal.threadLocal.set(str + "a");
                    System.out.println("线程" + name + ": " + Demo31_ThreadLocal.threadLocal.get());
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
