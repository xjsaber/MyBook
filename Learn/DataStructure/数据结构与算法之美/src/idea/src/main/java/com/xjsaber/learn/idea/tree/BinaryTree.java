package com.xjsaber.learn.idea.tree;

public class BinaryTree {

    void print(Node node){
        if (node != null) {
            System.out.println(node.value);
        }
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
