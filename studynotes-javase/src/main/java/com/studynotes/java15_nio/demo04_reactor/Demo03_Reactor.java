package com.studynotes.java15_nio.demo04_reactor;

import com.studynotes.constant.CommonConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 多Reactor多线程模型：将Reactor的职责更加细化，主Reactor只负责进行accept，将获取的Channel传给任一的子Reactor。
 * <p>
 * 子Reactor负责为Channel注册对应的Selector，然后将IO操作交给线程池中的工作线程完成
 */
public class Demo03_Reactor {

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(CommonConstants.PORT)).start();
    }

    static class Reactor implements Runnable {
        private final Selector selector;

        private final ServerSocketChannel serverSocketChannel;

        public Reactor(int port) throws IOException { // Reactor初始化
            selector = Selector.open(); // 打开一个Selector
            serverSocketChannel = ServerSocketChannel.open(); // 建立一个Server端通道
            serverSocketChannel.socket().bind(new InetSocketAddress(port)); // 绑定服务端口
            serverSocketChannel.configureBlocking(false); // selector模式下，所有通道必须是非阻塞的
            // Reactor是入口，最初给一个channel注册上去的事件都是accept
            SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // attach callback object, Acceptor
            sk.attach(new Acceptor(serverSocketChannel));// 绑定接收事件处理器
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select(); // 就绪事件到达之前，阻塞
                    Set<SelectionKey> selected = selector.selectedKeys(); // 拿到本次select获取的就绪事件
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        // 这里进行任务分发
                        dispatch(it.next());
                    }
                    selected.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (selector != null) {
                    try {
                        selector.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        void dispatch(SelectionKey k) {

            Runnable r = (Runnable) (k.attachment()); // 这里很关键，拿到每次selectKey里面附带的处理对象，然后调用其run，这个对象在具体的Handler
            // 里会进行创建，初始化的附带对象为Acceptor（看上面构造器）
            // 调用之前注册的callback对象
            if (r != null) {
                r.run();// 只是拿到句柄执行run方法，并没有新起线程
            }
        }
    }

    static class Acceptor implements Runnable {

        private final ServerSocketChannel serverSocketChannel;

        private final int coreNum = Runtime.getRuntime().availableProcessors(); // 获取CPU核心数

        private final Selector[] selectors = new Selector[coreNum]; // 创建selector给SubReactor使用，个数为CPU
        // 核心数（如果不需要那么多可以自定义，毕竟这里会吞掉一个线程）

        private int next = 0; // 轮询使用subReactor的下标索引

        private SubReactor[] subReactors = new SubReactor[coreNum]; // subReactor

        private Thread[] threads = new Thread[coreNum]; // subReactor的处理线程

        Acceptor(ServerSocketChannel serverSocketChannel) throws IOException {
            this.serverSocketChannel = serverSocketChannel;
            // 初始化
            for (int i = 0; i < coreNum; i++) {
                selectors[i] = Selector.open();
                subReactors[i] = new SubReactor(selectors[i], i); // 初始化sub reactor
                threads[i] = new Thread(subReactors[i]); // 初始化运行sub reactor的线程
                threads[i].start(); // 启动（启动后的执行参考SubReactor里的run方法）
            }
        }

        @Override
        public void run() {
            SocketChannel socketChannel;
            try {
                socketChannel = serverSocketChannel.accept(); // 阻塞获取连接
                if (socketChannel != null) {
                    // 轮询reactors[] 处理接收到的请求
                    System.out.println(String.format("收到来自 %s 的连接", socketChannel.getRemoteAddress()));
                    socketChannel.configureBlocking(false); //

                    subReactors[next].registering(true);
                /*让线下一次subReactors的while循环不去执行
                 selector.select,但是select我们是使用的不超时阻塞的方式，
                 所以下一步需要执行wakeup()
                 * */

                    selectors[next].wakeup(); // 使一個阻塞住的selector操作立即返回

                    SelectionKey selectionKey = socketChannel.register(selectors[next],
                            SelectionKey.OP_READ); // 当前客户端通道SocketChannel
                    // 向selector[next]注册一个读事件，返回key

                    selectors[next].wakeup();
                    /*使一個阻塞住的selector操作立即返回，这样才能对刚刚注册的SelectionKey感兴趣
                     */

                    subReactors[next].registering(false); // 本次事件注册完成后，需要再次触发select的执行
                    // ，因此这里Restart要在设置回false（具体参考SubReactor里的run方法）
                    selectionKey.attach(new AsyncHandler(socketChannel, selectors[next]));
                    // 绑定Handler

                    // 轮询负载
                    if (++next == selectors.length) {
                        next = 0; // 越界后重新分配
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class SubReactor implements Runnable {
        private final Selector selector;
        private boolean register = false; // 注册开关表示，为什么要加这么个东西，可以参考Acceptor设置这个值那里的描述
        private int num; // 序号，也就是Acceptor初始化SubReactor时的下标

        SubReactor(Selector selector, int num) {
            this.selector = selector;
            this.num = num;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println(String.format("%d号SubReactor等待注册中...", num));
                while (!Thread.interrupted() && !register) {
                    try {
                        if (selector.select() == 0) {
                            continue;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator it = selectedKeys.iterator();
                    while (it.hasNext()) {
                        dispatch((SelectionKey) it.next());
                        it.remove();
                    }
                }
            }
        }

        private void dispatch(SelectionKey key) {
            Runnable r = (Runnable) (key.attachment());
            if (r != null) {
                r.run();
            }
        }

        void registering(boolean register) {
            this.register = register;
        }

    }

    static class AsyncHandler implements Runnable {

        private final Selector selector;

        private final SelectionKey selectionKey;
        private final SocketChannel socketChannel;

        private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        private ByteBuffer sendBuffer = ByteBuffer.allocate(2048);

        private final static int READ = 0; // 读取就绪
        private final static int SEND = 1; // 响应就绪
        private final static int PROCESSING = 2; // 处理中

        private int status = READ; // 所有连接完成后都是从一个读取动作开始的

        // 开启线程数为5的异步处理线程池
        private static final ExecutorService workers = Executors.newFixedThreadPool(5);

        public AsyncHandler(SocketChannel socketChannel, Selector selector) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            this.selector = selector;
            this.selector.wakeup();
        }

        @Override
        public void run() { // 如果一个任务正在异步处理，那么这个run是直接不触发任何处理的，read和send只负责简单的数据读取和响应，业务处理完全不阻塞这里的处理
            switch (status) {
                case READ:
                    read();
                    break;
                case SEND:
                    send();
                    break;
                default:
            }
        }

        private void read() {
            if (selectionKey.isValid()) {
                try {
                    readBuffer.clear();
                    int count = socketChannel.read(readBuffer);
                    if (count > 0) {
                        status = PROCESSING; // 置为处理中，处理完成后该状态为响应，表示读入处理完成，接下来可以响应客户端了
                        workers.execute(this::readWorker); // 异步处理
                    } else {
                        selectionKey.cancel();
                        socketChannel.close();
                        System.out.println("read时-------连接关闭");
                    }
                } catch (IOException e) {
                    System.err.println("处理read业务时发生异常！异常信息：" + e.getMessage());
                    selectionKey.cancel();
                    try {
                        socketChannel.close();
                    } catch (IOException e1) {
                        System.err.println("处理read业务关闭通道时发生异常！异常信息：" + e.getMessage());
                    }
                }
            }
        }

        void send() {
            if (selectionKey.isValid()) {
                status = PROCESSING; // 置为执行中
                workers.execute(this::sendWorker); // 异步处理
                selectionKey.interestOps(SelectionKey.OP_READ); // 重新设置为读
            }
        }

        // 读入信息后的业务处理
        private void readWorker() {
            //        try {
            //            Thread.sleep(5000L);
            //        } catch (InterruptedException e) {
            //            e.printStackTrace();
            //        }
            System.out.println(String.format("收到来自客户端的消息: %s",
                    new String(readBuffer.array())));

            status = SEND;

            selectionKey.interestOps(SelectionKey.OP_WRITE); // 把当前事件改为写事件
            this.selector.wakeup(); // 唤醒阻塞在select的线程，
            // 因为该interestOps写事件是放到子线程的，
            // select在该channel还是对read事件感兴趣时又被调用
            // ，因此如果不主动唤醒，
            // select可能并不会立刻select该读就绪事件（在该例中，可能永远不会被select到）
        }

        private void sendWorker() {
            try {
                sendBuffer.clear();
                sendBuffer.put(String.format("我收到来自%s的信息辣：%s,  200ok;",
                        socketChannel.getRemoteAddress(),
                        new String(readBuffer.array())).getBytes());
                sendBuffer.flip();

                int count = socketChannel.write(sendBuffer);

                if (count < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
                    System.out.println("send时-------连接关闭");
                } else {
                    // 再次切换到读
                    status = READ;
                }
            } catch (IOException e) {
                System.err.println("异步处理send业务时发生异常！异常信息：" + e.getMessage());
                selectionKey.cancel();
                try {
                    socketChannel.close();
                } catch (IOException e1) {
                    System.err.println("异步处理send业务关闭通道时发生异常！异常信息：" + e.getMessage());
                }
            }
        }
    }
}
