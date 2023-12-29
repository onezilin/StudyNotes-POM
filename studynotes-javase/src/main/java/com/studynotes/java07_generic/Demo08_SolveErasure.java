package com.studynotes.java07_generic;

/**
 * Description: 由于泛型擦除：
 * 无法知道泛型是什么类型，无法执行泛型的方法和属性
 * 无法对对象使用【arg instanceof T】，需要使用【arg.instance(class)】
 * 无法实例泛型、泛型数组（可以进行类型强转，可能会抛类型转换异常）
 */
public class Demo08_SolveErasure {
    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }

    static class Building {
    }

    static class House extends Building {
    }

    static class ClassTypeCapture<T> {
        Class<T> kind;

        private final int SIZE = 100;

        // 上面的instanceof 可以使用 isInstance()方法替代
        public ClassTypeCapture(Class<T> kind) {
            this.kind = kind;
        }

        public boolean f(Object arg) {
            return kind.isInstance(arg);
        }

        public void g(Object arg) {

            // 不可以使用instanceOf
            //        if (arg instanceof T) {
            //        }

            // 不可以实例泛型
            //        T var = new T();
            T var = (T) new Object();

            // 不可以实例泛型数组
            //        T[] array = new T[SIZE];
            T[] array = (T[]) new Object[SIZE];
        }
    }
}


