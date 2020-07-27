package com.xjsaber.learn.datastruct.idea.algorithm;

public class Backtracking {

    /**
     * 八王后问题
     */
    // 全局或成员变量，下标表示行，值表示queen存储在哪一列
    int[] result = new int[8];
    public void cal8queens(int row){
        if (row == 8){ // 8个棋子都放置好了，打印结果
            printQueens(result);
            return;
        }
        for (int column = 0; column < 8; ++column){
            if (isOk(row, column)){
                result[row] = column; // 第row行的棋子放到了column列
                cal8queens(row + 1); // 考察下一行
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     * @param row 当前行
     * @param column 当前列
     * @return boolean
     */
    private boolean isOk(int row, int column){
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column) return false;
            // 考察左上对角线
            if (leftup >= 0) {
                if (result[i] == leftup) return false;
            }
            // 考察右上对角线
            if (rightup < 8) {
                if (result[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result){
        for (int row = 0; row < 8; row++){
            for (int column = 0; column < 8; column++){
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 0-1背包问题
     */
    public int maxW = Integer.MIN_VALUE;
    public void f(int i, int cw, int[] items, int n, int w){
        if (cw == w || i == n) {
            if (cw > maxW) maxW =cw;
            return;
        }
        f (i + 1, cw, items, n, w);
        if (cw + items[i] <= w){
            f(i + 1, cw + items[i], items, n, w);
        }
    }
}
