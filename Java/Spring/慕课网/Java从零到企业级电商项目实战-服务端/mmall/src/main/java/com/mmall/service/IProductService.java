package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

import java.util.List;

/**
 * @author xjsaber
 */
public interface IProductService {

    /**
     * 商品列表
     * @return list
     */
    ServerResponse<List<Product>> list(int categoryId, String keyword, int pageNum, int pageSize, String orderBy);

    /**
     * 商品详情
     * @param productId 商品编号
     * @return product
     */
    ServerResponse<Product> detail(int productId);
}
