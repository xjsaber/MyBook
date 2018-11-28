package com.mmall.controller.portal;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ServerResponse<List<Product>> login(int categoryId, String keyword, @RequestParam(value = "pageNum", defaultValue = "1")int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize, @RequestParam(value = "orderBy", defaultValue = "")String orderBy){
        return productService.list(categoryId, keyword, pageNum, pageSize, orderBy);
    }

    @ResponseBody
    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public ServerResponse<Product> detail(int productId){
        return null;
//        return productService.detail(productId);
    }
}
