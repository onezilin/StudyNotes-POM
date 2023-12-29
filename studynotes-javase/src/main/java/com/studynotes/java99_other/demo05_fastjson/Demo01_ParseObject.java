package com.studynotes.java99_other.demo05_fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description: 测试 fastjson 的 parseObject 方法，及其 TypeReference 类型
 */
public class Demo01_ParseObject {

    public static void main(String[] args) {
        InnerClass innerClass1 = new InnerClass("1", "值");

        User user = new User("张三", innerClass1, Collections.singletonList(innerClass1));
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(user), new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class User {
        private String name;

        private InnerClass innerClass;

        private List<InnerClass> innerClassList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class InnerClass {

        private String id;

        private String value;
    }
}
