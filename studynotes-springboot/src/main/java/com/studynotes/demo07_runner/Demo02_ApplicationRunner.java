package com.studynotes.demo07_runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: ApplicationRunner 接口，run()方法中传入的参数为 ApplicationArguments，
 * ApplicationArguments 会对 SpringBoot 程序的启动参数进行解析和分类
 */
@Component
@Order(2)
public class Demo02_ApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner");
        System.out.println(args.getOptionNames());
        System.out.println(args.getNonOptionArgs());
    }
}
