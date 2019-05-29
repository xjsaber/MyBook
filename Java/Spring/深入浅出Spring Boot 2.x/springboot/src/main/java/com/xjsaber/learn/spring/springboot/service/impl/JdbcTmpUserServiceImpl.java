package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.JdbcTmpUserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
     * @param id 编号
     * @return user
     */
    @Override
    public User getUser(Long id) {
        String sql = " select id, user_name, note, sex, note from t_user where id = ? ";
        Object[] params = new Object[] {id};
        try {
            return jdbcTemplate.queryForObject(sql, params, getUserMapper());
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    /**
     * 使用StatementCallback和ConnectionCallback执行多条SQL
     * @param id 编号
     * @return 用户信息
     */
    public User getUser2(Long id){
        //通过Lambda表达式使用StatementCallback
        User result = this.jdbcTemplate.execute((Statement stmt) -> {
            String sql = " select count(*) total from t_user where id = " + id;
            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs1.next()){
                int total = rs1.getInt("total");
                System.out.println(total);
            }
            String sql2 = " select id, user_name, sex, note from t_user "
                    + " where id = " + id;
            ResultSet rs2 = stmt.executeQuery(sql2);
            User user = null;
            while (rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2, rowNum);
            }
            return user;
        });
        return result;
    }

    public User getUser3(Long id){
        return jdbcTemplate.execute((Connection conn) -> {
            String sql1 = " select count(*) as total from t_user "
                    + " where id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                System.out.println(rs1.getInt("total"));
            }
            String sql2 = " select id, user_name, sex, note from t_user "
                    + " where id = ? ";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1, id);;
            ResultSet rs2 = ps2.executeQuery();
            User user = null;
            while (rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2, rowNum);
            }
            return user;
        });
    }

    /**
     * 查询用户列表
     * @param userName
     * @param note
     * @return
     */
    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = " select id, user_name, sex, note from t_user "
                + " where user_name like concat('%', ?, '%') "
                + " and note like concat('%', ?, '%')";
        Object[] params = new Object[] {userName, note};
        return jdbcTemplate.query(sql, params, getUserMapper());
    }

    /**
     * 插入数据库
     * @param user 用户信息
     * @return 新增结果
     */
    @Override
    public int insertUser(User user) {
        String sql = " insert into t_user (user_name, sex, note) values(?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getSex().getId(), user.getNote());
    }

    /**
     * 更新数据库
     * @param user 用户信息
     * @return 修改结果
     */
    @Override
    public int updateUser(User user) {
        // 执行的SQL
        String sql = " update t_user set user_name = ?, sex = ?, note = ? "
                + " where id = ? ";
        return jdbcTemplate.update(sql, user.getUsername(), user.getSex().getId(), user.getNote(), user.getId());
    }

    /**
     * 删除数据
     * @param id 编号
     * @return 删除结果
     */
    @Override
    public int deleteUser(long id) {
        String sql = " delete from t_user where id = ? ";
        return jdbcTemplate.update(sql, id);
    }
}
