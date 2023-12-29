package com.studynotes.java05_enum;

/**
 * Description: 通过 enum 定义枚举类
 * 有以下特点:
 * * 枚举类是最终类，不可以被继承
 * * 枚举类中可以添加构造函数，**默认且只能**被 `private` 修饰，可以不用手动添加，因此不能被实例化
 * * 枚举类中的枚举常量是 public static final 类型，因此可以直接通过类名.常量名进行访问
 * * 枚举类不能继承其他类，但是可以实现接口
 */
public class Demo01_Enum {

    enum MyEnum {
        RED, GREEN, BLUE;

        /**
         * 只能被 private 修饰，因此不能被实例化
         */
        MyEnum() {

        }
    }

    static void test() {
        /**
         * 枚举常量是 public static final 类型，因此可以直接通过类名.常量名进行访问
         */
        System.out.println(MyEnum.RED);
    }

    /**
     * enum 是最终类，不可以被继承
     */
    // enum Son1 extends MyEnum {
    // }


    /**
     * 枚举类不能继承其他类，但是可以实现接口
     */
    // enum Son2 extends Demo01_Enum {
    // }

    /**
     * 枚举类不能继承其他类，但是可以实现接口
     */
    enum Son3 implements Runnable {
        /**
         * 注意：如果没有枚举常量，但是又有属性、方法时，必须要有这个分号，否则会报错。
         */
        ;

        @Override
        public void run() {

        }
    }
}


