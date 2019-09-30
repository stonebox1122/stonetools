package com.stone.designpattern.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author stone
 * @date 2019/9/23 13:11
 * description 测试多线程环境下各种单例模式的效率
 */
public class SingletonClient2 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 100000; i1++) {
                        Object o = SingletonDCL.getInstance();
                    }

                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await(); // 阻塞main线程，直到计数器变为0，才会继续向下执行

        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end-start));

    }
}
