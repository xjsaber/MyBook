package com.xjsaber.learn.spring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjsaber
 */
@RequestMapping("/my")
@Controller
public class MyController {

    @GetMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str){
        Map<String, Object> paramsMap = new HashMap<>(8);
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    /**
     * 通过注解@RequestParam获取参数
     * @param intVal ——整数
     * @param longVal ——长整数
     * @param str ——字符串
     * @return 响应JSON数据集
     */
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String, Object> requestParam(@RequestParam("int_val") Integer intVal,@RequestParam("long_val") Long longVal, @RequestParam("str") String str){
        Map<String, Object> paramsMap = new HashMap<>(8);
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    /**
     * 通过注解@RequestParam获取参数
     * @param intArr ——整数
     * @param longArr ——长整数
     * @param strArr ——字符串
     * @return 响应JSON数据集
     */
    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr,Long[] longArr, String[] strArr){
        Map<String, Object> paramsMap = new HashMap<>(8);
        paramsMap.put("intVal", intArr);
        paramsMap.put("longVal", longArr);
        paramsMap.put("str", strArr);
        return paramsMap;
    }

    @GetMapping("/valid/page")
    public String validatePage(){
        return "/validator/pojo";
    }
}
