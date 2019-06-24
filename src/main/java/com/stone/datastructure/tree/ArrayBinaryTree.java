package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/10 14:22
 * description
 */
public class ArrayBinaryTree {
    private int[] data;

    public ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    public void frontShow(){
        frontShow(0);
    }

    /**
     * 前序遍历
     * @param index
     */
    public void frontShow(int index) {
        if (data == null || data.length == 0) {
            return;
        }
        // 处理本节点
        System.out.println(data[index]);

        // 处理左子树
        if (2 * index + 1 < data.length) {
            frontShow(2 * index + 1);
        }
        // 处理右子树
        if (2 * index + 2 < data.length) {
            frontShow(2 * index + 2);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.frontShow();
    }
}
