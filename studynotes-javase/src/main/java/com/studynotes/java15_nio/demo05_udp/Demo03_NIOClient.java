package com.studynotes.java15_nio.demo05_udp;

import com.studynotes.constant.CommonConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Description: UDP 客户端
 */
public class Demo03_NIOClient {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> {
            EXECUTOR.execute(new MyClient(CommonConstants.PORT, "client" + i));
        });
    }

    static class MyClient implements Runnable {

        private String name;

        private int port;

        MyClient(int port, String name) {
            this.port = port;
            this.name = name;
        }

        public void run() {
            try {
                DatagramChannel datagramChannel = DatagramChannel.open();
                datagramChannel.configureBlocking(false);

                InetSocketAddress address = new InetSocketAddress(CommonConstants.HOST, port);

                datagramChannel.send(ByteBuffer.wrap((name + "测试UDP发送").getBytes()), address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
