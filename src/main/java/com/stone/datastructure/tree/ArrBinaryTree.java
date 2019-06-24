package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/21 10:14
 * description 顺序存储二叉树
 */
public class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void midOrder(){
        midOrder(0);
    }

    public void postOrder(){
        postOrder(0);
    }

    /**
     * 前序遍历
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }

        System.out.print(arr[index]);

        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     * @param index
     */
    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }

        if (2 * index + 1 < arr.length) {
            midOrder(2 * index + 1);
        }

        System.out.print(arr[index]);

        if (2 * index + 2 < arr.length) {
            midOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }

        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }

        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }

        System.out.print(arr[index]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.midOrder();
        System.out.println();
        arrBinaryTree.postOrder();
    }
}
