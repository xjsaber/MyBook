package com.xjsaber.learn.idea.algorithm;

/**
 * 动态规划
 */
public class Dynamic {

    /**
     *
     * 使用回溯算法实现0-1背包
     * 结果放到maxW中
     */
    private int maxW = Integer.MIN_VALUE;
    /**
     * 物品重量
     */
    private int[] weight = {2, 2, 4, 6, 3};
    /**
     * 物品个数
     */
    private int n = 5;
    /**
     * 背包承受的最大重量
     */
    private int w = 9;
    public void f(int i, int cw){
        if (cw == w || i == n){
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 选择不装第i个物品
        f (i+1, cw);
        if (cw + weight[i] <= w){
            // 选择装第i个物品
            f(i+1, cw+weight[i]);
        }
    }

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
        return 0;
    }





    /**
     *
     * @param items 商品数量
     * @param n 商品个数
     * @param w 满减条件，比如200
     */
    public static void double11advance(int[] items, int n, int w){

        boolean[][] states = new boolean[n][3*w+1];
        // 第一行的数据要特殊处理
        states[0][0] = true;
    }
}
