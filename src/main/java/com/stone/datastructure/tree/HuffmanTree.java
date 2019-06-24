package com.stone.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author stone
 * @date 2019/6/24 11:55
 * description 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = new int[]{13, 7, 8, 3, 29, 6, 1};
        HuffmanNode huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr
     * @return
     */
    public static HuffmanNode createHuffmanTree(int[] arr) {
        List<HuffmanNode> nodes = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            nodes.add(new HuffmanNode(arr[i]));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parentNode = new HuffmanNode(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }

        return nodes.get(0);
    }

    public static void preOrder(HuffmanNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("不能遍历空树");
        }
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    private int value;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
