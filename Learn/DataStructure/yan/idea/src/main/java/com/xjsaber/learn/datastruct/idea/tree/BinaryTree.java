package com.xjsaber.learn.datastruct.idea.tree;

public class BinaryTree {

    void print(Node node){
        if (node != null) {
            System.out.println(node.value);
        }
    }

    Node createNode(int value){
        Node node = new Node();
        node.setValue(value);
        node.setLeft(null);
        node.setRight(null);
        return  node;
    }

    /*
    前序遍历
     */
    void preOrder(Node root) {
        if (root == null) return;
        print(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 查找节点
     * @param root 根节点
     * @param x 查找的值
     * @return 返回的node
     */
    Node FindNode(Node root, int x){
        return null;
    }

    /*
    中序遍历
     */
    void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        print(root);
        inOrder(root.right);
    }

    /*
    后序遍历
    */
    void postOrder(Node root) {
        if (root == null) return;
        postOrder(root);
        postOrder(root);
        print(root);
    }
}
