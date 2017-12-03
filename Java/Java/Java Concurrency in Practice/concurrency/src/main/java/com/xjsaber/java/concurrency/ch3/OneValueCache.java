package com.xjsaber.java.concurrency.ch3;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author xjsaber
 */
public class OneValueCache {

    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors){
        lastNumber = i;
        lastFactors = factors;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !i.equals(lastNumber)){
            return null;
        }
        else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
        return lastNumber;
    }
}
