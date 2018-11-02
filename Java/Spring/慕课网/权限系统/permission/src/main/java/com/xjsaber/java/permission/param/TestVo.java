package com.xjsaber.java.permission.param;

import com.xjsaber.java.permission.common.JsonData;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class TestVo {

    /**************************************************************
     * 是否是email，长度限制 Max，Min
     */

    @NotBlank
    private String msg;
    @NotNull
    @Max(value = 10, message = "id 不能超过10")
    @Min(value = 0, message = "id 至少大于等于0")
    private Integer id;
    @NotEmpty
    private List<String> str;
}
