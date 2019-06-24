package com.stone.datastructure.tree;

/**
 * @author stone
 * @date 2019/6/20 14:48
 * description
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "a");
        HeroNode node2 = new HeroNode(2, "al");
        HeroNode node3 = new HeroNode(3, "ar");
        HeroNode node4 = new HeroNode(4, "arr");
        HeroNode node5 = new HeroNode(5, "arl");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        HeroBinaryTree binaryTree = new HeroBinaryTree(root);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.midOrder();
        System.out.println("后续遍历");
        binaryTree.postOrder();
        System.out.println("前序查找开始：");
        System.out.println(binaryTree.preOrderSearch(5));
        System.out.println("中序查找开始：");
        System.out.println(binaryTree.midOrderSearch(5));
        System.out.println("后续查找开始：");
        System.out.println(binaryTree.postOrderSearch(5));
        binaryTree.delete(3);
        binaryTree.preOrder();
    }
}

class HeroBinaryTree {
    private HeroNode root;

    public HeroBinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int id) {
        if (root != null) {
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    public HeroNode midOrderSearch(int id) {
        if (root != null) {
            return root.midOrderSearch(id);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int id) {
        if (root != null) {
            return root.postOrderSearch(id);
        } else {
            return null;
        }
    }

    public void delete(int id) {
        if (root != null) {
            if (root.getId() == id) {
                root = null;
                System.out.println("删除id为" + id + "的节点");
            } else {
                root.deleteNode(id);
            }
        } else {
            System.out.println("二叉树为空");
        }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
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
     * 后续遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * @param id
     * @return
     */
    public HeroNode preOrderSearch(int id) {
        System.out.println("前序查找");
        if (this.id == id) {
            return this;
        }

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public HeroNode midOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("中序查找");
        if (this.id == id) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.midOrderSearch(id);
        }
        return resNode;
    }

    /**
     * 后续查找
     *
     * @param id
     * @return
     */
    public HeroNode postOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("后续查找");
        if (this.id == id) {
            return this;
        }
        return resNode;
    }

    /**
     * 删除节点
     *
     * @param id
     */
    public void deleteNode(int id) {
        if (this.left != null && this.left.id == id) {
            this.left = null;
            System.out.println("删除id为" + id + "的节点");
            return;
        }
        if (this.right != null && this.right.id == id) {
            this.right = null;
            System.out.println("删除id为" + id + "的节点");
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(id);
        }
        if (this.right != null) {
            this.right.deleteNode(id);
        }
    }
}
