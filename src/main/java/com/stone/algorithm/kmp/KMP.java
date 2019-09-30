package com.stone.algorithm.kmp;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/27 14:46
 * description KMP算法
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(Arrays.toString(kmpNext(str2)));
        System.out.println(kmpSearch(str1, str2));
    }

    /**
     * KMPs搜索
     *
     * @param str1 源字符串
     * @param str2 子字符串
     * @return
     */
    public static int kmpSearch(String str1, String str2) {
        int[] next = kmpNext(str2);
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串（子串）的部分匹配表
     * 子串：ABCDABD
     * 部分匹配表：0,0,0,0,1,2,0
     *
     * @param str
     * @return
     */
    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
