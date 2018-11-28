package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *
 * @author xjsaber
 */
@RequestMapping("/cart/")
@RestController
public class CartController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping("list")
    ServerResponse<CartVo> list(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.getList(user.getId());
    }

    @RequestMapping("add")
    ServerResponse add(HttpSession session, Integer count, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.add(user.getId(), productId, count);
    }

    @RequestMapping("update")
    ServerResponse update(HttpSession session, Integer count, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.update(user.getId(), productId, count);
    }

    @RequestMapping("delete_product")
    ServerResponse deleteProduct(HttpSession session, String productIds){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.deleteProduct(user.getId(), productIds);
    }

    // 全选
    // 全反选

    @RequestMapping("select_all")
    ServerResponse selectAll(){
        return null;
    }

    // 单独选
    // 单独反选

    // 查询当前用户的购物车里面的产品数量，如果一个产品有10个，那么数量就是10。


    @RequestMapping("un_select")
    ServerResponse unSelect(){
        return null;
    }

    @RequestMapping("get_cart_product_count")
    ServerResponse getCartProductCount(){
        return null;
    }

    @RequestMapping("un_select_all")
    ServerResponse unSelectAll(){
        return null;
    }
}
