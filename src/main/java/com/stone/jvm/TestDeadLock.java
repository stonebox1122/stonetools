package com.stone.jvm;

/**
 * @author stone
 * @date 2019/7/2 10:51
 * description 测试死锁
 */
public class TestDeadLock {
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable{

        @Override
        public void run() {
            synchronized (obj1){
                System.out.println("Thead1 拿到了 obj1 的锁！");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj2){
                    System.out.println("Thread1 拿到了 obj2 的锁！");
                }
            }
        }
    }

    private static class Thread2 implements Runnable{

        @Override
        public void run() {
            synchronized (obj2){
                System.out.println("Thead2 拿到了 obj2 的锁！");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (obj1){
                    System.out.println("Thread2 拿到了 obj1 的锁！");
                }
            }
        }
    }
}
