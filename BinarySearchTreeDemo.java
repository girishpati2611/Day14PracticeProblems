package com.assignment;

class MyBinaryNode<K extends Comparable<K>> {
    K key;
    MyBinaryNode<K> left;
    MyBinaryNode<K> right;

    public MyBinaryNode(K key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}

class MyBinarySearchTree<K extends Comparable<K>> {
    private MyBinaryNode<K> root;

    public MyBinarySearchTree() {
        this.root = null;
    }

    public void add(K key) {
        this.root = addRecursive(root, key);
    }

    private MyBinaryNode<K> addRecursive(MyBinaryNode<K> currentNode, K key) {
        if (currentNode == null) {
            return new MyBinaryNode<>(key);
        }

        if (key.compareTo(currentNode.key) < 0) {
            currentNode.left = addRecursive(currentNode.left, key);
        } else if (key.compareTo(currentNode.key) > 0) {
            currentNode.right = addRecursive(currentNode.right, key);
        }

        return currentNode;
    }

    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(MyBinaryNode<K> currentNode) {
        if (currentNode == null) {
            return 0;
        }

        return 1 + calculateSize(currentNode.left) + calculateSize(currentNode.right);
    }

    public boolean search(K key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(MyBinaryNode<K> currentNode, K key) {
        if (currentNode == null) {
            return false;
        }

        if (key.equals(currentNode.key)) {
            return true;
        }

        if (key.compareTo(currentNode.key) < 0) {
            return searchRecursive(currentNode.left, key);
        } else {
            return searchRecursive(currentNode.right, key);
        }
    }
}

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

        // UC 1: Add nodes 56, 30, and 70
        bst.add(56);
        bst.add(30);
        bst.add(70);

        // UC 2: Check size
        System.out.println("Size of BST: " + bst.size());

        // UC 3: Search for key 63
        boolean found = bst.search(63);
        System.out.println("Key 63 found in BST: " + found);
    }
}

