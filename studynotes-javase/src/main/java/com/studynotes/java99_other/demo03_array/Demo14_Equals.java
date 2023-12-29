package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 数组的equals方法首先比较数组的大小是否相等，然后让每一个对应索引的元素调用本身对象相应的equals方法进行比较
// 对于多维数组，equals会先比较第一维的数组大小，再让一维数组的值（也就是地址）进行比较，会出现false的情况，所以用deepEquals

public class Demo14_Equals {
    public static final int SZ = 15;

    static String[][] twoDArray() {
        String[][] md = new String[5][];
        Arrays.setAll(md, n -> new String[n]);
        //	     for (int i = 0; i < md.length; i++) Arrays.setAll(md[i], new Rand.String()::get);
        return md;
    }

    public static void main(String[] args) {
        int[] a = new int[SZ];
        int[] b = new int[SZ];
        Arrays.setAll(a, n -> n);
        Arrays.setAll(b, n -> n);
        System.out.println("a == b: " + Arrays.equals(a, b));

        b[3] = 11;
        System.out.println("a == b: " + Arrays.equals(a, b));

        Integer[] c = new Integer[SZ], d = new Integer[SZ];
        Arrays.setAll(c, Integer::new);
        Arrays.setAll(d, Integer::new);
        System.out.println("c == c: " + Arrays.equals(c, d));

        d[3] = 11;
        System.out.println("c == d: " + Arrays.equals(c, d));

        String[][] e = twoDArray(), f = twoDArray();
        System.out.println(Arrays.deepToString(e));
        System.out.println("e == f: " + Arrays.equals(e, f));
        System.out.println("deepEquals(e, f): " + Arrays.deepEquals(e, f));

        e[4][1] = "#$#$#$#";
        System.out.println("e == f: " + Arrays.equals(e, f));
        System.out.println("deepEquals(e, f): " + Arrays.deepEquals(e, f));
    }
}

/* Output:
a1 == a2: true
a1 == a2: false
a1w == a2w: true
a1w == a2w: false
[[], [btpenpc], [btpenpc, cuxszgv], [btpenpc, cuxszgv,
gmeinne], [btpenpc, cuxszgv, gmeinne, eloztdv]]
deepEquals(md1, md2): true
md1 == md2: false
[[], [btpenpc], [btpenpc, cuxszgv], [btpenpc, cuxszgv,
gmeinne], [btpenpc, #$#$#$#, gmeinne, eloztdv]]
deepEquals(md1, md2): false
*/
