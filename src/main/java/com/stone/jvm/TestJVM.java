package com.stone.jvm;

/**
 * @author stone
 * @date 2019/7/1 16:00
 * description
 */
public class TestJVM {
    public static void main(String[] args) {
        String str = System.getProperty("str");
        if (str == null) {
            System.out.println("stone");
        } else {
            System.out.println(str);
        }
    }
}
