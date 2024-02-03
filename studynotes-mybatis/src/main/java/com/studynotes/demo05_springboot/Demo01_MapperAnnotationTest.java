package com.studynotes.demo05_springboot;

import com.studynotes.MyBatisMain;
import com.studynotes.demo01_base.dao.UserDao;
import com.studynotes.demo01_base.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Description: 测试 @Mapper 和 @MapperScan 注解
 *
 * 注解 @Mapper 可以为指定接口生成对应的代理类，并注册到 Spring 容器中
 * 注解 @MapperScan 可以扫描指定包下的所有接口，并为其生成代理类
 */
@SpringBootTest
public class Demo01_MapperAnnotationTest {

    @Resource
    private UserDao userDao;

    /**
     * Description:
     * 测试步骤：
     * 1、在 {@link com.studynotes.demo01_base.dao.UserDao} 上添加 @Mapper 注解
     * 2、执行程序
     * <p>
     * 预期结果：
     * 1、程序正常执行
     */
    @Test
    public void test1() {
        User user = userDao.queryById(1L);
        System.out.println(user);
    }

    /**
     * Description:
     * 测试步骤：
     * 1、在 {@link MyBatisMain} 上添加 @MapperScan 注解
     * 2、在启动类上添加 @MapperScan 注解
     * 3、执行程序
     * <p>
     * 预期结果：
     * 1、程序正常执行
     */
    @Test
    public void test2() {
        User user = userDao.queryById(1L);
        System.out.println(user);
    }
}
