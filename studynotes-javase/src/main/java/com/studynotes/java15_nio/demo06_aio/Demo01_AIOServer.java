package com.studynotes.java15_nio.demo06_aio;

import com.studynotes.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Description: <a href="https://blog.csdn.net/weixin_30342639/article/details/105892596">AIO 用法</a>
 * 测试 AIO 的用法，实现异步 IO
 */
@Slf4j
public class Demo01_AIOServer {

    public static void main(String[] args) {
        try (AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();) {
            serverChannel.bind(new InetSocketAddress(CommonConstants.PORT));
            // 注册 AcceptHandler
            serverChannel.accept(null, new AcceptHandler(serverChannel));

            System.out.println("继续做其他事");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AllArgsConstructor
    static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

        private AsynchronousServerSocketChannel serverChannel;

        @Override
        @SneakyThrows
        public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
            // 继续注册 AcceptHandler，如果不写这行代码后面的客户端连接连不上服务端
            serverChannel.accept(attachment, this);

            log.debug(String.valueOf(socketChannel.getRemoteAddress()));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 注册 ReadHandler
            socketChannel.read(buffer, attachment, new ReadHandler(socketChannel, buffer));
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            exc.printStackTrace();
        }
    }

    @AllArgsConstructor
    static class ReadHandler implements CompletionHandler<Integer, Object> {

        private AsynchronousSocketChannel socketChannel;

        private ByteBuffer buffer;

        @Override
        @SneakyThrows
        public void completed(Integer result, Object attachment) {
            // -1 表示已读完
            if (result == -1) return;

            System.out.println("异步收到消息，开始处理");
            buffer.flip();
            log.info(new String(buffer.array(), 0, result));
            buffer.clear();

            socketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));

            // 注册 ReadHandler，继续读取后续内容
            socketChannel.read(buffer, buffer, this);
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            exc.printStackTrace();
        }
    }
}
