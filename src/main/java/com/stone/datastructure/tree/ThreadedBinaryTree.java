package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/21 10:58
 * description 中序线索二叉树
 */
public class ThreadedBinaryTree {
    private ThreadedNode root;
    private ThreadedNode pre;

    public ThreadedBinaryTree(ThreadedNode root) {
        this.root = root;
    }

    public ThreadedNode getRoot() {
        return root;
    }

    public void setRoot(ThreadedNode root) {
        this.root = root;
    }

    public ThreadedNode getPre() {
        return pre;
    }

    public void setPre(ThreadedNode pre) {
        this.pre = pre;
    }

    /**
     * 遍历中序线索化二叉树
     * 1.先找到leftType==1的节点，输出该节点
     * 2.再判断节点rightType==1，如果为1，表示该节点的右节点为后继节点，直接输出其右节点，再把右节点赋给当前节点
     * 3.如果节点rightType不为1，则表示该节点无后继节点，直接把右节点赋给当前节点
     */
    public void threadedList(){
        ThreadedNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    public void threadedNode(){
        this.threadedNode(root);
    }

    /**
     * 中序线索化二叉树
     * 1.线索化左子树
     * 2.线索化当前节点
     * 1）设置前驱节点，让当前节点的左指针指向前驱节点
     * 2) 设置后继节点，让前驱节点的右指针指向当前节点
     * 3）处理一个节点后，让当前节点是下一个节点的前驱节点
     * 3.线索化右子树
     *
     * @param node
     */
    public void threadedNode(ThreadedNode node) {
        if (node == null) {
            return;
        }

        threadedNode(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadedNode(node.getRight());
    }

    public static void main(String[] args) {
        ThreadedNode root = new ThreadedNode(1, "tom");
        ThreadedNode node2 = new ThreadedNode(3, "jack");
        ThreadedNode node3 = new ThreadedNode(6, "smith");
        ThreadedNode node4 = new ThreadedNode(8, "mary");
        ThreadedNode node5 = new ThreadedNode(10, "key");
        ThreadedNode node6 = new ThreadedNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNode();
        System.out.println("10号节点的前驱节点" + node5.getLeft());
        System.out.println("10号节点的前驱节点" + node5.getRight());
        threadedBinaryTree.threadedList();
    }
}

class ThreadedNode {
    private int id;
    private String name;
    private ThreadedNode left;
    private ThreadedNode right;
    /**
     * 标识节点左指针类型，为0表示指向左节点，为1表示指向前驱节点
     */
    private int leftType;
    /**
     * 标识节点右指针类型，为0表示指向右节点，为1表示指向后继节点
     */
    private int rightType;

    public ThreadedNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
