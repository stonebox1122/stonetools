package com.stone.datastructure.array;

/**
 * @author stone
 * @date 2019/6/11 11:35
 * description
 */
public class SparseArray {

    /**
     * 将二维数组转换为稀疏数组
     * @param arr
     */
    public static int[][] arrayToSparseArray(int[][] arr){
        // 1.获取数组中不为0的元素的个数
        int arrHeight = arr.length;
        int arrLength = arr[0].length;
        int sum = 0;
        for (int i = 0; i < arrHeight; i++) {
            for (int j = 0; j < arrLength; j++) {
                if (arr[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2.创建稀疏数组，列数为3，行数为sum+1，第一行存储原数组长度，后面行存储原数组不为0值的位置以及值
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = arrHeight;
        sparseArr[0][1] = arrLength;
        sparseArr[0][2] = sum;

        // 3.将原数组中不为0的值存储到稀疏数组中
        int count = 1;
        for (int i = 0; i < arrHeight; i++) {
            for (int j = 0; j < arrLength; j++) {
                if (arr[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                    count++;
                }
            }
        }
        return sparseArr;
    }

    public static int[][] sparseArrayToArray(int[][] sparseArr){
        // 1.从稀疏数组中获取第一行的值来创建数组
        int arrHeight = sparseArr[0][0];
        int arrLength = sparseArr[0][1];
        int[][] arr = new int[arrHeight][arrLength];

        // 2.从稀疏数组中获取值赋予给新创建的数组
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

    public static void main(String[] args) {
        // 创建一个稀疏二维数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        for (int[] row : chessArr1) {
            for (int i : row) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }

        int[][] parseArray = SparseArray.arrayToSparseArray(chessArr1);
        for (int[] row : parseArray) {
            for (int i : row) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }

        int[][] sparseArrayToArray = SparseArray.sparseArrayToArray(parseArray);
        for (int[] row : sparseArrayToArray) {
            for (int i : row) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
    }
}
