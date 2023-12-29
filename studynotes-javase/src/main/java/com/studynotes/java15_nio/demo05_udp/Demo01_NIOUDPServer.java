package com.studynotes.java15_nio.demo05_udp;

import com.studynotes.constant.CommonConstants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * Description: UDP 连接
 */
public class Demo01_NIOUDPServer {

    @Test
    void send() throws IOException {
        DatagramChannel sendChannel = DatagramChannel.open();
        InetSocketAddress sendAddress = new InetSocketAddress(CommonConstants.HOST, CommonConstants.PORT);

        // 发送
        ByteBuffer buffer = ByteBuffer.wrap("我是 UDP 连接".getBytes(StandardCharsets.UTF_8));
        sendChannel.send(buffer, sendAddress);
        System.out.println("已经发送完成");

        sendChannel.close();
    }

    @Test
    void receive() throws IOException {
        // 打开 DatagramChannel
        DatagramChannel receiveChannel = DatagramChannel.open();
        // 绑定端口
        receiveChannel.bind(new InetSocketAddress(CommonConstants.PORT));
        // 关闭阻塞模式
        receiveChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketAddress socketAddress;

        // 获取端口接收的数据，关闭阻塞模式后，receive() 方法不会阻塞
        while ((socketAddress = receiveChannel.receive(buffer)) != null) {
            buffer.flip();
            System.out.println(socketAddress);
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
            buffer.clear();
        }
    }
}

