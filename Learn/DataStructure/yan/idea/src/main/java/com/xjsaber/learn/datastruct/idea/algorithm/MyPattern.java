package com.xjsaber.learn.datastruct.idea.algorithm;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

public class MyPattern {

    private boolean matched = false;
    // 正则表达式
    private char[] pattern;
    // 正则表达式长度
    private int plen;

    public MyPattern(char[] pattern, int plen){
        this.pattern = pattern;
        this.plen = plen;
    }

//    public boolean match(char[] text, int tlen){
//        matched = false;
//
//    }

    private void rmatch(int ti, int pj, char[] text, int tlen){
        // 如果已经匹配了，就不要继续递归了
        if (matched) return;
        // 正则表达式到结尾了
        if (pj == plen){
            // 文本串也到结尾了
            if (ti == tlen) matched = true;
            return;
        }
        if (pattern[pj] == '*'){
            for (int k = 0; k < tlen - ti; ++k) {

            }
        }
    }
}
