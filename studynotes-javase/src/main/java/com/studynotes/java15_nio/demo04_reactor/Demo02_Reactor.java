package com.studynotes.java15_nio.demo04_reactor;

import com.studynotes.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description: 单 Reactor 多线程模型：单线程用于处理Channel的接收和监听事件的派发（派发给不同线程做）
 * <p>
 * 可能会在接收和派发过程中阻塞
 */
@Slf4j
public class Demo02_Reactor {

    public static void main(String[] args) throws IOException {
        new Reactor(CommonConstants.PORT).run();
    }

    static class Reactor {
        private int port;

        private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

        Reactor(int port) {
            this.port = port;
        }

        public void run() throws IOException {
            // 1、实例ServerSocketChannel，监听对应端口
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 2、切换非阻塞式模式
            serverSocketChannel.configureBlocking(false);
            // 4、获取选择器
            Selector selector = Selector.open();
            // 5、将通道注册到选择器上，并且指定“监听接收事件”
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 6.轮询式的获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {
                // 7、获取当前选择器中所有注册的“选择键（已就绪的监听事件）”
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    // 8、获取准备“就绪”的事件
                    SelectionKey selectionKey = iterator.next();
                    /**
                     * 在多线程下，可能会在select()后调用selectionKey.cancel()，将selectionKey中的valid置为false
                     * 导致抛异常，因此还要检查一次selectionKey是否有效
                     */
                    if (!selectionKey.isValid()) continue;
                    // 9、对不同的就绪事件进行业务逻辑处理
                    if (selectionKey.isAcceptable()) {
                        handleAccept(selectionKey);
                    } else if (selectionKey.isReadable()) {
                        EXECUTOR.execute(new ReadHandler(selectionKey, selector));
                    }
                    // 10、取消选择键SelectionKey
                    iterator.remove();
                }
            }
        }

        public void handleAccept(SelectionKey selectionKey) throws IOException {
            // 返回创建此键的通道，接受客户端建立连接的请求，并返回SocketChannel对象
            SocketChannel clientChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
            clientChannel.configureBlocking(false);
            // 将clientChannel注册到服务端的selector中，并添加附属对象（相当于可以传值）
            clientChannel.register(selectionKey.selector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    // 多线程体现在处理read/write时采用线程池执行
    static class ReadHandler implements Runnable {

        private SelectionKey selectionKey;

        private final Selector selector;

        ReadHandler(SelectionKey selectionKey, Selector selector) {
            this.selectionKey = selectionKey;
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                // 1、获取客户端通信的通道
                SocketChannel client = (SocketChannel) selectionKey.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(100);
                while (client.read(readBuffer) > 0) {
                    // 2、从客户端通道读取信息到buffer缓冲区中(并返回读到信息的字节数)
                    readBuffer.flip();
                    String request = StandardCharsets.UTF_8.newDecoder().decode(readBuffer).toString();
                    log.info("客户端：" + client.getRemoteAddress() + "，请求：" + request);
                    readBuffer.clear();
                    // 3、响应
                    if ("end connect".equals(request) || "".equals(request)) {
                        selectionKey.cancel();
                        //                        client.close();
                    } else {
                        try {
                            log.info("消息处理中");
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String response = "已收到请求：" + request;
                        client.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
