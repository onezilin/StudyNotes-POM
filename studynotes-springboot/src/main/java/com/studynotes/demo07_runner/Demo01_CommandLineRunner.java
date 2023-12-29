package com.studynotes.demo07_runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: CommandLineRunner 接口，run()方法中传入的参数为 String 数组，可以获取到命令行中的参数
 */
@Component
@Order(1) // 设置该类在spring容器中的加载顺序，数字小优先级高
public class Demo01_CommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("MyCommandLineRunner");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}

