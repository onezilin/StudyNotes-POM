package com.studynotes.java07_generic;

/**
 * Description: 泛型方法
 */
public class Demo04_GenericMethod<A> {

    /**
     * 和泛型类相同的泛型时，可以省略泛型 `<A>`
     */
    public <A> void f(A x) {
        System.out.println(x.getClass().getName());
    }

    /**
     * 在普通类中或者与泛型类不同的泛型时，必须要加上泛型 `<B>`
     */
    public <B> void g(B x) {
        System.out.println(x.getClass().getName());
    }


    /**
     * 在静态方法中，必须要加上泛型 `<A>`
     */

    public static <A> void h(A x) {
        System.out.println(x.getClass().getName());
    }

}
