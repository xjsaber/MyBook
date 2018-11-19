package com.mmall.controller.portal;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
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

    int categoryId = 0;
    int keyword = 0;
    int pageNum = 1;
    int pageSize = 10;
    String orderBy = "";

    @ResponseBody
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<List<Product>> login(int categoryId, String keyword, int pageNum, int pageSize, String orderBy){
        return productService.list(categoryId, keyword, pageNum, pageSize, orderBy);
    }
}
