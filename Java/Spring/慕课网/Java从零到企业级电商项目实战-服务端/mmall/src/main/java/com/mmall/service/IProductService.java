package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

import java.util.List;

/**
 * @author xjsaber
 */
public interface IProductService {

    /**
     *
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 商品列表
     * @param categoryId
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    ServerResponse<List<Product>> list(int categoryId, String keyword, int pageNum, int pageSize, String orderBy);

    /**
     * 商品详情
     * @param productId 商品编号
     * @return product
     */
    ServerResponse<Product> detail(int productId);
}
