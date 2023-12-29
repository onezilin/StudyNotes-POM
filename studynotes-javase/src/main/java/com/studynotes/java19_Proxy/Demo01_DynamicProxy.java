package com.studynotes.java19_Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

// 动态代理
public class Demo01_DynamicProxy {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
        // 获取被代理类的所有接口的Class
        Class[] interfaces = userServiceImpl.getClass().getInterfaces();
        InvocationHandler logHandler = new LogHandler(userServiceImpl);
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        proxy.select();
        proxy.update();
    }
}

interface UserService {
    void select();
    void update();
}

class UserServiceImpl implements UserService {
    public void select() {
        System.out.println("查询 selectById");
    }
    public void update() {
        System.out.println("更新 update");
    }
}

class LogHandler implements InvocationHandler {
    Object target;  // 被代理的对象，实际的方法执行者

    public LogHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        System.out.println(proxy.getClass());
        Object result = method.invoke(target, args);
        after();
        return result;
    }
    // 调用invoke方法之前执行
    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }
    // 调用invoke方法之后执行
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
