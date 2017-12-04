package com.xjsaber.java.concurrency.ch3;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xjsaber
 */
@Immutable
public final class ThreeStooges {

    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Li");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }
}
