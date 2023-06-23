package com.assignment;

import java.util.*;

class MyMapNode {
    String key;
    int value;
    MyMapNode next;

    public MyMapNode(String key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class MyHashTable {
    private int capacity;
    private LinkedList<MyMapNode>[] bucketArray;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.bucketArray = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            bucketArray[i] = new LinkedList<>();
        }
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void insert(String key) {
        int index = getHash(key);
        LinkedList<MyMapNode> bucket = bucketArray[index];
        for (MyMapNode node : bucket) {
            if (node.key.equals(key)) {
                node.value++;
                return;
            }
        }
        bucket.addLast(new MyMapNode(key, 1));
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            LinkedList<MyMapNode> bucket = bucketArray[i];
            for (MyMapNode node : bucket) {
                System.out.println("Word: " + node.key + ", Frequency: " + node.value);
            }
        }
    }
    public void remove(String key) {
        int index = getHash(key);
        LinkedList<MyMapNode> bucket = bucketArray[index];
        Iterator<MyMapNode> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            MyMapNode node = iterator.next();
            if (node.key.equals(key)) {
                iterator.remove();
                return;
            }
        }
    }
}

public class WordFrequency {
    public static void findWordFrequency(String sentence) {
        String[] words = sentence.split("\\s+");
        int capacity = words.length;
        MyHashTable hashTable = new MyHashTable(capacity);

        for (String word : words) {
            hashTable.insert(word.toLowerCase());
        }
        System.out.println("--------------------------------------------");
        System.out.println("Hash table before removing 'avoidable' word");
        System.out.println("--------------------------------------------");
        hashTable.display();
        // Remove the word "avoidable"
        hashTable.remove("avoidable");
        System.out.println("--------------------------------------------");
        System.out.println("Hash table after removing 'avoidable' word");
        System.out.println("--------------------------------------------");
        hashTable.display();
    }

    public static void main(String[] args) {
//        String sentence = "To be or not to be";
          String sentence = "Paranoids are not paranoid because they are paranoid because they keep putting themselves deliberately into paranoid avoidable situations";
        findWordFrequency(sentence);
    }
}
