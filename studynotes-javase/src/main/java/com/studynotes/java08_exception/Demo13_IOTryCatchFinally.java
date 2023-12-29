package com.studynotes.java08_exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Description: 构造器中 IO 操作的try-catch, 不能在构造中关闭 IO 流, 正确的方法是像下面调用构造后再包裹一个try catch
 */
public class Demo13_IOTryCatchFinally {

    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("Cleanup.java");
            // finally 只针对与其匹配的 try-catch，进入此 try 之前就 throw，就不会执行下面的 finally 了
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null)
                    System.out.println(s);
            } catch (Exception e) {
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.out);
            } finally {
                System.out.println("进入 finally 了");
                in.dispose();
            }
        } catch (Exception e) {
            System.out.println("InputFile construction failed");
        }
    }


    static class InputFile {
        private BufferedReader in;

        public InputFile(String fname) throws Exception {
            try {
                in = new BufferedReader(new FileReader(fname));
            } catch (FileNotFoundException e) {
                System.out.println("Could not open " + fname);
                // 还没开启，不用 close
                throw e;
            } catch (Exception e) {
                // 已经开启，其他异常必须 close
                try {
                    in.close();
                } catch (IOException e2) {
                    System.out.println("in.close() unsuccessful");
                }
                throw e;
            } finally {
                // 不能在此处关闭 IO，否则使用不了
            }
        }

        public String getLine() {
            String s;
            try {
                s = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException("readLine() failed", e);
            }
            return s;
        }

        public void dispose() {
            try {
                in.close();
                System.out.println("dispose() successful");
            } catch (IOException e) {
                throw new RuntimeException("in.close() failed", e);
            }
        }
    }
}
