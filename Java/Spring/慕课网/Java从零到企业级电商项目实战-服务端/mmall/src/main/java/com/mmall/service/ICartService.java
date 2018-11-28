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
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<List<Cart>> getList(int pageNum, int pageSize);

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
     * 删除
     * @param cartId
     * @return
     */
    ServerResponse<String> delete(Integer cartId);

    /**
     * 搜索
     * @param cartId
     * @return
     */
    ServerResponse<Cart> selectCart(Integer cartId);

}
