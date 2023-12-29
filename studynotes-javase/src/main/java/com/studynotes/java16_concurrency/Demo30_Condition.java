package com.studynotes.java16_concurrency;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 测试AQS中ConditionObject的await和signal方法，实现生产者和消费者
public class Demo30_Condition {
    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer(20);
        MyProducer producer = new MyProducer(producerAndConsumer);
        MyConsumer consumer = new MyConsumer(producerAndConsumer);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer1 = new Thread(consumer);
        Thread threadConsumer2 = new Thread(consumer);
        Thread threadConsumer3 = new Thread(consumer);

        threadConsumer1.start();
        threadConsumer2.start();
        threadConsumer3.start();
        threadProducer.start();
    }

    static class ProducerAndConsumer {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition producerCondition = lock.newCondition();
        private final Condition consumerCondition = lock.newCondition();
        private final LinkedList<Integer> product = new LinkedList<>();
        private final int maxSize;

        public ProducerAndConsumer(int maxSize) {
            this.maxSize = maxSize;
        }

        public void producer() {
            lock.lock();
            try {
                while (product.size() == maxSize) {
                    System.out.println("商品满了，请停止生产");
                    producerCondition.await();
                }
                product.offer(1);
                Thread.sleep(3000);
                consumerCondition.signal();
                System.out.println("有" + product.size() + "个商品了，通知消费者");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void consumer() {
            lock.lock();
            try {
                while (product.isEmpty()) {
                    System.out.println("商品空了，请停止消费");
                    consumerCondition.await();
                }
                product.poll();
                Thread.sleep(1000);
                producerCondition.signal();
                System.out.println("消费商品了，通知生产者");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class MyProducer implements Runnable {

        private ProducerAndConsumer producerAndConsumer;

        public MyProducer(ProducerAndConsumer producerAndConsumer) {
            this.producerAndConsumer = producerAndConsumer;
        }

        @Override
        public void run() {
            System.out.println("生产者线程启动");
            while (true) {
                producerAndConsumer.producer();
            }
        }
    }

    static class MyConsumer implements Runnable {
        private ProducerAndConsumer producerAndConsumer;

        public MyConsumer(ProducerAndConsumer producerAndConsumer) {
            this.producerAndConsumer = producerAndConsumer;
        }

        @Override
        public void run() {
            System.out.println("消费者线程启动");
            while (true) {
                producerAndConsumer.consumer();
            }
        }
    }
}
