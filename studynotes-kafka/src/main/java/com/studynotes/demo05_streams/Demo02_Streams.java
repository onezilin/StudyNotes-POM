package com.studynotes.demo05_streams;

import com.studynotes.constant.CommonConstants;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * Description: 通过 Kafka Streams，实现 consumer-transfer-produce 模式，即从 topic-source 消费消息，传入 topic-sink，
 * 并且对消息进行处理，去除相同的 key，只保留最新的 value
 */
public class Demo02_Streams {

    private static final String SOURCE = "streams-plaintext-input";

    private static final String SINK = "streams-wordcount-output";

    private static Properties getStreamsProperties() {
        Properties props = new Properties();
        // streams 应用 id，唯一
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.KAFKA_BROKER_LIST);
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        // 序列化器
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return props;
    }

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();
        // 获取 source 的 KStream 流
        // KStream 是一个由键值对构成的抽象记录流，即使相同的 key 也不会被覆盖
        KStream<String, String> source = builder.stream(SOURCE);
        // KTable 是一个由键值对构成的抽象记录流，相同的 key 只保留最新的一个 value
        // KTable 和 KStream 可以互相转换
        KTable<String, Long> counts =
                source.flatMapValues(value -> Arrays.asList(value.toLowerCase((Locale.getDefault())).split(" ")))
                        .groupBy((key, value) -> value)
                        .count();
        // 写入 sink
        counts.toStream().to(SINK, Produced.with(Serdes.String(), Serdes.Long()));

        // 开启 Streams 流处理功能
        final KafkaStreams streams = new KafkaStreams(builder.build(), getStreamsProperties());
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (InterruptedException e) {
            System.exit(1);
            e.printStackTrace();
        }
        System.exit(0);
    }
}
