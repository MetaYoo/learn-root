package com.kotall.learn;


import lombok.Data;

/**
 * 树木节点
 */
@Data
public class Node {
    /**
     * 节点状态
     */
    private boolean flag;

    private Node left;
    private Node right;
    private Node up;
    private Node down;

}
