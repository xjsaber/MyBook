package com.xjsaber.learn.spring.springboot.dao;

import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author xjsaber
 */
@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, Long> {

    /**
     * 符合JPA规范命名方法，则不需要再实现该方法也可用
     * 意在对满足条件的文档按照用户名称进行模糊查询
     * @param userName --用户名称
     * @return 满足条件的用户信息
     */
    List<MongoUser> findByUserNameLike(String userName);

    /**
     * 根据编号或者用户名查找用户
     * @param id -- 编号
     * @param userName -- 用户名
     * @return 用户信息
     */
    MongoUser findMongoUserByIdOrUserName(Long id, String userName);

    /**
     * 使用id和用户名称查询
     * 注解@Query 阿拉伯数字指定参数的下标，以0开始
     * @param id -- 编号
     * @param userName 用户编号
     * @return 用户信息
     */
    @Query("{'id': ?0, 'userName': ?1}")
    MongoUser find(Long id, String userName);


}
