package com.stone.datastructure;

import java.util.Arrays;

/**
 * @author stone
 * @date 2019/6/4 17:30
 * description
 */
public class Sort {

    /**
     * 冒泡排序
     * 4,3,2,1
     * 3,2,1,4 第1轮
     * 2,1,3,4 第2轮
     * 1,2,3,4 第3轮
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 比较的轮数，共（长度-1）轮，比如4个数字，第1轮将最大数字移动到最后，第3轮将第2小数字放到第2个位置
        for (int i = 0; i < arr.length - 1; i++) {
            // 比较的次数，上一轮找到的最大值就不需要比较了
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 在每1轮中，两两比较，大则交换，最终将最大的数字交换到最后
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序，此方式是从右边开始找比基准数小的，找到后停止，然后替换左边当前下标的数，不是替换左边比基准数大的。
     * 5,6,4,7,3,2,9,1
     * 1,6,4,7,3,2,9,1
     * 1,6,4,7,3,2,9,6
     * 1,2,4,7,3,2,9,6
     * 1,2,4,7,3,7,9,6
     * 1,2,4,3,3,7,9,6
     * 1,2,4,3,5,7,9,6
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort1(int[] arr, int start, int end) {
        // 如果左边下标和右边下标相等，则退出递归
        if (start < end) {
            // 指定标准数，这里指定最左边为基准数，则需要先从右向左开始检索
            int standard = arr[start];
            // 左边下标，左边比较起始位置
            int low = start;
            // 右边下标，右边比较起始位置
            int high = end;
            // 从右向左找比标准数小的，从左向右找比标准数大的，进行交换，
            while (low < high) {
                // 从右向左，如果右边的数字比标准数大，则继续向左移动，右边下标递减
                while (low < high && standard <= arr[high]) {
                    high--;
                }
                // 如果右边的数字比标准数小，则替换当前左边下标指定的数字
                arr[low] = arr[high];

                // 从左向右，如果左边的数字比标准数小，则继续向右移动，左边下标递增
                while (low < high && arr[low] <= standard) {
                    low++;
                }
                // 如果左边的数字比标准数大，则替换当前右边下标指定的数字
                arr[high] = arr[low];
            }
            // 如果左边下标和右边下标相等了，则将标准数赋给当前下标指定的数字
            arr[low] = standard;
            // 递归排序左边
            quickSort1(arr, start, low - 1);
            // 递归排序右边
            quickSort1(arr, low + 1, end);
        }
    }

    /**
     * 快速排序，此方式是从右边开始找比基准数小的，找到后停止，然后从左边开始找比基准数大的，找到后停止，然后交互。
     * 5,6,4,7,3,2,9,1
     * 5,1,4,7,3,2,9,6
     * 5,1,4,2,3,7,9,6
     * 3,1,4,2,5,7,9,6
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort2(int[] arr, int start, int end) {
        // 如果左边下标和右边下标相等，则退出递归
        if (start < end) {
            // 指定标准数，这里指定最左边为基准数，则需要先从右向左开始检索
            int standard = arr[start];
            // 左边下标，左边比较起始位置
            int low = start;
            // 右边下标，右边比较起始位置
            int high = end;
            // 从右向左找比标准数小的，从左向右找比标准数大的，进行交换，
            while (low < high) {
                // 从右向左，如果右边的数字比标准数大，则继续向左移动，右边下标递减
                while (low < high && standard <= arr[high]) {
                    high--;
                }

                // 从左向右，如果左边的数字比标准数小，则继续向右移动，左边下标递增
                while (low < high && arr[low] <= standard) {
                    low++;
                }

                // 进行交换
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }
            // 如果左边下标和右边下标相等了，则将当前下标值与标准数进行交换
            arr[start] = arr[low];
            arr[low] = standard;
            // 递归排序左边
            quickSort2(arr, start, low - 1);
            // 递归排序右边
            quickSort2(arr, low + 1, end);
        }
    }

    /**
     * 插入排序
     * 1,3,4,2
     * 1,3,4,4
     * 1,3,3,4
     * 1,2,3,4
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 如果当前数字比前一个数字小
            if (arr[i] < arr[i - 1]) {
                int target = arr[i];
                int j;
                // 遍历当前数字之前的所有数字，如果比当前数字大，则把遍历的数字赋给后一个
                for (j = i - 1; j >= 0 && target < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                // 如果当前数字大于或者当前前面的某个数字，则把当前数字赋给前面这个数字的后一个数字
                arr[j + 1] = target;
            }
        }
    }

    /**
     * 希尔排序
     * 5,6,4,7,3,2,9,1  长度为8，8/2=4，距离步长4的两个元素两两排序
     * --> 3,6,4,7,5,2,9,1 --> 3,2,4,7,5,6,9,1 --> 3,2,4,1,5,6,9,7
     * 3,2,4,1,5,6,9,7  4/2=2，距离步长为2的四个元素进行排序
     * --> 3,1,4,2,5,6,9,7
     * 3,1,4,2,5,6,9,7  2/2=1，距离步长为1，即所有元素进行排序
     * --> 1,3,4,2,5,6,9,7 --> 1,3,2,4,5,6,9,7 --> 1,2,3,4,5,6,9,7 --> 1,2,3,4,5,6,7,9
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        // 遍历所有步长
        for (int d = arr.length / 2; d > 0; d /= 2) {
            // 遍历所有元素
            for (int i = d; i < arr.length; i++) {
                // 遍历本组中所有的元素，满足添加进行交换
                for (int j = i - d; j >= 0; j -= d) {
                    // 当前元素大于加上步长后的元素就进行交换
                    if (arr[j] > arr[j + d]) {
                        int temp = arr[j];
                        arr[j] = arr[j + d];
                        arr[j + d] = temp;
                    }
                }
            }
        }
    }

    /**
     * 选择排序
     * 5,6,4,7,3,2,9,1
     * 1,6,4,7,3,2,9,5
     * 1,2,4,7,3,6,9,5
     * 1,2,3,7,4,6,9,5
     * 1,2,3,4,7,6,9,5
     * 1,2,3,4,5,6,9,7
     * 1,2,3,4,5,6,7,9
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        // 遍历所有元素
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 当前遍历的数与后面所有的数依次进行比较，记录最小数的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 如果最小数和当前遍历的数下标不一致，则进行交换，将最小数放在前面
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 归并数组，如果数组前面部分和后面部分都是有序的，则可以直接归并，结果就是有序的
     * 1,3,2,4,6,8
     * 1,0,0,0,0,0（临时数组）
     * 1,2,0,0,0,0（临时数组）
     * 1,2,3,0,0,0（临时数组）
     * 1,2,3,4,0,0（临时数组）
     * 1,2,3,4,6,8（临时数组）
     *
     * @param arr
     * @param low
     * @param middle
     * @param high
     */
    public static void merge(int[] arr, int low, int middle, int high) {
        // 用于储存归并后的临时数组
        int[] temp = new int[high - low + 1];
        // 记录前部分数组中需要遍历的下标（有序）
        int i = low;
        // 记录后部分数组中需要遍历的下标（有序）
        int j = middle + 1;
        // 记录临时数组中存放的下标
        int index = 0;
        // 遍历前部分和后部分数组，依次比较，将小的放入临时数组
        while (i <= middle && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[index] = arr[i];
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        // 处理前部分多余的数据（如果有）
        while (i <= middle) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        // 处理后部分多余的数据（如果有）
        while (j <= high) {
            temp[index] = arr[j];
            j++;
            index++;
        }

        for (int k = 0; k < temp.length; k++) {
            arr[k + low] = temp[k];
        }
    }

    /**
     * 归并排序
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void mergeSort(int[] arr, int low, int high) {
        int middle = (low + high) / 2;
        if (low < high) {
            // 处理左边
            mergeSort(arr, low, middle);
            // 处理右边
            mergeSort(arr, middle + 1, high);
            // 归并
            merge(arr, low, middle, high);
        }
    }

    /**
     * 基数排序（使用数组实现）
     * [32, 6, 421, 75, 3, 219]
     * [421, 32, 3, 75, 6, 219]（按照个位数排序）
     * [3, 6, 219, 421, 32, 75]（按照十位数排序）
     * [3, 6, 32, 75, 219, 421]（按照百位数排序）
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        // 找到数组的最大值，根据位数来确定循环的轮数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        // 声明一个二维数组来保存各个元素，按照其个位，十位等在0~9中排序
        int[][] c = new int[10][arr.length];

        // 声明一个一维数组来保存在二维数组的0~9中元素的个数，实际上就是记录前面二维数组第二维的下标
        int[] d = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 第1次循环获取各个元素的个位数，放入到二维数组0~9对应位置，同时更新一维数组0~9对应位置元素个数
            for (int j = 0; j < arr.length; j++) {
                int e = arr[j] / n % 10;
                c[e][d[e]] = arr[j];
                d[e]++;
            }

            // 从二维数组中取出元素回写到原始数组
            int index = 0;
            for (int j = 0; j < d.length; j++) {
                if (d[j] != 0) {
                    // 从一维数组中获取元素在二维数组中的下标
                    for (int k = 0; k < d[j]; k++) {
                        arr[index] = c[j][k];
                        index++;
                    }
                    // 把一维数组中不为0的元素置为0
                    d[j] = 0;
                }
            }
        }
    }

    /**
     * 基数排序（使用队列实现）
     * [32, 6, 421, 75, 3, 219]
     * [421, 32, 3, 75, 6, 219]（按照个位数排序）
     * [3, 6, 219, 421, 32, 75]（按照十位数排序）
     * [3, 6, 32, 75, 219, 421]（按照百位数排序）
     *
     * @param arr
     */
    public static void radixQueueSort(int[] arr) {
        // 找到数组的最大值，根据位数来确定循环的轮数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        // 声明一个队列数组来保存各个元素，按照其个位，十位等在0~9中排序
        MyQueue[] c = new MyQueue[10];
        for (int i = 0; i < c.length; i++) {
            c[i] = new MyQueue();
        }

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 第1次循环获取各个元素的个位数，放入到队列数组0~9对应位置
            for (int j = 0; j < arr.length; j++) {
                int e = arr[j] / n % 10;
                c[e].add(arr[j]);
            }

            // 从队列中取出元素回写到原始数组
            int index = 0;
            for (int j = 0; j < c.length; j++) {
                while (!c[j].isEmpty()) {
                    arr[index] = c[j].poll();
                    index++;
                }
            }
        }
    }

