package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.pojo.Cart;
import com.mmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        }
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
