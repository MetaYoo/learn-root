package com.kotall.learn.bst;

import lombok.Data;

import java.util.List;

/**
 * 二叉搜索树
 *
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class BST {

    private BST parent;

    private int data;

    private BST leftChild;

    private BST rightChild;

    /**
     * 二叉搜索树-查找
     *
     * @param data
     * @return
     */
    public BST find(int data) {
        if (this.data == data) {
            return this;
        }
        if (this.data < data) {
            if (null != this.rightChild) {
                return this.rightChild.find(data);
            }
            return null;
        }
        if (this.data > data) {
            if (null != this.leftChild) {
                return this.leftChild.find(data);
            }
            return null;
        }
        return null;
    }

    /**
     * 二叉搜索树-插入
     *
     * @param data
     */
    public BST insert(int data) {
        if (this.data == data) {
            throw new RuntimeException("node already exists!");
        }

        if (this.data < data) {
            if (null != this.getRightChild()) {
                return this.rightChild.insert(data);
            }
            BST node = new BST();
            node.setData(data);
            node.setParent(this);
            this.setRightChild(node);
            return node;
        }

        if (this.data > data) {
            if (null != this.getLeftChild()) {
                return this.leftChild.insert(data);
            }
            BST node = new BST();
            node.setData(data);
            node.setParent(this);
            this.setLeftChild(node);
            return node;
        }
        return null;
    }


    /**
     * 二叉搜索树-删除
     *
     * @param data
     */
    public BST delete(int data) {
        BST node = this.find(data);
        if (this == node) {
            // 找出左子树的最大子节点或者右子树的最小子节点替换
            if (null != node.getLeftChild()) {
                BST _node = this.getRightLargestNode(node.getLeftChild());
                this.setData(_node.getData());
                _node.getParent().setRightChild(null);
                return this;
            } else if (null != node.getRightChild()) {
                BST _node = this.getLeftLowestNode(node.getRightChild());
                this.setData(_node.getData());
                _node.getParent().setLeftChild(null);
                return this;
            } else {
                node = null;
                return this;
            }
        }

        if (this.data < data) {
            if (null != this.getRightChild()) {
                return this.getRightChild().delete(data);
            }
        }

        if (this.data > data) {
            if (null != this.getLeftChild()) {
                return this.getLeftChild().delete(data);
            }
        }

        return null;
    }

    /**
     *  查找最大左子树节点
     * @param node
     * @return
     */
    public BST getRightLargestNode(BST node) {
      if (null == node.getRightChild()) {
          return node;
      } else {
          return getRightLargestNode(node.getRightChild());
      }
    }

    /**
     * 查找最小右子树节点
     * @param node
     * @return
     */
    public BST getLeftLowestNode(BST node) {
        if (null == node.getLeftChild()) {
            return node;
        } else {
            return getLeftLowestNode(node.getLeftChild());
        }
    }

    public void toArray(BST node, List<Integer> list) {
    }

}
