package com.studynotes.java11_functional;

import java.util.function.*;

/**
 * Description: Function 类型的接口，接收一个类型的参数，返回一个类型的结果
 */
public class Demo05_Function {
    static Function<Foo, Bar> f1 = Bar::new;
    static IntFunction<IBaz> f2 = IBaz::new;
    static LongFunction<LBaz> f3 = LBaz::new;
    static DoubleFunction<DBaz> f4 = DBaz::new;
    static ToIntFunction<IBaz> f5 = ib -> ib.i;
    static ToLongFunction<LBaz> f6 = lb -> lb.l;
    static ToDoubleFunction<DBaz> f7 = db -> db.d;
    static IntToLongFunction f8 = i -> i;
    static IntToDoubleFunction f9 = i -> i;
    static LongToIntFunction f10 = l -> (int) l;
    static LongToDoubleFunction f11 = l -> l;
    static DoubleToIntFunction f12 = d -> (int) d;
    static DoubleToLongFunction f13 = d -> (long) d;

    public static void main(String[] args) {
        Bar b = f1.apply(new Foo());
        IBaz ib = f2.apply(11);
        LBaz lb = f3.apply(11);
        DBaz db = f4.apply(11);

        int i = f5.applyAsInt(ib);
        long l = f6.applyAsLong(lb);
        double d = f7.applyAsDouble(db);

        l = f8.applyAsLong(12);
        d = f9.applyAsDouble(12);
        i = f10.applyAsInt(12);
        d = f11.applyAsDouble(12);
        i = f12.applyAsInt(13.0);
        l = f13.applyAsLong(13.0);
    }

    // functional/FunctionVariants.java

    static class Foo {
    }

    static class Bar {
        Foo f;

        Bar(Foo f) {
            this.f = f;
        }
    }

    static class IBaz {
        int i;

        IBaz(int i) {
            this.i = i;
        }
    }

    static class LBaz {
        long l;

        LBaz(long l) {
            this.l = l;
        }
    }

    static class DBaz {
        double d;

        DBaz(double d) {
            this.d = d;
        }
    }
}


