package com.studynotes.java15_nio.demo01;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

// 缓冲区相当于一个数组，用于存储和获取某种类型的数据
// nio提供多种不同类型的缓冲区
// buffer并不会删除数据，而是使用position和limit进行覆盖并让我们读不到原来的数据
public class Demo01_ByteBuffer {
    // 分配一个指定大小的缓冲区
    private static ByteBuffer buf = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        initBuffer();
        writeBuffer();
        readBuffer();
        repeatReadBuffer();
        clearBuffer();
    }

    public static void initBuffer() {
        System.out.println("--------------allocate()----------------");
        // 是下一个读写操作的下标。读时，下一个要读的位置；写时，下一个要写入的位置
        System.out.println(buf.position());
        // 写模式下，limit等于capacity
        // 读模式下，limit等于position，相当于缓冲区中数据的长度
        System.out.println(buf.limit());
        // 缓冲区的最大长度
        System.out.println(buf.capacity());
    }

    /**
     * put() 将数据写入 Buffer 中
     */
    public static void writeBuffer() {
        buf.put("abcde".getBytes());

        System.out.println("-------------put()-------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
    }

    /**
     * 切换为读模式，将 limit 置为 position，position 置为 0，mark 置为-1
     */
    public static void readBuffer() {
        buf.flip();

        // 若在读模式下进行写入操作，
        // 1、会改变Buffer的position位置，get(buffer[])时会进行判断，然后报错
        //        buf.put("g".getBytes());
        // 2、若真的需要在读的时候改，需要使用rewind重置position的值
        //        buf.rewind();

        System.out.println("--------------flip()------------");
        System.out.println(buf.position());// 0
        System.out.println(buf.limit());// 5
        System.out.println(buf.capacity());// 1024

        byte[] dst = new byte[buf.limit()];
        // 将buf中的数据传输到目标dst
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("--------------get()------------");
        System.out.println(buf.position());// 5
        System.out.println(buf.limit());// 5
        System.out.println(buf.capacity());// 1024
    }

    /**
     * 重置当前参数： 将position设置为0，mark设置为-1
     * 读模式下，从头开始读；写模式下，重新写入
     */
    public static void repeatReadBuffer() {
        buf.rewind();

        System.out.println("--------------rewind()------------");
        System.out.println(buf.position());// 0
        System.out.println(buf.limit());// 5
        System.out.println(buf.capacity());// 1024
    }

    /**
     * close() 转换为写模式，将 position 置为 0，limit 置为 capacity，mark 置为-1
     */
    public static void clearBuffer() {
        buf.clear();

        System.out.println("--------------clear()------------");
        System.out.println(buf.position());// 0
        System.out.println(buf.limit());// 1024
        System.out.println(buf.capacity());// 1024

        // 虽然执行clear()，但是buffer中的数据并没有被删除，仍然可以获取position或指定位置的数据
        System.out.println((char) buf.get());
    }

    /**
     * mark() 用于标记当前 position 位置，在 reset() 后 position 回到 mark 的位置
     */
    @Test
    void remainBuffer() {
        System.out.println("--------------mark()------------");
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));// ab
        System.out.println(buf.position());// 2

        // mark()，将mark置为当前的position
        buf.mark();

        buf.get(dst, 2, 2);// 再读两个位置
        System.out.println(new String(dst, 2, 2));// cd
        System.out.println(buf.position());// 4

        // reset()，将position置为mark，相当于回到上一个位置
        buf.reset();
        System.out.println(buf.position());// 2

        // 判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            // 获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());// 3
        }
    }

    /**
     * 当前Buffer分配直接缓冲区
     */
    @Test
    void isDirectBuffer() {
        System.out.println("--------------allocateDirect()------------");
        buf = ByteBuffer.allocateDirect(1024);
        // 是否是直接缓冲区
        System.out.println(buf.isDirect());
    }
}
