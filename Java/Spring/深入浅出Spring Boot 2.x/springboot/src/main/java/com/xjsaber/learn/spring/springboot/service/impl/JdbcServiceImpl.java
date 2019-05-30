package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.JdbcTmpUserService;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xjsaber
 */
public class JdbcServiceImpl implements JdbcTmpUserService {

    @Autowired
    private DataSource dataSource = null;

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        Connection conn = null;
        int result = 0;
        try {
            // 获取连接
            conn = dataSource.getConnection();
            // 设置事务
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
            PreparedStatement ps = conn.prepareStatement("insert into t_user(user_name, note ) value (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            result = ps.executeUpdate();
            // 提交事务
            conn.commit();
        } catch (Exception e){
            // 回滚事务
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        } finally {
            // 关闭数据库连接
            try {
                if (conn != null && !conn.isClosed()){
                    conn.close();
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(long id) {
        return 0;
    }
}
