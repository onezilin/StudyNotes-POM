package com.studynotes.java15_nio.demo02_bio;

import com.studynotes.constant.CommonConstants;
import com.studynotes.util.CommonUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.StandardOpenOption;

public class Demo02_NIOBlockingClient {

    public static void main(String[] args) throws IOException {
        new MyClient(CommonConstants.PORT).run();
    }

    public static class MyClient {
        private int port;

        MyClient(int port) {
            this.port = port;
        }

        public void run() throws IOException {
            // 1、连接到指定（主机+端口）
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(CommonConstants.HOST, port));
            // 2、像FileChannel的文件IO一样操作
            FileChannel fi = FileChannel.open(CommonUtil.getFile().toPath(), StandardOpenOption.READ);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fi.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            /*
             * 3、客户端显示的告诉服务器端已经结束写数据了
             * 从Socket管道中读取从服务器端发回来的消息，但是因为服务器端一直在等待客户端继续输入，客户端一直等待服务器端的回复，导致阻塞了。
             */
            socketChannel.shutdownOutput();
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                byteBuffer.clear();
            }
            // 4、注意需要关闭FileChannel，否则管道一直处于开启状态，并且一直占有该文件读取，
            fi.close();
            /*
             * 5、注意需要关闭SocketChannel，否则管道一直处于开启状态，导致服务器端不知道客户端有没有发完数据，也一直处于等待的状态
             * 管道服务器可以主动关闭，客户端也可以主动关闭
             */
            socketChannel.close();
        }
    }
}
