package com.stone.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stone
 * @date 2019/6/19 15:28
 * description 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8, 8};
        int result = binarySearch(arr, 0, arr.length - 1, 8);
        System.out.println(result);
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 8);
        System.out.println(list);
    }

    /**
     * 二分法查找
     *
     * @param arr   有序数组
     * @param left  左边索引
     * @param right 右边索引
     * @param value 查找的值
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (midValue < value) {
            return binarySearch(arr, mid + 1, right, value);
        } else if (midValue > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    /**
     * 找到所有的元素
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (midValue < value) {
            return binarySearch2(arr, mid + 1, right, value);
        } else if (midValue > value) {
            return binarySearch2(arr, left, mid - 1, value);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(mid);

            int temp = mid - 1;
//            while (true) {
//                if (temp < 0 || arr[temp] != value) {
//                    break;
//                }
//                list.add(temp);
//                temp--;
//            }
            for (int i = temp; i >= 0; i--) {
                if (arr[i] == value) {
                    list.add(i);
                }
            }

            temp = mid + 1;
//            while (true) {
//                if (temp > arr.length-1 || arr[temp] != value) {
//                    break;
//                }
//                list.add(temp);
//                temp++;
//            }
            for (int i = temp; i <= arr.length - 1; i++) {
                if (arr[i] == value) {
                    list.add(i);
                }
            }

            return list;
        }
    }
}
