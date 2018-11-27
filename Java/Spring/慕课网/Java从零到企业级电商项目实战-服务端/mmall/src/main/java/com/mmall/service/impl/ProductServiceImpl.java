package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
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
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null){
            if (StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            if (product.getId() != null){
                int row = productMapper.updateByPrimaryKey(product);
                if (row > 0){
                    ServerResponse.createBySuccess("更新产品成功");
                }
                else {
                    ServerResponse.createBySuccess("更新产品失败");
                }
            } else {
                int row = productMapper.insert(product);
                if (row > 0){
                    ServerResponse.createBySuccess("新增产品成功");
                }
                else {
                    ServerResponse.createBySuccess("新增产品失败");
                }
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setId(status);
        int row = productMapper.updateByPrimaryKey(product);
        if (row > 0){
            return ServerResponse.createBySuccessMessage("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    @Override
    public ServerResponse<ProductDetailVo> managerProductDetail(Integer productId) {
        if (productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        // VO对象--value object
        // pojo->bo(business object)->vo(view object)
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setName(product.getName());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setSubTitle(product.getSubtitle());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImage(product.getSubImages());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        // imageHost;
        // parentCategoryId
        // createTime
        // updateTime

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));

        return productDetailVo;
    }

    @Override
    public ServerResponse<List<Product>> getProductList(int pageNum, int pageSize) {
        // startPage -- start
        // 填充自己的SQL查询逻辑
        // pageHelper -- 收尾
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.selectList();
        return ServerResponse.createBySuccess(list);
    }

//    @Override
//    public ServerResponse<List<Product>> list(int categoryId, String keyword, int pageNum, int pageSize, String orderBy) {
//        List<Product> list = productMapper.listProduct(categoryId, keyword, pageNum, pageSize, orderBy);
//        return ServerResponse.createBySuccess(list);
//    }

}
