package com.xjsaber.java.permission.controller;

import com.xjsaber.java.permission.common.JsonData;
import com.xjsaber.java.permission.exception.ParamException;
import com.xjsaber.java.permission.exception.PermissionException;
import com.xjsaber.java.permission.param.TestVo;
import com.xjsaber.java.permission.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        throw new PermissionException("test error");
//        return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
