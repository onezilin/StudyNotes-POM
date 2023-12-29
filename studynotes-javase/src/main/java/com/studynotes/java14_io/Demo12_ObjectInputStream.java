package com.studynotes.java14_io;

import com.studynotes.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Description: ObjectInputStream 的 readObject() 反序列化功能
 */
public class Demo12_ObjectInputStream {

    private String fileName = "Person.bin";

    // 将 Person 对象反序列化为字节文件
    @Test
    void testOut() {
        try (FileOutputStream outputStream = new FileOutputStream(CommonUtil.getFile(fileName));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
            Person person = new Person("zhangsan", 22);
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIn() {
        try (FileInputStream inputStream = new FileInputStream(CommonUtil.getFile(fileName));
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);) {
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    static class Person implements Serializable {

        private String name;

        private int age;
    }
}
