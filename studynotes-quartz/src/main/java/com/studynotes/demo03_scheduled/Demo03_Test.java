package com.studynotes.demo03_scheduled;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Description: 测试
 */
@SpringBootTest
public class Demo03_Test {

    /**
     * Description:
     * 测试步骤：
     * 将 Demo01_ScheduledAnnotation 中的 @Scheduled 注解放开，查看每种参数的执行效果
     */
    @Test
    public void test() {
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}
