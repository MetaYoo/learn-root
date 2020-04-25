package com.kotall.learn.java8.consisthash;

import java.util.SortedMap;
import java.util.TreeMap;

public class NodeArray {
//    Node[] nodes = new Node[1024];
//    int size = 0;
//
//    public void addNode(Node node) {
//        nodes[size++] = node;
//    }
//
//    Obj get(Obj obj) {
//        int index = obj.hashCode() % size;
//        return nodes[index].getObj(obj);
//    }
//
//    void put(Obj obj) {
//        int index = obj.hashCode() % size;
//        nodes[index].putObj(obj);
//    }


    /**
     * 按照键值排序
     */
    TreeMap<Integer, Node> nodes = new TreeMap<>();

    public void addNode(Node node) {
        nodes.put(node.hashCode(), node);
    }


    public void put(Obj obj) {
        int objHashCode = obj.hashCode();
        Node node = nodes.get(objHashCode);
        if (node != null) {
            node.putObj(obj);
            return;
        }
        // 找到比给定 key 大的集合
        SortedMap<Integer, Node> tailMap = nodes.tailMap(objHashCode);
        // 找到最小的节点
        int nodeHashCode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        nodes.get(nodeHashCode).putObj(obj);
    }


    public Obj get(Obj obj) {
        Node node = nodes.get(obj.hashCode());
        if (node != null) {
            return node.getObj(obj);
        }

        // 找到比给定key大的集合
        SortedMap<Integer, Node> tailMap = nodes.tailMap(obj.hashCode());
        // 找到最小的节点
        int nodeHashCode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        return nodes.get(nodeHashCode).getObj(obj);
    }

}
