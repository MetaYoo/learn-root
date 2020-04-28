package com.kotall.learn.node;

public class Node {
    private Object data;
    private Node pre;
    private Node next;

    public Node(Object data) {
        this.data = data;
        this.pre = null;
        this.next = null;
    }
}
