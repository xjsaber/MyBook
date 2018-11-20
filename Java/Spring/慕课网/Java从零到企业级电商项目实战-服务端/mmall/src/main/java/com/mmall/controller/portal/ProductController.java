package com.mmall.controller.portal;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xjsaber
 */
@Controller
public class ProductController {

    @Autowired
    IProductService productService;

    @ResponseBody
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public ServerResponse<List<Product>> login(int categoryId,String keyword,@Param(value = "pageNum", = "1")int pageNum, int pageSize, String orderBy){
        return productService.list(categoryId, keyword, pageNum, pageSize, orderBy);
    }

    @ResponseBody
    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public ServerResponse<Product> detail(int productId){
        return productService.detail(productId);
    }
}
