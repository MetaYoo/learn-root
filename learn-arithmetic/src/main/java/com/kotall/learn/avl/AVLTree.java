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

    public AVLTree insert(int node) {
        AVLTree t;
        if (this.data == node) {
            t = this;
        }
        // 插入左子树
        else if (node < this.data) {
            if (this.left == null) {
                t = this.left = new AVLTree(node, null, null, 1);
            } else {
                t = this.left.insert(node);
                // 旋转
            }
        }
        // 插入右子树
        else {
            if (this.right == null) {
                t = this.right = new AVLTree(node, null, null, 1);
            } else {
                t = this.right.insert(node);
            }
        }
        // 调整数的高度
        t.height = this.max(this.getHeight(t.left), this.getHeight(t.right)) + 1;
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

}
