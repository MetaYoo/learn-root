package com.kotall.learn.java8.consisthash;

public class ConsistHashTest {
    public static void main(String[] args) {
        NodeArray nodeArray = new NodeArray();

        Node[] nodes = {
                new Node("Node1"),
                new Node("Node2"),
                new Node("Node3")
        };

        for (Node node : nodes) {
            nodeArray.addNode(node);
        }

        Obj[] objs = {
                new Obj("1"),
                new Obj("2"),
                new Obj("3"),
                new Obj("4"),
                new Obj("5")
        };

        for (Obj obj : objs) {
            nodeArray.put(obj);
        }

        validate(nodeArray, objs);

    }


    private static void validate(NodeArray nodeArray, Obj[] objs) {
        for (Obj obj : objs) {
            System.out.println(nodeArray.get(obj));
        }

        // 新增了 node节点，导致之前的数据找不到节点了
        nodeArray.addNode(new Node("anything1"));
        nodeArray.addNode(new Node("anything2"));

        System.out.println("========== after  =============");

        for (Obj obj : objs) {
            System.out.println(nodeArray.get(obj));
        }
    }
}
