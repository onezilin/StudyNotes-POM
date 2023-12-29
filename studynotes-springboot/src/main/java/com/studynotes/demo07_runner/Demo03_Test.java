package com.studynotes.demo07_runner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description: 测试
 */
@SpringBootTest
public class Demo03_Test {

    /**
     * 测试步骤：
     * 1. 启动项目
     * <p>
     * 预期：
     * 控制台打印出 MyCommandLineRunner 和 MyApplicationRunner，查看两者参数不同
     * <p>
     * 结论：
     * CommandLineRunner 和 ApplicationRunner 接口，两者都是在容器启动成功后自动执行，两者唯一区别在于 run()方法中传入的参数不同
     */
    @Test
    public void test() {
    }
}
