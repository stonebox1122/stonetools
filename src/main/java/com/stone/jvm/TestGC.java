package com.stone.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @author stone
 * @date 2019/7/2 14:21
 * description 测试垃圾回收器
 */
public class TestGC {
    public static void main(String[] args) throws Exception {
        List<Object> list = new ArrayList<>();
        while (true) {
            int sleep = new Random().nextInt(100);
            if (System.currentTimeMillis() % 2 == 0) {
                list.clear();
            } else {
                for (int i = 0; i < 10000; i++) {
                    Properties properties = new Properties();
                    properties.put("key_"+i,"value_"+System.currentTimeMillis()+"_"+i);
                    list.add(properties);
                }
            }
            Thread.sleep(sleep);
        }
    }
}
