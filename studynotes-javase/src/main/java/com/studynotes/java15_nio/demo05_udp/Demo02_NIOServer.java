package com.studynotes.java15_nio.demo05_udp;

import com.studynotes.constant.CommonConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * Description: UDP 服务端
 */
public class Demo02_NIOServer {

    public static void main(String[] args) {
        new MyServer(CommonConstants.PORT).run();
    }

    static class MyServer {

        private int port;

        MyServer(int port) {
            this.port = port;
        }

        public void run() {
            try {
                DatagramChannel datagramChannel = DatagramChannel.open();
                datagramChannel.bind(new InetSocketAddress(port));
                datagramChannel.configureBlocking(false);
                Selector selector = Selector.open();
                // UDP无连接，所以只需要监听read事件
                datagramChannel.register(selector, SelectionKey.OP_READ);
                while (selector.select() > 0) {
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    selectedKeys.forEach(selectionKey -> {
                        if (selectionKey.isValid() && selectionKey.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                            try {
                                datagramChannel.receive(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                                byteBuffer.clear();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    selectedKeys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
