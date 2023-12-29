package com.studynotes.java15_nio.demo02_bio;

import com.studynotes.constant.CommonConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Description: 阻塞式的网络 NIO，和传统的 OIO 一样会在 accept 和 IO 部分阻塞，只是这里使用 Channel 的写法
 */
public class Demo01_NIOBlockingServer {
    public static void main(String[] args) throws IOException {
        new MyServer(CommonConstants.PORT).run();
    }

    static class MyServer {

        private int port;

        MyServer(int port) {
            this.port = port;
        }

        public void run() throws IOException {
            // 1、实例化一个ServerSocketChannel，底层是将ServerSocket封装成ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 2、手动绑定监听的端口，ServerSocket是自动bind()了，监听客户端连接
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 3、获取客户端连接，会阻塞直到有客户端进行连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 4、获取客户端请求，像正常的FileChannel文件IO操作
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.remaining()));
                byteBuffer.clear();
            }
            // 5、服务器也可以响应客户端
            byteBuffer.put("text is success!".getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            /*
             * 6、主要要关闭SocketChannel，否则管道一直处于开启状态，服务器端不知道客户端有没有发完数据，也一直处于等待的状态，
             * 管道服务器可以主动关闭，客户端也可以主动关闭
             */
            socketChannel.close();
            // 7、关闭服务器
            serverSocketChannel.close();
        }
    }
}