    /**
     * 该函数假设一个元素的两个子节点都满足最大堆的性质(左右子树都是最大堆)，只有根元素可能违反最大堆性质，
     * 那么把该元素以及左右子节点的最大元素找出来，如果该元素已经最大，那么整棵树都是最大堆，程序退出，
     * 否则交换根元素与最大元素的位置，继续调用maxHeap原最大元素所在的子树
     * @param arr 数组
     * @param size 数组的长度
     * @param index 最后一个元素的父节点位置
     */
    public static void maxHeap(int[] arr, int size, int index) {
        // 左子节点
        int leftNode = 2 * index + 1;
        // 右子节点
        int rightNode = 2 * index + 2;
        // 和两个子节点分别对比，找出最大的节点
        int max = index;
        if (leftNode < size && arr[max] < arr[leftNode]) {
            max = leftNode;
        }
        if (rightNode < size && arr[max] < arr[rightNode]) {
            max = rightNode;
        }
        // 交换位置
        if (max != index) {
            int temp = arr[max];
            arr[max] = arr[index];
            arr[index] = temp;
            // 交换位置后可能会破坏之前排好的堆，需要重新调整
            maxHeap(arr, size, max);
        }
    }

    /**
     * 堆排序
     * 1.构建最大堆。
     * 2.选择顶，并与第0位置元素交换
     * 3.由于步骤2的的交换可能破环了最大堆的性质，第0不再是最大元素，需要调用maxHeap调整堆(沉降法)，如果需要重复步骤2
     * @param arr
     */
    public static void heapSort(int[] arr){
        int size = arr.length;
        // 开始位置是最后一个非叶子节点，即最后一个节点的父节点
        int start = (size-1)/2;
        // 构建最大堆 [9, 6, 8, 7, 0, 1, 10, 4, 2] --> [10, 7, 9, 6, 0, 1, 8, 4, 2]
        for (int i=start;i>=0;i--){
            maxHeap(arr,size,i);
        }
        // 把数组中的第0个和堆中的最后一个交换，再把前面的处理为大顶堆
        for (int i=size-1;i>0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr,i,0);
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{5, 6, 4, 7, 3, 2, 9, 1};
//        int[] arr = new int[]{1,3,5,7,2,4,6,8};
        int[] arr = new int[]{32, 6, 421, 75, 3, 219};
        System.out.println(Arrays.toString(arr));

//        Sort.bubbleSort(arr);
//        Sort.quickSort1(arr, 0, arr.length - 1);
//        Sort.quickSort2(arr, 0, arr.length - 1);
//        Sort.insertSort(arr);
//        Sort.shellSort(arr);
//        Sort.selectSort(arr);
//        Sort.merge(arr,0,2,arr.length-1);
//        Sort.mergeSort(arr,0,arr.length-1);
//        Sort.radixSort(arr);
        Sort.radixQueueSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[]{9,6,8,7,0,1,10,4,2};
        System.out.println(Arrays.toString(arr1));
        Sort.heapSort(arr1);
        System.out.println(Arrays.toString(arr1));

    }
}
