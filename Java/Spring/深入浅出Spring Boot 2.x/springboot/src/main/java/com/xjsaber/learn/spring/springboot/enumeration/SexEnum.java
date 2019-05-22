package com.xjsaber.learn.spring.springboot.enumeration;

import lombok.Getter;

/**
 * @author xjsaber
 */
@Getter
public enum SexEnum {

    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(2, "女");

    private int id;
    private String name;
    SexEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id){
        for (SexEnum sex: SexEnum.values()){
            if (sex.getId() == id){
                return sex;
            }
        }
        return null;
    }
}
