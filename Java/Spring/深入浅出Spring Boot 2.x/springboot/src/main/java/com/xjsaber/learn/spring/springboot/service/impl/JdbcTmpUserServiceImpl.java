package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.JdbcTmpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author xjsaber
 */
@Service
public class JdbcTmpUserServiceImpl implements JdbcTmpUserService {

    private JdbcTemplate jdbcTemplate = null;

    public JdbcTmpUserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取映射关系
     * @return RowMapper
     */
    private RowMapper<User> getUserMapper() {
        return (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("id"));
            int sexId  = rs.getInt("sex");
            SexEnum sex = SexEnum.getEnumById(sexId);
            user.setSex(sex);
            user.setNote(rs.getString("note"));
            user.setId(rs.getLong("id"));
            return user;
        };
    }

    /**
     * 获取对象
     * @param id
     * @return
     */
    @Override
    public User getUser(Long id) {
        return null;
    }

    /**
     * 查询用户列表
     * @param userName
     * @param note
     * @return
     */
    @Override
    public List<User> findUsers(String userName, String note) {
        return null;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        return 0;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteUser(long id) {
        return 0;
    }
}
