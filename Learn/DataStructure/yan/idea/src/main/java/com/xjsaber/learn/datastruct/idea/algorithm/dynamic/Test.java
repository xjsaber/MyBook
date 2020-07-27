package com.xjsaber.learn.datastruct.idea.algorithm.dynamic;

public class Test {

    public static void main(String[] args) {
        int[][]w = new int[4][4];
        w[0][0] = 1;
        w[0][1] = 3;
        w[0][2] = 5;
        w[0][3] = 9;
        w[1][0] = 2;
        w[1][1] = 1;
        w[1][2] = 3;
        w[1][3] = 4;
        w[2][0] = 5;
        w[2][1] = 2;
        w[2][2] = 6;
        w[2][3] = 7;
        w[3][0] = 6;
        w[3][1] = 8;
        w[3][2] = 4;
        w[3][3] = 3;
        minDistBT(0, 0, 0, w, 4);
    }

    private static int minDist = Integer.MAX_VALUE;
    public static void minDistBT(int i, int j, int dist, int[][] w, int n){
        if (i == n && j == n){
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        if (i < n){
            // 往下走，更新i=i+1, j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n){
            // 往右走，更新i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }
}
