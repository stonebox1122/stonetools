package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/25 17:12
 * description 平衡二叉树
 */
public class AVLTree {
    private AVLNode root;

    public AVLTree() {
    }

    public AVLTree(AVLNode root) {
        this.root = root;
    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    public void add(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void midOrder() {
        if (root != null) {
            root.midOrder();
        } else {
            System.out.println("二叉平衡树为空");
        }
    }

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AVLNode(arr[i]));
        }
        avlTree.midOrder();

        System.out.println("旋转处理前树的高度");
        System.out.println("树的高度" + avlTree.getRoot().height());
        System.out.println("左子树的高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度" + avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot().getLeft()+","+avlTree.getRoot());
    }
}

class AVLNode {
    private int value;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(AVLNode node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 当添加完一个节点后，如果rightHeight() - leftHeight() > 1 时需要进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树高度大于它的右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先进行右子树的右旋转，再进行当前节点左旋转
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        // 当添加完一个节点后，如果leftHeight()  - rightHeight()  > 1 时需要进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 先进行左子树左旋转，再进行当前节点右旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 查找指定节点
     *
     * @param value
     * @return
     */
    public AVLNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找节点的父节点
     *
     * @param value
     * @return
     */
    public AVLNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 当前节点为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左子树高度
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 右子树高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 左旋转，rightHeight() - leftHeight() > 1 时需要进行左旋转
     */
    public void leftRotate() {
        //创建一个新的节点，值等于当前根节点的值
        AVLNode newNode = new AVLNode(value);
        //把新节点的左子树设置了当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换为右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newNode;
    }

    /**
     * 右旋转，leftHeight()  - rightHeight()  > 1 时需要进行右旋转
     */
    public void rightRotate() {
        AVLNode newNode = new AVLNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
