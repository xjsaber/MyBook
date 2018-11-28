package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Cart;
import com.mmall.vo.CartVo;

import java.util.List;

/**
 * @author xjsaber
 */
public interface ICartService {

    /**
     * 列表
     * @param userId 用户编号
     * @return
     */
    ServerResponse<CartVo> getList(Integer userId);

    /**
     * 新增或者修改
     * @param cart
     * @return
     */
    ServerResponse<String> addOrUpdate(Cart cart);

    /**
     * 新增
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    /**
     * 修改
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    /**
     * 删除
     * @param userId
     * @param productIds product编号，用“，”相连
     * @return
     */
    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    /**
     * 搜索
     * @param cartId
     * @return
     */
    ServerResponse<Cart> selectCart(Integer cartId);

}
