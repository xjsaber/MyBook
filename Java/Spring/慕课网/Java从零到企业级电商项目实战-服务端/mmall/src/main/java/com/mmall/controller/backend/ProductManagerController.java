package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/manage/product/")
public class ProductManagerController {

    @ResponseBody
    @RequestMapping("list.do")
    public ServerResponse<List<Product>> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

    }

    @ResponseBody
    @RequestMapping("search.do")
    public ServerResponse<List<Product>> list(String productName, int productId,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

    }
}
