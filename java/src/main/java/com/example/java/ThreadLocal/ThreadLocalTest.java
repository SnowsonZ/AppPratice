package com.example.java.ThreadLocal;

/**
 * ThreadLocal 存储变量在线程中的副本，避免共享变量造成的同步问题，隔离多线程，独立访问；
 */
public class ThreadLocalTest {
    public static void main(String[] args) {

        class Test {
            private ThreadLocal<String> strThreadLocal = new ThreadLocal<>();
            private ThreadLocal<Long> intThreadLocal = new ThreadLocal<>();

            public void set() {
                strThreadLocal.set(Thread.currentThread().getName());
                intThreadLocal.set(Thread.currentThread().getId());
            }

            public String getName() {
                return "Thread Name: " + strThreadLocal.get();
            }

            public String getId() {
                return "Thread ID: " + intThreadLocal.get();
            }
        }

        Test test = new Test();
        test.set();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.set();
                System.out.println(test.getId());
                System.out.println(test.getName());
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.getId());
        System.out.println(test.getName());

    }

}
