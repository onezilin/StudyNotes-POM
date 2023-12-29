package com.studynotes.java16_concurrency;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

// CompletableFuture测试
public class Demo38_CompletableFuture {

    @Test
    void test() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            return 1 / 0;
        });

        try {
            Integer value = f1.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        createCompletableFuture();

        //        diffBetweenGetAndJoin();

        //        completeTest();

        //        whenTest();

        //        thenApplyTest();

        //        thenComposeTest();

        //        thenAcceptBothTest();

        //        acceptEitherTest();

        allOfTest();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<Integer> createCompletableFuture() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        return future;
    }

    // get和join区别
    public static void diffBetweenGetAndJoin() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
        // join抛出未检查的异常
        //        future.join();
        // get抛出已检查异常，需要放在try-catch中，其他和join基本没区别
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // complete作用
    public static void completeTest() {
        final CompletableFuture<Integer> future = createCompletableFuture();

        class Client<T> extends Thread {
            CompletableFuture<T> future;

            Client(String threadName, CompletableFuture<T> future) {
                super(threadName);
                this.future = future;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + "：" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        new Client<>("Client1", future).start();
        new Client<>("Client2", future).start();
        System.out.println("waiting");
        // 执行到这里时，若CompletableFuture还没有返回结果，则使用指定的值作为结果返回
        future.complete(100);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // when测试
    public static void whenTest() {
        CompletableFuture<Integer> future = createCompletableFuture().whenComplete((v, e) -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(future.join());
        System.out.println("等待，是否以async结尾");
    }

    // thenApply测试
    public static void thenApplyTest() {
        CompletableFuture<String> future = createCompletableFuture().thenApply(i -> {
            // 将Integer变成String
            return "s" + i;
        });
        System.out.println(future.join());
    }

    // thenCompose测试
    public static void thenComposeTest() {
        createCompletableFuture().thenCompose((i) -> {
            System.out.println("执行thenCompose");
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("生成新的CompletableFuture");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "11111";
            });
        });
    }

    // thenAcceptBoth测试
    public static void thenAcceptBothTest() {
        createCompletableFuture().thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("里面的执行了");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        }), (t, u) -> {
            System.out.println("消费了");
            System.out.println(t);
            System.out.println(u);
        });
    }

    // acceptEither测试
    public static void acceptEitherTest() {
        createCompletableFuture().acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }), (i) -> {
            System.out.println(i);
        });
    }

    // allOf测试
    public static void allOfTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情";
        }, executorService);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000 + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息";
        }, executorService);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000 + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息";
        }, executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000 + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "订单信息";
        }, executorService);

        //        CompletableFuture<Void> future = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        //        System.out.println("执行了allOf操作");
        //        System.out.println(future.join());
        //        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());

        CompletableFuture<Object> future = CompletableFuture.anyOf(futureA, futureB, futureC, futureD);
        System.out.println("执行了anyOf操作");
        System.out.println(future.join());

        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
    }
}


