package com.xjsaber.learn.spring.springboot.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.management.relation.Role;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * MongoDB使用的user
 * @author xjsaber
 */
@Data
@Document
public class MongoUser implements Serializable {

    public static final long serialVersionUID = -7895435231819517614L;

    /**
     * 在MongoDB中使用的user_name保存属性
     */
    @Id
    private Long id;

    /**
     * 在MongoDB中使用
     */
    @Field("user_name")
    private String userName = null;

    private String note = null;

    /**
     * 角色列表
     */
    private List<Role> roles = null;
}
