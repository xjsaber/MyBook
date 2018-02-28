package com.xjsaber.java.thread.code.ch5.fork;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjsaber
 */
public class ProductListGenerator {

    public List<Product> generate(int size){
        List<Product> ret = new ArrayList<>();
        for (int i = 0; i < size; i++){
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}
