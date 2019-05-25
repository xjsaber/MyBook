package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * @author xjsaber
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer> {

    /**
     * 将枚举转化为数据库列
     */
    @Override
    public Integer convertToDatabaseColumn(SexEnum sex) {
        return sex.getId();
    }

    /**
     * 将数据库列转化为枚举
     */
    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
