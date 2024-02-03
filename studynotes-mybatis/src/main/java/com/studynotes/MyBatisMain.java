package com.studynotes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.studynotes.demo01_base.dao")
public class MyBatisMain {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisMain.class, args);
    }
}
