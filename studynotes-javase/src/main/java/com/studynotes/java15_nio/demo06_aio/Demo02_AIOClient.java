package com.studynotes.java15_nio.demo06_aio;

import com.studynotes.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Description:
 */
@Slf4j
public class Demo02_AIOClient {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> {
            EXECUTOR.execute(new AIOClient(i));
        });
    }

    @AllArgsConstructor
    static class AIOClient implements Runnable {

        private int i;

        @Override
        public void run() {
            try (AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();) {
                socketChannel.connect(new InetSocketAddress(CommonConstants.HOST, CommonConstants.PORT)).get();
                socketChannel.write(ByteBuffer.wrap(("It's client" + i + ", HelloServer").getBytes()));
                Thread.sleep(5000);
                socketChannel.write(ByteBuffer.wrap(("It's client" + i + ", After 5s, HelloServer").getBytes()));
                ByteBuffer buffer = ByteBuffer.allocate(512);
                Integer len = socketChannel.read(buffer).get();
                if (len != -1) {
                    if (log.isInfoEnabled()) {
                        log.info("客户端收到信息：" + new String(buffer.array(), 0, len));
                    }
                }
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
