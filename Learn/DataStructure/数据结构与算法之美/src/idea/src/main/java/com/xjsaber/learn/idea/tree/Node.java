package com.xjsaber.learn.idea.tree;

import lombok.Getter;
import lombok.Setter;

public class Node {

    @Getter
    @Setter
    int value;

    @Getter
    @Setter
    Node left;

    @Getter
    @Setter
    Node right;
}
