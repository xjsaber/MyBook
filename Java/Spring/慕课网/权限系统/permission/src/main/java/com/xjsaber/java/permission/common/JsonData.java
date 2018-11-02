package com.xjsaber.java.permission.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JsonData {

    /**
     * 表示接口数据状态，true or false
     * true 从data里面拿数据
     * false 表示数据返回不回来，从msg里拿数据信息
     */
    private boolean ret;

    private String msg;

    private Object data;

    public JsonData(boolean ret){
        this.ret = ret;
    }

    public static JsonData success(Object object, String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData jsonData(){
        return new JsonData(true);
    }

    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("ret", ret);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }
}
