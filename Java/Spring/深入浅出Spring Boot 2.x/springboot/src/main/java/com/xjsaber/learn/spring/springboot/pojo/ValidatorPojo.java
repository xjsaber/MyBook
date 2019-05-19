package com.xjsaber.learn.spring.springboot.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author xjsaber
 */
@Data
public class ValidatorPojo {

    /**
     * 非空判断
     */
    @NotNull
    private Long id;

    /**
     * #@Future 只能是将来的日期
     * #@Past 只能是过去的日期
     * #@DateTimeFormat 日期格式化转换
     * #@NotNull 不能为空
     */
    @Future(message = "需要一个将来日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    /**
     * 最小值0.1元
     * 最大值10000元
     */
    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    private Double doubleValue = null;

    /**
     * Min 最小值为1
     * Max 最大值为88
     */
    @Min(value = 1, message = "最小值为1")
    @Max(value = 88, message = "最大值为88")
    @NotNull
    private Integer integer;

    /**
     * 限定范围
     */
    @Range(min = 1, max = 888, message = "范围为1至888")
    private Long range;

    @Email(message = "邮箱格式有错")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求在20到30之间")
    private String size;
}
