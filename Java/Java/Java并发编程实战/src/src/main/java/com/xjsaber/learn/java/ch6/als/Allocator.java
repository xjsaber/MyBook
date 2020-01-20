package main.java.com.xjsaber.learn.java.ch6.als;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjsaber
 */
public class Allocator {
    private List<Object> als = new ArrayList<>();

    /**
     * 一次性申请所有资源
     * @param from 来源
     * @param to 应用
     * @return 应用
     */
    synchronized boolean apply(Object from, Object to){
        if (als.contains(from) || als.contains(to)){
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    /**
     * 归还资源
     */
    synchronized void free(Object from, Object to){
        als.remove(from);
        als.remove(to);
    }
}
