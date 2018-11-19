package com.mmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xjsaber
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {
    private Integer id;

    private Integer userId;

    private String receiveName;

    private String receivePhone;

    private String receiveMobile;

    private String receiveProvince;

    private String receiveCity;

    private String receiveDistrict;

    private String receiveAddress;

    private String receiveZip;

    private Date createTime;

    private Date updateTime;
}