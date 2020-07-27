package com.xjsaber.learn.datastruct.idea.search.trie;

import javax.swing.tree.TreeNode;

public class TrieTree {

    private TrieNode root = new TrieNode('/');

    // 往Trie树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (char c : text) {
            int index = c - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(c);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    // 在Trie树中查找一个字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        if (!p.isEndingChar) return false;
        else return true; // 找到pattern
    }
}
