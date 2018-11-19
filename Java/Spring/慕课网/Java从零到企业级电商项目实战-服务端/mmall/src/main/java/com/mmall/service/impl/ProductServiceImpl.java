package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xjsaber
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse<List<Product>> list(int categoryId, String keyword, int pageNum, int pageSize, String orderBy) {
        List<Product> list = productMapper.listProduct(categoryId, keyword, pageNum, pageSize, orderBy);
        return null;
    }

    @Override
    public ServerResponse<Product> detail(int productId) {
        return null;
    }
}
