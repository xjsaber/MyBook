package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author xjsaber
 */
public interface UserMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int updatePasswordByPasswordOld(@Param("passwordOld") String passwordOld, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param(value="password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);

    /**
     * 验证管理员
     * @param userId 用户编号
     * @param role 角色编号
     * @return
     */
    int checkAdmin(Integer userId, Integer role);
}