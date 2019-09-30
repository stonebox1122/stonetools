package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/25 14:29
 * description 二叉排序树
 */
public class BinarySortTree {
    private BinarySortNode root;

    public BinarySortTree() {
    }

    public BinarySortTree(BinarySortNode root) {
        this.root = root;
    }

    public BinarySortNode getRoot() {
        return root;
    }

    public void setRoot(BinarySortNode root) {
        this.root = root;
    }

    public void add(BinarySortNode node) {
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
            System.out.println("二叉排序树为空");
        }
    }

    public BinarySortNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public BinarySortNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回node为根节点的二叉排序树的最小节点的值
     * 删除node为根节点的二叉排序树的最小节点
     *
     * @param node 传入的节点，作为二叉排序树的根节点
     * @return
     */
    public int deleteMinNode(BinarySortNode node) {
        BinarySortNode targert = node;
        // 移动到最左节点（也就是最小节点）
        while (targert.getLeft() != null) {
            targert = targert.getLeft();
        }
        // 删除最小节点
        deleteNode(targert.getValue());
        return targert.getValue();
    }

    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            BinarySortNode targetNode = search(value);
            if (targetNode == null) {
                return;
            }

            // 当前二叉排序树只有一个节点
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }

            // 找到要删除节点的父节点
            BinarySortNode parentNode = searchParent(value);

            // 如果要删除节点是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(null);
                } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                    parentNode.setRight(null);
                }
            } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                int minValue = deleteMinNode(targetNode.getRight());
                targetNode.setValue(minValue);
            } else {
                if (targetNode.getLeft() != null) {
                    if (parentNode != null) {
                        if (parentNode.getLeft().getValue() == value) {
                            parentNode.setLeft(targetNode.getLeft());
                        } else if (parentNode.getRight().getValue() == value) {
                            parentNode.setRight(targetNode.getLeft());
                        }
                    } else {
                        root = targetNode.getLeft();
                    }
                }

                if (targetNode.getRight() != null) {
                    if (parentNode != null) {
                        if (parentNode.getLeft().getValue() == value) {
                            parentNode.setLeft(targetNode.getRight());
                        } else if (parentNode.getRight().getValue() == value) {
                            parentNode.setRight(targetNode.getRight());
                        }
                    } else {
                        root = targetNode.getRight();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new BinarySortNode(arr[i]));
        }
        binarySortTree.midOrder();
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.midOrder();
        System.out.println(binarySortTree.getRoot());
    }
}

class BinarySortNode {
    private int value;
    private BinarySortNode left;
    private BinarySortNode right;

    public BinarySortNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortNode left) {
        this.left = left;
    }

    public BinarySortNode getRight() {
        return right;
    }

    public void setRight(BinarySortNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinarySortNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(BinarySortNode node) {
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
    public BinarySortNode search(int value) {
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
    public BinarySortNode searchParent(int value) {
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
}
