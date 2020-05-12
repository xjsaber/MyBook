package com.xjsaber.learn.idea.algorithm;

/**
 * 动态规划
 */
public class DynamicProgramming {

    /**
     * 01背包问题
     * @param weight 重量
     * @param n
     * @param w
     * @return
     */
    public int knapsack(int[] weight, int n, int w){
        // 默认值false
        boolean[][] states = new boolean[n][w+1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
    }
}
