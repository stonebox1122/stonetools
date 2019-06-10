package com.stone.datastructure;

/**
 * @author stone
 * @date 2019/6/10 09:47
 * description
 */
public class BinaryTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void frontShow() {
        if (root != null) {
            root.frontShow();
        }
    }

    private void middleShow() {
        if (root != null) {
            root.middleShow();
        }
    }

    private void afterShow() {
        if (root != null) {
            root.afterShow();
        }
    }

    private TreeNode frontSearch(int i) {
        return root.frontSearch(i);
    }

    private void delete(int i) {
        if (root.getValue() == i) {
            root = null;
        } else {
            root.delete(i);
        }
    }

    public static void main(String[] args) {
        // 创建树
        BinaryTree binaryTree = new BinaryTree();
        // 创建根节点
        TreeNode root = new TreeNode(1);
        // 将根节点赋给树
        binaryTree.setRoot(root);
        // 创建左节点
        TreeNode rootLeftNode = new TreeNode(2);
        // 将新创建的节点设置为根节点的左节点
        root.setLeftNode(rootLeftNode);
        // 创建右节点
        TreeNode rootRightNode = new TreeNode(3);
        // 将新创建的节点设置为根节点的右节点
        root.setRightNode(rootRightNode);

        // 为第二层的左节点创建两个子节点
        rootLeftNode.setLeftNode(new TreeNode(4));
        rootLeftNode.setRightNode(new TreeNode(5));
        // 为第二层的右节点创建两个子节点
        rootRightNode.setLeftNode(new TreeNode(6));
        rootRightNode.setRightNode(new TreeNode(7));

        // 前序遍历
        binaryTree.frontShow();
        System.out.println("======================");

        // 中序遍历
        binaryTree.middleShow();
        System.out.println("======================");

        // 后序遍历
        binaryTree.afterShow();
        System.out.println("======================");

        // 前序查找
        TreeNode result = binaryTree.frontSearch(3);
        System.out.println(result == rootRightNode);
        System.out.println("======================");

        // 删除节点
        binaryTree.delete(7);
        binaryTree.frontShow();
        System.out.println("======================");

    }
}
