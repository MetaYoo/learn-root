package com.kotall.learn.bst;

import org.junit.Before;
import org.junit.Test;

/**
 * 二叉搜索树测试
 * @author zpwang
 * @version 1.0.0
 */
public class BSTTest {
//                9
//       5                15
//   3      6        12          26
// 1   4         11      13  20       30
//

    private BST root = null;

    @Before
    public void startUp() {
        root = new BST();
        root.setParent(null);
        root.setData(9);

        BST p;
        p = root.insert(5);
        System.out.println("插入：" + 5 + "父节点是：" + p.getParent().getData());
        p = root.insert(15);
        System.out.println("插入：" + 15 + "父节点是：" + p.getParent().getData());
        p = root.insert(3);
        System.out.println("插入：" + 3 + "父节点是：" + p.getParent().getData());
        p = root.insert(6);
        System.out.println("插入：" + 6 + "父节点是：" + p.getParent().getData());
        p = root.insert(12);
        System.out.println("插入：" + 12 + "父节点是：" + p.getParent().getData());
        p = root.insert(26);
        System.out.println("插入：" + 26 + "父节点是：" + p.getParent().getData());
        p = root.insert(1);
        System.out.println("插入：" + 1 + "父节点是：" + p.getParent().getData());
        p = root.insert(4);
        System.out.println("插入：" + 4 + "父节点是：" + p.getParent().getData());
        p = root.insert(11);
        System.out.println("插入：" + 11 + "父节点是：" + p.getParent().getData());
        p = root.insert(13);
        System.out.println("插入：" + 13 + "父节点是：" + p.getParent().getData());
        p = root.insert(20);
        System.out.println("插入：" + 20 + "父节点是：" + p.getParent().getData());
        p = root.insert(30);
        System.out.println("插入：" + 30 + "父节点是：" + p.getParent().getData());
    }


    @Test
    public void find() {
        System.out.println(root.find(12).getLeftChild().getData());

    }

    @Test
    public void delete() {
        BST rt = root.delete(9);
        System.out.println(rt.getRightChild().getData());
    }

}
