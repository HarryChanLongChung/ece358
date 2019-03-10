package tests;

import lab02.*;

public class testsForNode {

    public static void main(String[] args) {
        node testNode = new node(10.0, 5, 1000);
        testNode.print();
        testNode.handleCollision();
        testNode.print();
        testNode.verify();
    }
}