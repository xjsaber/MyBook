package com.xjsaber.learn.datastruct.idea.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayStackTest {

    private final MyArrayQueue queue = new MyArrayQueue();

    @Test
    void init() {
        queue.initQueue(queue);
        Assertions.assertNotNull(queue);
    }
}
