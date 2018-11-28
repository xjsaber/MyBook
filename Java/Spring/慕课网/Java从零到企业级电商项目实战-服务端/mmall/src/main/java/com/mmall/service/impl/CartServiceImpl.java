package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.service.ICartService;
import com.mmall.vo.CartProductVo;
import com.mmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xjsaber
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ServerResponse<String> add(Integer userId, Integer productId, Integer count) {
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null){
            // 这个产品不在购物车中，需要增加一个这个产品的记录
            Cart cartItem = new Cart();
            cartItem.setQualtity(count);
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartMapper.insert(cartItem);
        }
        else {
            // 这个产品已经在购物车里了
            // 这个产品已经存在，数量相加
            count = cart.getQualtity() + count;
            cart.setQualtity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return null;
    }

    private CartVo getCartVoLimit(Integer userId){
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        return null;
    }

    @Override
    public ServerResponse<List<Cart>> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = cartMapper.selectList();
        return ServerResponse.createBySuccess(list);
    }



    @Override
    public ServerResponse<String> addOrUpdate(Cart cart) {
        if (cart.getId() == null){
            int resultRow = cartMapper.insertSelective(cart);
            if (resultRow > 0){
                return ServerResponse.createBySuccess("新增成功");
            }else {
                return ServerResponse.createBySuccess("新增失败");
            }
        }
        else {
            int resultRow = cartMapper.updateByPrimaryKey(cart);
            if (resultRow > 0){
                return ServerResponse.createBySuccess("修改成功");
            }else {
                return ServerResponse.createBySuccess("修改失败");
            }
        }
    }

    @Override
    public ServerResponse<String> delete(Integer cartId) {
        if (cartId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int resultRow = cartMapper.deleteByPrimaryKey(cartId);
        if (resultRow > 0){
            return ServerResponse.createBySuccess("删除成功");
        }else {
            return ServerResponse.createBySuccess("删除失败");
        }
    }

    @Override
    public ServerResponse<Cart> selectCart(Integer cartId) {
        return null;
    }
}
