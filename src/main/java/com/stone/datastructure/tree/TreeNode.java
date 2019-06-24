package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/10 09:48
 * description
 */
public class TreeNode {
    // 节点的权
    private int value;
    // 左节点
    private TreeNode leftNode;
    // 右节点
    private TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * 前序遍历
     */
    public void frontShow() {
        System.out.println(value);
        if (leftNode != null) {
            leftNode.frontShow();
        }
        if (rightNode != null) {
            rightNode.frontShow();
        }
    }

    /**
     * 中序遍历
     */
    public void middleShow() {
        if (leftNode != null) {
            leftNode.middleShow();
        }
        System.out.println(value);
        if (rightNode != null) {
            rightNode.middleShow();
        }
    }

    /**
     * 后序遍历
     */
    public void afterShow() {
        if (leftNode != null) {
            leftNode.afterShow();
        }
        if (rightNode != null) {
            rightNode.afterShow();
        }
        System.out.println(value);
    }

    /**
     * 前序查找
     *
     * @param i
     * @return
     */
    public TreeNode frontSearch(int i) {
        TreeNode result = null;
        if (value == i) {
            return this;
        }
        if (leftNode != null) {
            result = leftNode.frontSearch(i);
            if (result != null) {
                return result;
            }
        }
        if (rightNode != null) {
            result = rightNode.frontSearch(i);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /**
     * 删除子树
     * @param i
     */
    public void delete(int i) {
        TreeNode parent = this;
        // 判断左节点
        if (parent.leftNode != null && parent.leftNode.value == i) {
            parent.leftNode = null;
            return;
        }
        // 判断右节点
        if (parent.rightNode != null && parent.rightNode.value == i) {
            parent.rightNode = null;
            return;
        }
        // 递归检查并删除左节点
        parent = leftNode;
        if (parent != null) {
            parent.delete(i);
        }
        // 递归检查并删除右节点
        parent = rightNode;
        if (parent != null) {
            parent.delete(i);
        }
    }
}
