package com.xjsaber.learn.datastruct.idea;

import com.xjsaber.learn.datastruct.idea.algorithm.Backtracking;

import java.util.Stack;

class MinStack {

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        backtracking.cal8queens(0);
    }

    Stack<Integer> stack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        int i = 1;
        int min = stack.elementAt(0);
        while (i < stack.size()){
            int temp = stack.elementAt(i);
            if (min > temp){
                min = temp;
            }
            i++;
        }
        return min;
    }
}