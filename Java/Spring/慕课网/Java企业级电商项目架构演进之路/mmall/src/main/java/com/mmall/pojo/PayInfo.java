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
public class PayInfo {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Integer payPlatform;

    private String platformNumberr;

    private String platformStatus;

    private Date createTime;

    private Date updateTime;
}