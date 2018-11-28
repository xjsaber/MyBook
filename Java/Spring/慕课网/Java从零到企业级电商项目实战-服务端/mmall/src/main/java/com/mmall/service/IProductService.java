package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

import java.util.List;

/**
 * @author xjsaber
 */
public interface IProductService {

    /**
     * 保存和修改产品
     * @param product 产品
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 修改商品状态
     * @param productId 产品编号
     * @param status 状态值
     * @return
     */
    ServerResponse<String> setSaleStatus(Integer productId, Integer status) ;



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
    ServerResponse<ProductDetailVo> managerProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

}
