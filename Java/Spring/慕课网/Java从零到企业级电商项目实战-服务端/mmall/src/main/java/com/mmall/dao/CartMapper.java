package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId")Integer userId,@Param("productId")Integer productId);

    List<Cart> selectCartByUserId(Integer userId);

    List<Cart> selectList();

    int selectCartProductCheckedStatusByUserId(Integer userId);

    /**
     *
     * @param userId 用户编号
     * @param productList 产品列表
     * @return
     */
    int deleteByUserIdProductIds(@Param("userId") Integer userId, @Param("productIdList") List<String> productList);
}