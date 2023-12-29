package com.studynotes.java15_nio.demo03_nio;

import com.studynotes.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class Demo02_NIOClient {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        IntStream.range(0, 1).forEach(i ->
                EXECUTOR.execute(new MyClient(CommonConstants.PORT, "client" + i))
        );
        EXECUTOR.shutdown();
    }

    static class MyClient implements Runnable {
        private int port;
        private String clientName;

        MyClient(int port, String clientName) {
            this.port = port;
            this.clientName = clientName;
        }

        public void run() {
            try {
                SocketChannel client = SocketChannel.open(new InetSocketAddress(CommonConstants.HOST, port));
                /**
                 * 设置为非阻塞模式
                 */
                client.configureBlocking(false);
                String request = "这是" + clientName + "的请求";
                client.write(ByteBuffer.wrap(request.getBytes(StandardCharsets.UTF_8)));
                // 不需要关闭输出流
                // client.shutdownOutput();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                client.write(ByteBuffer.wrap("等等，我还有话要说".getBytes(StandardCharsets.UTF_8)));
                log.info("请求完毕");
                // 注册SocketChannel到Selector（也可以写成正常的阻塞式NIO）
                Selector selector = Selector.open();
                client.register(selector, SelectionKey.OP_READ);
                // 在一定时间内，没有监听到事件便退出阻塞
                while (selector.select(5000) > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()) {
                            handleRead(selectionKey);
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void handleRead(SelectionKey selectionKey) {
            SocketChannel client = (SocketChannel) selectionKey.channel();
            try {
                // 获取响应
                ByteBuffer readBuffer = ByteBuffer.allocate(100);
                if (client.read(readBuffer) > 0) {
                    readBuffer.flip();
                    log.info("服务器响应：" + new String(readBuffer.array(), 0, readBuffer.limit()));
                    readBuffer.clear();
                }
                // 自定义断开连接的逻辑
                client.write(ByteBuffer.wrap("end connect".getBytes(StandardCharsets.UTF_8)));
            } catch (IOException e) {
                // 服务器主动关闭 SocketChannel 时，IO 操作会抛异常，这里需要主动从 Selector 移除
                selectionKey.cancel();
                e.printStackTrace();
            }
        }
    }
}
