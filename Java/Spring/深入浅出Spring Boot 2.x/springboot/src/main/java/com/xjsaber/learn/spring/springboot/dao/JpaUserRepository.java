package com.xjsaber.learn.spring.springboot.dao;

import com.xjsaber.learn.spring.springboot.pojo.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author xjsaber
 */
public interface JpaUserRepository
    extends JpaRepository<JpaUser, Long> {

    /**
     * 按用户名称模糊查询
     * @param userName 用户名
     * @return 用户列表
     */
    @Query("from user where user_name like concat('%', ?1, '%') ")
    List<JpaUser> findByUserNameLike(String userName);

    /**
     * 根据主键查询
     * @param id ——主键
     * @return 用户
     */
    JpaUser getUserById(Long id);

    /**
     * 按照用户名称或者备注进行模糊查询
     * @param userName 用户名
     * @param note 备注
     * @return 用户列表
     */
    @Query("from user where user_name like concat('%', ?1, '%') "
            + "and note like concat('', ?2, '%')")
    List<JpaUser> findByUserNameLikeOrNoteLike(String userName, String note);
}
