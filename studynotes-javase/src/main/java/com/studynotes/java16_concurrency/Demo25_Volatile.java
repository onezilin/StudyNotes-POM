package com.studynotes.java16_concurrency;


// volatile 是一种轻量级锁,保证了可见性,不保证原子性
// volitile 保证可见性,也就是修改变量值后,会把新值覆盖到主内存中,其他线程会立刻从主内存中获取新值
// 不保证原子性
public class Demo25_Volatile {

    public static volatile int inc = 0;

    /**
     * volatile变量自增运算测试
     */
    public static void increase() {
        inc++;
       /*
            刚才的inc++操作来说，这个操作其实细分为三步，读inc的值给temp，将temp+1，赋值给inc。
            1、当线程1将inc读入内存，然后被阻塞。
            2、线程2也将inc读入内存中，然后执行过第二步，temp+1，然后被阻塞。
            3、线程1被唤醒，此时并没有对inc执行写操作，所以线程1不需要重新从内存读，所以执行完+1操作被赋值后重新写入主存中。
            4、线程2被唤醒，由于inc执行了写操作，导致线程2中的inc缓存失效，所以从内存中重新读进来此时的inc值，由于已经执行过第二步了，
            此时将最新的temp赋值给inc，然后重新写入内存。就在刚才那一步发生了数据不一致性，此时的inc只增加1。
        */
    }

    private static final int THREADS_COUNT = 30;

    public static void main(String[] args) {
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }).start();
        }

        // 如果在IDEA里面进行调试的话，这里的判断条件改为2，因为IDEA会启动一个 Monitor Ctrl-Break 线程。
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(inc);
    }
}
