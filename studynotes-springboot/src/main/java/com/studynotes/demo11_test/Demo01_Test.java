package com.studynotes.demo11_test;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description:
 */
@Component
public class Demo01_Test {

    private Demo demo;

    public Demo01_Test(Demo demo) {
        this.demo = demo;
    }

    public Demo01_Test(String s) {
        System.out.println(s);
    }

    @PostConstruct
    public void init() {
        System.out.println(demo);
    }
}
