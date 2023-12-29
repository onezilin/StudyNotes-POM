package com.studynotes.java15_nio.demo03_nio;

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
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description: 使用 Selector 构建非阻塞式 IO，
 * 非阻塞式网络IO（select()时会阻塞线程）和传统的网络IO，优势是不会在IO部分阻塞（在read(byteBuffer)时不会阻塞）
 */
@Slf4j
public class Demo01_NIOServer {
    public static void main(String[] args) throws IOException {
        new MyServer(CommonConstants.PORT).run();
    }

    static class MyServer {
        private int port;

        MyServer(int port) {
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
                // 7、获取当前选择器中所有注册的“selectedKeys（已就绪的监听事件）”
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    // 8、获取准备“就绪”的事件
                    SelectionKey selectionKey = iterator.next();
                    // 9、对不同的就绪事件进行业务逻辑处理
                    if (selectionKey.isAcceptable()) {
                        handleAccept(selectionKey);
                    } else if (selectionKey.isReadable()) {
                        handleRead(selectionKey);
                    }
                    // 10、selectedKeys 不会重置，需要手动将元素从 selectedKeys 中移除
                    iterator.remove();
                }
            }
            // serverSocketChannel.close();
        }

        public void handleAccept(SelectionKey selectionKey) throws IOException {
            // 返回创建此键的通道，接受客户端建立连接的请求，并返回SocketChannel对象
            SocketChannel clientChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
            clientChannel.configureBlocking(false);
            // 将clientChannel注册到服务端的selector中，并添加附属对象（相当于可以传值）
            clientChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        }

        public void handleRead(SelectionKey selectionKey) throws IOException {
            // 1、获取客户端通信的通道
            SocketChannel client = (SocketChannel) selectionKey.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            while ((client.read(readBuffer)) > 0) {
                // 2、从客户端通道读取信息到buffer缓冲区中(并返回读到信息的字节数)
                readBuffer.flip();
                String request = StandardCharsets.UTF_8.newDecoder().decode(readBuffer).toString();
                log.info("客户端：" + client.getRemoteAddress() + "，请求：" + request);
                readBuffer.clear();
                // 3、响应
                if ("end connect".equals(request) || "".equals(request)) {
                    selectionKey.cancel();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    client.close();
                    break;
                } else {
                    try {
                        log.info("消息处理中");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String response = "已收到请求：" + request;
                    if (client.isConnected()) {
                        client.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
                    }
                }
            }
            System.out.println("继续处理后面的事情");
        }
    }
}
