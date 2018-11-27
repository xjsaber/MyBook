package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
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

    @RequestMapping("list")
    ServerResponse list(){
        return null;
    }

    @RequestMapping("add")
    ServerResponse add(HttpSession session, Integer count, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return null;
    }

    @RequestMapping("update")
    ServerResponse update(){
        return null;
    }

    @RequestMapping("delete_product")
    ServerResponse deleteProduct(){
        return null;
    }

    @RequestMapping("select")
    ServerResponse select(){
        return null;
    }

    @RequestMapping("un_select")
    ServerResponse unSelect(){
        return null;
    }

    @RequestMapping("get_cart_product_count")
    ServerResponse getCartProductCount(){
        return null;
    }

    @RequestMapping("select_all")
    ServerResponse selectAll(){
        return null;
    }

    @RequestMapping("un_select_all")
    ServerResponse unSelectAll(){
        return null;
    }
}
