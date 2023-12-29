package com.studynotes.java07_generic;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: FastJson 中 TypeReference 的使用，用于解决 JSON 数据类型转换时泛型擦除问题
 */
@Slf4j
public class Demo17_TypeReference {

    /**
     * Description: 测试 TypeReference 使用
     */
    @Test
    void test() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);

        JSONObject o = new JSONObject();
        o.put("k", list);
        o.put("j", new MyClass());

        // list中加入Integer类型元素，这里获取的List对象就是元素类型Integer
        List<String> types = o.getObject("k", List.class);
        System.out.println(JSON.toJSONString(types));

        // 通过TypeReference将List<Integer>中泛型映射成List<String>类型
        List<String> types2 = o.getObject("k", new TypeReference<List<String>>() {
        });
        System.out.println(JSON.toJSONString(types2));

        MyClass types3 = o.getObject("j", MyClass.class);
        System.out.println(JSON.toJSONString(types3));
    }

    class MyClass {
    }
}
