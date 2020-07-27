package com.xjsaber.learn.datastruct.idea.search.trie;

public class TrieNode {

    char data;
    TrieNode[] children;
    boolean isEndingChar = false;

    public TrieNode(char v){
        data = v;
    }
}
