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
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer quality;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

}