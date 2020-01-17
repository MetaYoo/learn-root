package com.kotall.learn.avl;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * desc:
 * 平衡二叉树
 *
 * @author zpwang
 * @date 2020/1/16 14:39
 * @since 1.0.0
 */
@AllArgsConstructor
@Data
public class AVLTree {
    private int data;
    private AVLTree left;
    private AVLTree right;
    private int height;

    public AVLTree(int data) {
        this.data = data;
        this.height = 1;
    }

    public AVLTree insert(int node) {
        AVLTree t;
        if (this.data == node) {
            t = this;
        }
        // 插入左子树
        else if (node < this.data) {
            if (this.left == null) {
                t = this.left = new AVLTree(node);
            } else {
                t = this.left.insert(node);
                // 旋转
                if (this.getHeight(this.left) - this.getHeight(this.right) == 2) {
                    if (node < this.left.data) {
                        // R 旋转
                        t = this.singleRightRotation(t);
                    } else {
                        // LR 旋转
                        t = this.leftRightRotation(t);
                    }
                }
            }
        }
        // 插入右子树
        else {
            if (this.right == null) {
                t = this.right = new AVLTree(node);
            } else {
                t = this.right.insert(node);
                // 旋转
                if (this.getHeight(this.left) - this.getHeight(this.right) == -2) {
                    if (node > this.right.data) {
                        t = this.singleLeftRotation(t);
                    } else {
                        t = this.rightLeftRotation(t);
                    }
                }
            }
        }
        // 为新建的节点调整数的高度
        //t.height = this.max(this.getHeight(t.left), this.getHeight(t.right)) + 1;
        this.height = this.max(this.getHeight(this.left), this.getHeight(this.right)) + 1;
        return t;
    }

    public int getHeight(AVLTree node) {
        if (null == node) {
            return 0;
        }
        return node.height;
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public AVLTree singleRightRotation(AVLTree node) {
        AVLTree newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        node.height = this.max(this.getHeight(node.getLeft()), this.getHeight(node.getRight())) + 1;
        newRoot.height = this.max(this.getHeight(newRoot.getLeft()), this.getHeight(newRoot.getRight()));
        return newRoot;
    }

    public AVLTree singleLeftRotation(AVLTree node) {
        AVLTree newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        node.height = this.max(this.getHeight(node.getLeft()), this.getHeight(node.getRight())) + 1;
        newRoot.height = this.max(this.getHeight(newRoot.getLeft()), this.getHeight(newRoot.getRight()));
        return newRoot;
    }

    public AVLTree leftRightRotation(AVLTree node) {
        AVLTree t = this.singleLeftRotation(node.left);
        return this.singleRightRotation(t);
    }

    public AVLTree rightLeftRotation(AVLTree node) {
        AVLTree t = this.singleRightRotation(node.right);
        return this.singleLeftRotation(t);
    }

    public static void main(String[] args) {
        AVLTree root = new AVLTree(10);
        root.insert(5);
        root.insert(30);
        root.insert(7);
        root.insert(6);

        System.out.println(root.left.right.left.data);

    }
}
