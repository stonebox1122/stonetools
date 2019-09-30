package com.stone.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author stone
 * @date 2019/7/2 10:00
 * description 测试内存泄漏
 */
public class TestJvmOutOfMemory {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            String str = "";
            for (int j = 0; j < 1000; j++) {
                str += UUID.randomUUID().toString();
            }
            list.add(str);
        }
        System.out.println("ok");
    }
}
