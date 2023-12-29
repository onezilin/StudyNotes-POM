package com.studynotes.java16_concurrency;

/**
 * Description: 实现Runnable接口的类中给重写的run()方法加synchronized
 */
public class Demo10_RunWithSync {
    public static void main(String[] args) {

    }
}

class SynchTest0 {
    public static void main(String[] args) {
        // 线程使用不同的对象时,synchronized方法使用的是对象锁,因为是不同的对象,所以不会产生冲突
        new Thread(new Task()).start();
        new Thread(new Task()).start();
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        long start;

        Task() {
            this.start = System.currentTimeMillis();
        }

        @Override
        public synchronized void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}


class SynchTest {
    public static void main(String[] args) {
        // Now we pass the same instance each time.
        // 这里由于使用的是一个对象,其中一个线程拿到线程锁的时候,其他线程无法执行,由于run是伴随线程整个生命周期,所以其他线程必须等待前一个线程结束才能获取锁,效率较低
        Task t = new Task();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

    static class Task implements Runnable {
        long start;

        Task() {
            this.start = System.currentTimeMillis();
        }

        @Override
        public synchronized void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}

class SynchTest2 {
    public static void main(String[] args) {
        new Thread(new Task()).start();
        new Thread(new Task()).start();
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        long start;

        Task() {
            this.start = System.currentTimeMillis();
        }

        // 如果确实想实现多个线程的同步,可以设一个静态常量,然后用synchronized 代码块获取这个对象锁
        static final Object STATIC_MONITOR = new Object();

        @Override
        public void run() {
            //
            synchronized (STATIC_MONITOR) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println(System.currentTimeMillis() - start);
            }
        }
    }
}
