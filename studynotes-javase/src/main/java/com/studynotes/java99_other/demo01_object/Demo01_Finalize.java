package com.studynotes.java99_other.demo01_object;

// finalize方法是对象被GC回收前执行的方法，只会被执行一次
public class Demo01_Finalize {
    public static void main(String[] args) {
        Book novel = new Book(true);
        novel.checkIn();

        Book b = new Book(true);
        b = null;
        System.gc();
    }

    static class Book {
        boolean checkOut = false;

        Book(boolean checkOut) {
            this.checkOut = checkOut;

        }

        void checkIn() {
            checkOut = false;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            if (this.checkOut) {
                System.out.println("Error: check out");
            }
        }
    }
}

