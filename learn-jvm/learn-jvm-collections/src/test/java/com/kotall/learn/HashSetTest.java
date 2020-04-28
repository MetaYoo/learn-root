package com.kotall.learn;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetTest {
    static class Node {
        private int id;
        private String name;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id &&
                    Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }


    public static void main(String[] args) {
        Set<Node> hashSet = new HashSet<>();
        Node node = new Node();
        hashSet.add(node);
        node.setId(123);
        System.out.println(hashSet.size());
        hashSet.remove(node);
        System.out.println(hashSet.size());
    }
}
